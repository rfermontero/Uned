package compiler.syntax.nonTerminal;

import java.util.List;

public class ParametersList extends NonTerminal {

    private final List<Parameter> params;

    public ParametersList(List<Parameter> params) {
        this.params = params;
    }

    public List<Parameter> getParams() {
        return params;
    }
}
