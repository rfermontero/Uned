package compiler.syntax.nonTerminal;

public class StmVar extends NonTerminal {

    private final SentVar sentVar;

    public StmVar(SentVar sentVar) {
        super();
        this.sentVar = sentVar;
    }

    public SentVar getSentVar(){
        return this.sentVar;
    }
}