package symtable;

import minijava.analysis.DepthFirstAdapter;
import minijava.node.ABaseClassDecl;
import minijava.node.AMainClassDecl;
import minijava.node.ASubClassDecl;

/** 
 * This visitor class builds a symbol table as it traverses the tree.  The
 * table, an instance of ClassTable, can be returned via getTable().
 * @author Brad Richards
 */

public class SymTableVisitor extends DepthFirstAdapter
{
   private ClassTable table = new ClassTable();
   
   /** getTable returns the entire table */
   public ClassTable getTable() {
      return table;
   } 

   /** 
    * Figure out which visitor methods to override...
    */
}
