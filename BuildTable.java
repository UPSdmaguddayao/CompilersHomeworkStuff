import java.io.InputStreamReader;
import java.io.PushbackReader;

import symtable.SymTableVisitor;
import minijava.lexer.Lexer;
import minijava.node.Start;
import minijava.parser.Parser;

/**
 * This driver code will instantiate a lexer and parser from the SableCC-
 * generated classes, feed them some sample input, then apply a
 * SymTableVisitor to the tree to build a symbol table.  After the
 * table's built, it dumps the table so we can see what's in it.
 * 
 * @author Brad Richards
 */
public class BuildTable {
   
   /**
    * The main method creates an instance of SableCC's Parser that's fed tokens
    * by an instance of SableCC's Lexer.  (The Lexer takes its input from 
    * System.in.)  The parser produces an AST, which is then traversed by the
    * PrintVisitor.
    */
   public static void main(String[] args) {
      Parser parser = new Parser(
                          new Lexer(
                              new PushbackReader(
                                  new InputStreamReader(System.in), 1024)));
      SymTableVisitor visitor = new SymTableVisitor();
      
      try {
         // Ask our parser object to do its thing.  Store the AST in start.
         Start start = parser.parse();
         
         // Retrieve the top-level Program node from start, and apply 
         // our symbol table visitor to it.
         start.getPProgram().apply(visitor);
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(-1);
      }
      
      // The visitor created a symbol table for us, and that table has a
      // dump() method to display its contents.  Time to see what we got...
      visitor.getTable().dump();
	  
	  System.out.println("\n\n*************  IRT  **************\n\n");
	  //Then show the IRT
      visitor.getTable().dumpIRT(true);
System.out.println("It ended!");
   }
}
