package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class TemporalParam extends NonTerminal {

    private final String name;
    private final TypeIF type;

    public TemporalParam(String name, TypeIF type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public TypeIF getType() {
        return type;
    }
}
