package compiler.syntax.nonTerminal;


/**
 * Abstract class for non terminals.
 */
public abstract class NonTerminal implements NonTerminalIF {

    private List<QuadrupleIF> intermediateCode;

    /**
     * Constructor for NonTerminal.
     */
    public NonTerminal() {
        super();
        this.intermediateCode = new ArrayList<QuadrupleIF>();
    }

    /**
     * Returns the intermediateCode.
     * 
     * @return Returns the intermediateCode.
     */
    public List<QuadrupleIF> getIntermediateCode() {
        return intermediateCode;
    }

    /**
     * Sets The intermediateCode.
     * 
     * @param intermediateCode The intermediateCode to set.
     */
    public void setIntermediateCode(List<QuadrupleIF> intermediateCode) {
        this.intermediateCode = intermediateCode;
    }
}