package compiler.lexical;

import es.uned.lsi.compiler.lexical.TokenBase;

public class Token  
   extends TokenBase  
{
    private String lexema = null;
    private int    id     = 0;
    private int    line   = 0;
    private int    column = 0;
    
    /**
     * Constructor for Token.
     */
    public Token ()
    {
        super (); 
    }
    
    /**
     * Constructor for Token.
     * @param id The token id.
     */
    public Token (int id)
    {
        super (id);
        this.id = id;
    }

    /**
     * Returns the id.
     * @return Returns the id.
     */
    @Override
    public final int getId ()
    {
        return id;
    }

    /**
     * Sets The id.
     * @param id The id to set.
     */
    @Override
    public final void setId (int id)
    {
        this.id = id;
    }
    
    /**
     * Returns the line.
     * @return Returns the line.
     */
    @Override
    public final int getLine ()
    {
        return line;
    }

    /**
     * Sets The line.
     * @param line The line to set.
     */
    @Override
    public final void setLine (int line)
    {
        this.line = line;
    }
    
    /**
     * Returns the column.
     * @return Returns the column.
     */
    @Override
    public final int getColumn ()
    {
        return column;
    }

    /**
     * Sets The column.
     * @param column The column to set.
     */
    @Override
    public final void setColumn (int column)
    {
        this.column = column;
    }

    /**
     * Returns the lexema.
     * @return Returns the lexema.
     */
    @Override
    public final String getLexema ()
    {
        return lexema;
    }

    /**
     * Sets The lexema.
     * @param lexema The lexema to set.
     */
    @Override
    public final void setLexema (String lexema)
    {
        this.lexema = lexema;
    }
}
