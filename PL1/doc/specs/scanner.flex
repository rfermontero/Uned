package compiler.lexical;

import compiler.syntax.sym;
import compiler.lexical.Token;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.lexical.LexicalError;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;
import java_cup.runtime.Symbol;

%%
 
%public
%class Scanner
%char
%line
%column
%cup
%unicode
%ignorecase

%implements ScannerIF
%scanerror LexicalError

%x comment

%{
  LexicalErrorManager lexicalErrorManager = new LexicalErrorManager();
  private int commentCount = 0;
%}
  
whiteSpace=[ \t\r\n\f]
lineTerminator = [\r|\n|\r\n]
digits = 0 | [1-9][0-9]*
literalString = \"([^\\\"]|\\.)*\"
identifier = [A-Za-z][A-Za-z0-9]*
error = [^]
eveything = .*

%%


<comment> "(*"               { commentCount++; }
<comment> "*)"               { commentCount--; 
                                if(commentCount == 0) {
                                   yybegin(YYINITIAL); 
                                }
                             }
<comment> .|"\n"             { /* do nothing */ }

<<EOF>> {
    if(commentCount!=0) {
      LexicalError error = new LexicalError();
      error.setLexema("Error in nested comments");
      lexicalErrorManager.lexicalError(error);
    }
    return new Symbol(sym.EOF);
  }

<YYINITIAL> 
{

    {whiteSpace}	   {/* DO NOTHING */}

    "(*"             { yybegin(comment); }
   
                   
    "-"              {
                        Token token = new Token(sym.MINUS);
                        token.setLine(yyline + 1);
                        token.setColumn(yycolumn + 1);
                        token.setLexema(yytext());
                        return token;
                    }
                            
    "*"             {
                        Token token = new Token(sym.MULT);
                        token.setLine(yyline + 1);
                        token.setColumn(yycolumn + 1);
                        token.setLexema(yytext());
                        return token;
                    }

    "TRUE"          {
                         Token token = new Token(sym.TRUE);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 4);
                         token.setLexema(yytext());
                         return token;
                      }

    "FALSE"         {
                         Token token = new Token(sym.FALSE);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 4);
                         token.setLexema(yytext());
                         return token;
                      }
    
    "PROCEDURE"      {
                         Token token = new Token(sym.PROCEDURE);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
                        
    "ARRAY"          {
                         Token token = new Token(sym.ARRAY);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                    }

    "BEGIN"          {
                         Token token = new Token(sym.BEGIN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                    }

    "BOOLEAN"        {
                         Token token = new Token(sym.BOOLEAN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                     }

    "INTEGER"          {
                         Token token = new Token(sym.INTEGER);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "MODULE"           {
                         Token token = new Token(sym.MODULE);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "END"              {
                         Token token = new Token(sym.END);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    ":"                {
                         Token token = new Token(sym.COLON);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    ";"                {
                         Token token = new Token(sym.SEMICOLON);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
                      
    "WRITESTRING"       {
                         Token token = new Token(sym.WRITESTRING);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
          
    "WRITEINT"          {
                         Token token = new Token(sym.WRITEINT);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "WRITELN"           {
                         Token token = new Token(sym.WRITELN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "("                 {
                         Token token = new Token(sym.LEFT_PAREN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    ")"                 {
                         Token token = new Token(sym.RIGHT_PAREN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
    "["                 {
                         Token token = new Token(sym.LEFT_BRACK);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "]"               {
                         Token token = new Token(sym.RIGHT_BRACK);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "CONST"           {
                         Token token = new Token(sym.CONST);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "VAR"             {
                         Token token = new Token(sym.VAR);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "TYPE"            {
                         Token token = new Token(sym.TYPE);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "TO"              {
                         Token token = new Token(sym.TO);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "THEN"            {
                         Token token = new Token(sym.THEN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "RETURN"          {
                         Token token = new Token(sym.RETURN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "IF"              {
                         Token token = new Token(sym.IF);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }


    "ELSE"            {
                         Token token = new Token(sym.ELSE);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "DO"              {
                         Token token = new Token(sym.DO);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "FOR"             {
                         Token token = new Token(sym.FOR);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    ":="              {
                         Token token = new Token(sym.COLON_EQUAL);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    "="               {
                         Token token = new Token(sym.EQUALS);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    ">"               {
                         Token token = new Token(sym.BIGGER_THAN);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
                      
     "OR"             {
                         Token token = new Token(sym.OR);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
     "OF"             {
                         Token token = new Token(sym.OF);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
    "NOT"             {
                         Token token = new Token(sym.NOT);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
    "."          {
                         Token token = new Token(sym.POINT);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
    ","               {
                         Token token = new Token(sym.COMMA);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }
                
    {digits}          {
                         Token token = new Token(sym.DIGITS);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }

    {identifier}      {
                         Token token = new Token(sym.IDENTIFIER);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext());
                         return token;
                      }   

    {literalString}   {
                         Token token = new Token(sym.LITERAL_STRING);
                         token.setLine(yyline + 1);
                         token.setColumn(yycolumn + 1);
                         token.setLexema(yytext().substring(1, yytext().length() -1));
                         return token;
                      }

    {error}
                      {                                               
                          LexicalError error = new LexicalError ();
                          error.setLine(yyline + 1);
                          error.setColumn(yycolumn + 1);
                          error.setLexema(yytext());
                          lexicalErrorManager.lexicalError(error);
                        }

}


                         



