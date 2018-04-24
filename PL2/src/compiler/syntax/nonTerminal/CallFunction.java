package compiler.syntax.nonTerminal;

public class CallFunction extends AbstractSentence {

    private final String name;
    private final ParametersList parameterList;

    public CallFunction(String name, ParametersList parametersList) {
        this.name = name;
        this.parameterList = parametersList;
    }

    public String getName() {
        return name;
    }

    public ParametersList getParameterList() {
        return parameterList;
    }
}
