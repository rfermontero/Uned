package compiler.test;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Constructor;

import JFlex.sym;

import compiler.lexical.Token;

import es.uned.lsi.compiler.lexical.LexicalErrorManager;
import es.uned.lsi.compiler.lexical.ScannerIF;

/**
 * Test class for lexical checking. 
 */

public class LexicalTestCase
{
	
    /**
     * Constructor for LexicalTestCase.
     */
    public LexicalTestCase ()
    {
    	 
    }
              
    /**
     * Tests the scanner.
     * @param test The test file name.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
	public void testScan (String fileName)
    {
        LexicalErrorManager lexicalErrorManager = new LexicalErrorManager ();
        try
        {
            lexicalErrorManager.lexicalInfo ("Input file > " + fileName);
            
            FileReader aFileReader = new FileReader (fileName);
            ScannerIF aScanner = null;            
   			
            //Scanner
            Class scannerClass = null;
            try {
            scannerClass = Class.forName ("compiler.lexical.Scanner");   			
            }
            catch (Exception e1){
            	System.err.println ("Error! Scanner or parser not implemented." +
            			"Use build ant task to obtain Scanner.java, parser.java and sym.java \n");
            }
            
			Constructor scannerConstructor = scannerClass.getConstructor(Reader.class);
   			aScanner = (ScannerIF) scannerConstructor.newInstance(aFileReader);
   		    
   			Object anObject = aScanner.next_token ();
            while (anObject instanceof Token)
            {
                Token aToken = (Token) anObject;
                if (aToken.sym == sym.EOF) break;
                lexicalErrorManager.lexicalInfo (aToken);
                anObject = aScanner.next_token ();
            }
            lexicalErrorManager.lexicalInfo ("End of file.");
        }

        catch (Exception e2)
        {
            e2.printStackTrace ();
        }
    }
      
	/**
     * Starts the scanner test case.
     * @param args Arguments Array containing the path to the input file at the
     *            first element.
     */
    public static void main (String args[])
    {  
        if (args.length < 1 || args.length > 1) 
        {
        	System.err.println ("Use:  java FinalTestCase \"file\"");        
        }
        else
        {
            LexicalTestCase lexicalTest = new LexicalTestCase ();
            String fileName = args[0];
            lexicalTest.testScan (fileName);
        }
    }	
}
