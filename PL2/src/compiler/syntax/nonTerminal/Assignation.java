package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Assignation extends AbstractSentence {
    private Expression expression;
    private String name;
    private ArrayRange leftRange;
    private ArrayRange rightRange;

    public Assignation(String name, Expression expression) {
        super();
        this.name = name;
        this.expression = expression;
    }

    public Assignation(String name, ArrayRange leftRange, ArrayRange rightRange, Expression expression) {
        super();
        this.name = name;
        this.expression = expression;
        this.leftRange = leftRange;
        this.rightRange = rightRange;
    }

    public TypeIF getReturnType() {
        return expression.getReturnType();
    }
}
