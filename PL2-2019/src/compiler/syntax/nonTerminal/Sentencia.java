package compiler.syntax.nonTerminal;

public class Sentencia extends NonTerminal {

	private SentFor sentFor;
	private SentProcedure sentProcedure;
	private SWriteLn sWriteLn;
	private SWriteInt sWriteInt;
	private SWriteString sWriteString;
	private SentReturn sentReturn;
	private SentAsign sentAsign;
	private SentIf sentIf;

	public Sentencia(SentAsign sentAsign) {
		super();
		this.sentAsign = sentAsign;
	}

	public Sentencia(SentIf sentIf) {
		super();
		this.sentIf = sentIf;
	}

	public Sentencia(SentReturn sentReturn) {
		super();
		this.sentReturn = sentReturn;
	}

	public Sentencia(SWriteString sWriteString) {
		super();
		this.sWriteString = sWriteString;
	}

	public Sentencia(SWriteInt sWriteInt) {
		super();
		this.sWriteInt = sWriteInt;
	}

	public Sentencia(SentFor sentFor) {
		super();
		this.sentFor = sentFor;
	}

	public Sentencia(SWriteLn sWriteLn) {
		super();
		this.sWriteLn = sWriteLn;
	}

	public Sentencia(SentProcedure sentProcedure) {
		super();
		this.sentProcedure = sentProcedure;
	}

	public SentProcedure getSentProcedure() {
		return sentProcedure;
	}

	public void setSentProcedure(SentProcedure sentProcedure) {
		this.sentProcedure = sentProcedure;
	}

	public SWriteLn getsWriteLn() {
		return sWriteLn;
	}

	public void setsWriteLn(SWriteLn sWriteLn) {
		this.sWriteLn = sWriteLn;
	}

	public SWriteInt getsWriteInt() {
		return sWriteInt;
	}

	public void setsWriteInt(SWriteInt sWriteInt) {
		this.sWriteInt = sWriteInt;
	}

	public SWriteString getsWriteString() {
		return sWriteString;
	}

	public void setsWriteString(SWriteString sWriteString) {
		this.sWriteString = sWriteString;
	}

	public SentReturn getSentReturn() {
		return sentReturn;
	}

	public void setSentReturn(SentReturn sentReturn) {
		this.sentReturn = sentReturn;
	}

	public SentAsign getSentAsign() {
		return sentAsign;
	}

	public void setSentAsign(SentAsign sentAsign) {
		this.sentAsign = sentAsign;
	}

	public SentIf getSentIf() {
		return sentIf;
	}

	public void setSentIf(SentIf sentIf) {
		this.sentIf = sentIf;
	}

	public SentFor getSentFor() {
		return sentFor;
	}
}