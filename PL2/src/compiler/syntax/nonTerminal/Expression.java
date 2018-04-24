package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public abstract class Expression extends NonTerminal {

    private TypeIF returnType;

    public Expression(TypeIF returnType) {
        this.returnType = returnType;
    }

    public TypeIF getReturnType() {
        return returnType;
    }
}
