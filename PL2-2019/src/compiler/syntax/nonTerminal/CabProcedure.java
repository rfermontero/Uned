package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Procedure;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

import java.util.List;

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
		SymbolIF symbol = scope.getSymbolTable().getSymbol(getIdentificador());
		Procedure procedure = new Procedure(getIdentificador(), scope, symbol);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
		cb.addQuadruple("INL", procedure.getCodeLabel(), scope.getLevel());
		cb.addQuadruples(cuerpo.getIntermediateCode());
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