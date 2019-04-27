package compiler.syntax.nonTerminal;

public class StmSubprogram extends NonTerminal {

	private Cuerpo cuerpo;
	private CabProcedure cabProcedure;

	public StmSubprogram() {
		super();
	}

	public void addCuerpoAndProcedure(Cuerpo cuerpo, CabProcedure cabProcedure) {
		this.cabProcedure = cabProcedure;
		this.cuerpo = cuerpo;
	}
}