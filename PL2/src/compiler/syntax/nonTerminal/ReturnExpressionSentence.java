package compiler.syntax.nonTerminal;

public  class ReturnExpressionSentence extends AbstractSentence {

    private final AbstractReturn expressionReturn;

    public ReturnExpressionSentence(AbstractReturn expressionReturn) {
        this.expressionReturn = expressionReturn;
    }

    public AbstractReturn getExpressionReturn(){
        return this.expressionReturn;
    }
}
