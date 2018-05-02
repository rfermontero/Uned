package compiler.semantic.symbol;

import compiler.syntax.nonTerminal.BlockSentences;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

import java.util.List;


public class SymbolFunction extends SymbolProcedure {

    private final List<SymbolParameter> parameters;
    private final TypeIF returnType;
    private final BlockSentences sentences;

    public SymbolFunction(ScopeIF scope,
                          String name,
                          TypeIF returnType,
                          List<SymbolParameter> parameters,
                          BlockSentences sentences) {
        super(scope, name, returnType);
        this.parameters = parameters;
        this.returnType = returnType;
        this.sentences = sentences;
    }

    public TypeIF getReturnType() {
        return returnType;
    }

    public List<SymbolParameter> getParameters() {
        return parameters;
    }

    public BlockSentences getSentences() {
        return sentences;
    }
}
