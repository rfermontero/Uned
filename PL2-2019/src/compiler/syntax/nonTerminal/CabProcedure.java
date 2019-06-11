package compiler.syntax.nonTerminal;

import java.util.List;

import compiler.CompilerContext;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolProcedure;
import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeProcedure;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.ScopeManager;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class CabProcedure extends NonTerminal {

	private final String identificador;
	private final ProcParenParam procParemParam;
	private final TipoRetorno tipoRetorno;
	private final ScopeIF scope;
	private Cuerpo cuerpo;

	public CabProcedure(ScopeIF scope, String identificador, ProcParenParam procParenParam, TipoRetorno tipoRetorno) {
		super();
		this.identificador = identificador;
		this.procParemParam = procParenParam;
		this.tipoRetorno = tipoRetorno;
		this.scope = scope;
	}

	public String getIdentificador() {
		return identificador;
	}

	public ProcParenParam getProcParemParam() {
		return procParemParam;
	}

	public TipoRetorno getTipoRetorno() {
		return tipoRetorno;
	}

	public void addCuerpo(Cuerpo cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Cuerpo getCuerpo() {
		return cuerpo;
	}

	@Override
	public List<QuadrupleIF> getIntermediateCode() {

		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);

		LabelFactoryIF lf = new LabelFactory();
		LabelIF l1 = lf.create(identificador);
		LabelIF l2 = lf.create('F'+identificador);
		TemporalFactory tF = new TemporalFactory(scope);
		TemporalIF temp = tF.create();
		
		cb.addQuadruple("INL", l1);

		List<SymbolIF> symbols = scope.getSymbolTable().getSymbols();
		for(SymbolIF symbol : symbols){
			if(symbol instanceof SymbolVariable){
				SymbolVariable symbolVariable = (SymbolVariable) symbol;
				int value = symbolVariable.getValue();
				//cb.addQuadruple("SUBPROGRAMPARAM", temp, value);
			}
		}

		int size = scope.getSymbolTable().getSize()*scope.getTemporalTable().getSize() + 5;
		cb.addQuadruple("POINTERRA", temp, size);
		cb.addQuadruples(cuerpo.getIntermediateCode());
		int paramsSize = ((SymbolProcedure) CompilerContext.getScopeManager().searchSymbol(identificador)).getNumberOfParams();
		cb.addQuadruple("ENDPOINTERRA", l2, paramsSize+5);

		return cb.create();
	}

	@Override
	public String toString() {
		return "CabProcedure{" +
				"identificador='" + identificador + '\'' +
				", procParemParam=" + procParemParam +
				", tipoRetorno=" + tipoRetorno +
				", cuerpo=" + cuerpo +
				'}';
	}
}