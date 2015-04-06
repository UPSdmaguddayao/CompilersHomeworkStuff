// Initial (class level) register allocation (the stack stuff would take place here I think.
// THIS IS PROBABLY where we should keep track of some kind of offset for all our tables to use
package symtable;

import minijava.analysis.DepthFirstAdapter;
import minijava.node.ABaseClassDecl;
import minijava.node.AMainClassDecl;
import minijava.node.ASubClassDecl;

import java.io.PrintWriter;
import java.util.*;
import java.io.File;

/** 
 * This visitor class builds a symbol table as it traverses the tree.  The
 * table, an instance of ClassTable, can be returned via getTable().
 * @author Brad Richards
 */

public class SymTableVisitor extends DepthFirstAdapter
{
   private ClassTable table = new ClassTable();
   
   private int depth = -2;   // Start at -2 so Program->ClassDecl puts back to 0
   private PrintWriter out;
   
   private void indent() {
      for (int i = 0; i < depth; i++) out.write("   ");
   }
   
   /** Constructor takes a PrintWriter, and stores in instance var. */
   public SymTableVisitor() {
	   System.out.println("SymTableVisitor constructor");
      this.out = new PrintWriter(System.out);
   }
   
   /** getTable returns the entire table */
   public ClassTable getTable() {
      return table;
   } 

   /** 
    * Figure out which visitor methods to override...
    */
   public void caseAMainClassDecl(AMainClassDecl node)
   {
	   System.out.println("Main");
      inAMainClassDecl(node);
      depth++;
      if(node.getId() != null)
      {  
         try
         {
         table.put(node.getId(),null,node.getVarDecl(), node.getMethod());
         }
         catch (ClassClashException e)
         {
            System.err.println(e);
            System.exit(0);
         }
      }
      depth--;
      outAMainClassDecl(node);
   }
   
   
   public void caseABaseClassDecl(ABaseClassDecl node)
   {
	   System.out.println("Base");
      inABaseClassDecl(node);
      if(node.getId() != null)
      {
         try
         {
            table.put(node.getId(), null, node.getVarDecl(), node.getMethod());
         }
         catch (Exception e)
         {
            System.err.println(e);
            System.exit(0);
         }
      }
      outABaseClassDecl(node);
   }
   
   
   public void caseASubClassDecl(ASubClassDecl node)
   {
	   System.out.println("Sub");
      inASubClassDecl(node);
      if(node.getId() != null)
      {
      try{
            table.put(node.getId(),node.getExtends(), node.getVarDecl(), node.getMethod());
         }
         catch (Exception e)
         {
            System.err.println(e);
            System.exit(0);
         }
      }
      outASubClassDecl(node);
   }
   
   
}
