package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

import java.util.ArrayList;
import java.util.List;

public class Sentencia extends NonTerminal {

	private final SentenciaType type;

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
		this.type = SentenciaType.ASIGN;
	}

	public Sentencia(SentIf sentIf) {
		super();
		this.sentIf = sentIf;
		this.type = SentenciaType.IF;
	}

	public Sentencia(SentReturn sentReturn) {
		super();
		this.sentReturn = sentReturn;
		this.type = SentenciaType.RETURN;
	}

	public Sentencia(SWriteString sWriteString) {
		super();
		this.sWriteString = sWriteString;
		this.type = SentenciaType.SWRITE;
	}

	public Sentencia(SWriteInt sWriteInt) {
		super();
		this.sWriteInt = sWriteInt;
		this.type = SentenciaType.INTWRITE;
	}

	public Sentencia(SentFor sentFor) {
		super();
		this.sentFor = sentFor;
		this.type = SentenciaType.FOR;
	}

	public Sentencia(SWriteLn sWriteLn) {
		super();
		this.sWriteLn = sWriteLn;
		this.type = SentenciaType.LNWRITE;
	}

	public Sentencia(SentProcedure sentProcedure) {
		super();
		this.sentProcedure = sentProcedure;
		this.type = SentenciaType.PROCEDURE;
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

	public SentenciaType getType() {
		return type;
	}

	public enum SentenciaType {
		FOR, ASIGN, IF, SWRITE, RETURN, LNWRITE, INTWRITE, PROCEDURE
	}

	@Override
	public List<QuadrupleIF> getIntermediateCode() {
		List<QuadrupleIF> result;
		switch (type) {
			case FOR:
				result = sentFor.getIntermediateCode();
				break;

			case ASIGN:
				result = sentAsign.getIntermediateCode();
				break;

			case IF:
				result = sentIf.getIntermediateCode();
				break;

			case SWRITE:
				result = sWriteString.getIntermediateCode();
				break;

			case RETURN:
				result = sentReturn.getIntermediateCode();
				break;

			case LNWRITE:
				result = sWriteLn.getIntermediateCode();
				break;

			case INTWRITE:
				result = sWriteInt.getIntermediateCode();
				break;

			case PROCEDURE:
				result = sentProcedure.getIntermediateCode();
				break;
			default:
				result = new ArrayList<>();
		}
		return result;
	}
}