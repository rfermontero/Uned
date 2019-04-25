package compiler.syntax.nonTerminal;

public class EntOId extends NonTerminal {

    private String identificador;
    private int value;

    public EntOId(int value) {
        super();
        this.value = value;
    }

    public EntOId(String identificador) {
        super();
        this.identificador = identificador;
    }
}