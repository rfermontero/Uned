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
import es.uned.lsi.compiler.semantic.ScopeIF;
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

		SymbolProcedure symbol = (SymbolProcedure) scope.getSymbolTable().getSymbol(getIdentificador());
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);

		LabelFactoryIF lf = new LabelFactory();
		LabelIF l1 = lf.create(identificador);
		LabelIF l2 = lf.create("F" + identificador);

		cb.addQuadruple("INL", l1);

		TypeProcedure type = (TypeProcedure) CompilerContext.getScopeManager().searchType(identificador);
		for (SymbolIF symbolInScope : scope.getSymbolTable().getSymbols()) {
			if (symbolInScope instanceof SymbolVariable) {
				SymbolVariable symbolVariable = (SymbolVariable) symbolInScope;
				int value = symbolVariable.getValue();
				if (value != -1) {
					Variable var = new Variable(symbolVariable);
					cb.addQuadruple("INITVAR",var,value);
				}
			}
		}

		cb.addQuadruple("RESRA", new Value(type.getSize()));
		cb.addQuadruples(cuerpo.getIntermediateCode());
		cb.addQuadruple("ENDFUNC",l2, symbol.getNumberOfParams() + 4);
		
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