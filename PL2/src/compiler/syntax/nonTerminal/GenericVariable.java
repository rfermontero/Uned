package compiler.syntax.nonTerminal;


public class GenericVariable extends NonTerminal {

    private final String name;
    private final String type;
    private final String identifier;
    private final Integer value;

    public GenericVariable(String name, String type) {
        this.name = name;
        this.type = type;
        this.identifier = null;
        this.value = null;
    }

    public GenericVariable(String name, String type, String identifier) {
        this.name = name;
        this.type = type;
        this.identifier = identifier;
        this.value = null;
    }

    public GenericVariable(String name, String type, Integer value) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.identifier = null;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
