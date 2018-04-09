
package compiler.test;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.syntax.SyntaxErrorManager;

/**
 * Test class for syntax checking.
 */
public class SyntaxTestCase
{
	
	/**
     * Constructor for SyntaxisTestCase.
     */
    public SyntaxTestCase ()
    {
    }

    /**
     * Tests the parser against a test file.
     * @param fileName The path of the file test.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public void testParse (String fileName)
    {
       
        SyntaxErrorManager syntaxErrorManager = new SyntaxErrorManager ();       
        try
        {
            syntaxErrorManager.syntaxInfo ("Input file > " + fileName);

            FileReader aFileReader = new FileReader (fileName);
            ScannerIF aScanner = null; 
         
            
            // Llamada por introspeccion a las clases compiler.lexical.Scanner y 
            // compiler.syntax.parser ya que no existen hasta invocar a la tarea build
            // En la entrega de Febrero se generará una excepción en caso de no estar 
            // comentadas las líneas correspondientes en el fichero parser.cup
            
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
            // reflect parser
   			Class parserClass = Class.forName ("compiler.syntax.parser");
			Constructor parserConstructor = parserClass.getConstructor(java_cup.runtime.Scanner.class);
			// reflect call parser.pase()
			Method parseMethod =  parserClass.getMethod("parse");
			Object aParser = parserConstructor.newInstance(aScanner);		
			parseMethod.invoke(aParser);
            			
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    /**
     * Starts the parser test case.
     * @param args Arguments Array containing the path to the input file at the
     *            first element.
     */
    public static void main (String args[])
    {
        if (args.length < 1 || args.length > 1)
        {
            System.err.println ("Use:  java FinalTestCase [file name]");
        }
        else
        {
            SyntaxTestCase syntaxTest = new SyntaxTestCase ();
            String fileName = args[0];
            syntaxTest.testParse (fileName);
        }
    }
}
