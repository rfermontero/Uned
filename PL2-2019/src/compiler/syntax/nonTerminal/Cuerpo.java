package compiler.syntax.nonTerminal;

public class Cuerpo extends NonTerminal {

	private final StmConstantes stmConstantes;
	private final StmTipos stmTipos;
	private final StmVar stmVar;
	private final StmSubprogram stmSubprogram;
	private final Sentencias sentencias;

	public Cuerpo(StmConstantes stmConstantes, StmTipos stmTipos, StmVar stmVar, StmSubprogram stmSubprogram, Sentencias sentencias) {
		super();
		this.stmConstantes = stmConstantes;
		this.stmTipos = stmTipos;
		this.stmVar = stmVar;
		this.stmSubprogram = stmSubprogram;
		this.sentencias = sentencias;
	}

	public StmConstantes getStmConstantes() {
		return stmConstantes;
	}

	public StmTipos getStmTipos() {
		return stmTipos;
	}

	public StmVar getStmVar() {
		return stmVar;
	}

	public StmSubprogram getStmSubprogram() {
		return stmSubprogram;
	}

	public Sentencias getSentencias() {
		return sentencias;
	}
}