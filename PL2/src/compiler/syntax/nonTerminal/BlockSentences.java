package compiler.syntax.nonTerminal;

public class BlockSentences extends NonTerminal {

    private TypesDeclaration typesDeclaration;

    public BlockSentences() {
        this.typesDeclaration = null;
    }

    public BlockSentences(TypesDeclaration typesDeclaration) {
        this.typesDeclaration = typesDeclaration;
    }

    public TypesDeclaration getTypesDeclaration() {
        return typesDeclaration;
    }

    public void add(BlockSentences sentence) {
        if (typesDeclaration == null) {
            this.typesDeclaration = sentence.getTypesDeclaration();
        } else {
            typesDeclaration.addTypesDeclaration(sentence.typesDeclaration);
        }
    }
}
