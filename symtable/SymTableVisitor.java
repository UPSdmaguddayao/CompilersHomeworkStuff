// Initial (class level) register allocation (the stack stuff would take place here I think.
// After doing this, we would know where to start out offsets?
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
      inAMainClassDecl(node);
      depth++;
      if(node.getId() != null)
      {  
       /*  try
         {
         table.put(node.getId(),null,node.getVarDecl(), node.getMethod());
         }
         catch (ClassClashException e)
         {
            System.err.println("FUCKED UP");
         }*/
      }
      depth--;
      outAMainClassDecl(node);
   }
   
   
   public void caseABaseClassDecl(ABaseClassDecl node)
   {
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
