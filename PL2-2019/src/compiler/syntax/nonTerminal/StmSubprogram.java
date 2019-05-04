package compiler.syntax.nonTerminal;

public class StmSubprogram extends NonTerminal {

	private Cuerpo cuerpo;
	private CabProcedure cabProcedure;

	public StmSubprogram() {
		super();
	}

	public StmSubprogram addCuerpoAndProcedure(Cuerpo cuerpo, CabProcedure cabProcedure) {
		this.cabProcedure = cabProcedure;
		this.cuerpo = cuerpo;
		return this;
	}


	public Cuerpo getCuerpo() {
		return cuerpo;
	}

	public CabProcedure getCabProcedure() {
		return cabProcedure;
	}
}