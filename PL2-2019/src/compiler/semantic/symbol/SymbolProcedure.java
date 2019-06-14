package compiler.semantic.symbol;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

import compiler.syntax.nonTerminal.CabProcedure;
import compiler.syntax.nonTerminal.ProcParam;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolProcedure.
 */

public class SymbolProcedure extends SymbolBase {

	private int size;
	private int tempSize;

	private final CabProcedure cabProcedure;

	/**
	 * Constructor for SymbolProcedure.
	 *
	 * @param scope The declaration scope.
	 * @param name  The symbol name.
	 * @param type  The symbol type.
	 */
	public SymbolProcedure(ScopeIF scope,
	                       String name,
	                       CabProcedure cabProcedure,
	                       TypeIF type) {
		super(scope, name, type);
		this.cabProcedure = cabProcedure;
	}

	public CabProcedure getCabProcedure() {
		return cabProcedure;
	}

	public int getNumberOfParams() {
		int params = 0;
		for(ProcParam procParam : cabProcedure.getProcParemParam().getProcParams()){
			params+=procParam.getCadIdVar().getIdentificadores().size();
		}
		return params;
	}

	public List<String> getParameterNames() {
		List<String> params = new ArrayList<String>();
		for(ProcParam procParam : cabProcedure.getProcParemParam().getProcParams()){
			for(String param : procParam.getCadIdVar().getIdentificadores()){
				params.add(param);
			}
		}
		return params;
	}

	public int getTempSize() {
		return tempSize;
	}

	public void setTempSize(int tempSize) {
		this.tempSize = tempSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void incrementSize(int size) {
		this.size += size;
	}

	public void incrementSize() {
		size += 1;
	}

	public int getParamSize() {
		return this.getSize() - tempSize;
	}

	public void incrementTempSize(int tempSize) {
		this.tempSize += tempSize;
	}

	public void incrementTempSize() {
		this.tempSize++;
	}
}
