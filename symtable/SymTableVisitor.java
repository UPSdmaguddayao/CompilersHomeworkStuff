package symtable;

import minijava.analysis.DepthFirstAdapter;
import minijava.node.*;
import minijava.node.ABaseClassDecl;
import minijava.node.AMainClassDecl;
import minijava.node.ASubClassDecl;

import java.io.PrintWriter;
import java.util.*;

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
      indent(); out.print("class ");
      depth++;
      if(node.getId() != null)
      {
         out.print(node.getId().getText());
      }
      out.println(" {");
      indent(); out.println("public static void main(String[] bogus) {");
      if(node.getStmt() != null)
      {
         node.getStmt().apply(this);
      }
      indent(); out.println("}");
      depth--;
      indent(); out.println("}\n");
      outAMainClassDecl(node);
   }
   
   
   public void caseABaseClassDecl(ABaseClassDecl node)
   {
      inABaseClassDecl(node);
      
      indent(); out.print("class ");
      if(node.getId() != null)
      {
         out.print(node.getId().getText());
      }
      out.println(" {");
      {
         List<PVarDecl> copy = new ArrayList<PVarDecl>(node.getVarDecl());
         for(PVarDecl e : copy)
         {
            e.apply(this);
         }
      }
      {
         List<PMethod> copy = new ArrayList<PMethod>(node.getMethod());
         for(PMethod e : copy)
         {
            e.apply(this);
         }
      }
      indent(); out.println("}\n");
      outABaseClassDecl(node);
   }
   
   
   public void caseASubClassDecl(ASubClassDecl node)
   {
      inASubClassDecl(node);
      out.print("class ");
      if(node.getId() != null)
      {
         out.print(node.getId().getText());
      }
      out.print(" extends ");
      if(node.getExtends() != null)
      {
         node.getExtends().apply(this);
         out.print(node.getExtends().getText());
      }
      out.println(" {");
      {
         List<PVarDecl> copy = new ArrayList<PVarDecl>(node.getVarDecl());
         for(PVarDecl e : copy)
         {
            e.apply(this);
         }
      }
      {
         List<PMethod> copy = new ArrayList<PMethod>(node.getMethod());
         for(PMethod e : copy)
         {
            e.apply(this);
         }
      }
      out.println("}\n");
      outASubClassDecl(node);
   }
   
   
   public void caseAVarDecl(AVarDecl node)
   {
      inAVarDecl(node);
      indent();
      if(node.getType() != null)
      {
         node.getType().apply(this);
      }
      out.print(" ");
      if(node.getId() != null)
      {
         out.print(node.getId().getText());
      }
      out.println(";");
      outAVarDecl(node);
   }
   
   
   public void caseAMethod(AMethod node)
   {
      inAMethod(node);
      out.println();
      indent(); out.print("public ");
      if(node.getType() != null)
      {
         node.getType().apply(this);
      }
      out.print(" ");
      if(node.getId() != null)
      {
         out.print(node.getId().getText());
      }
      out.print("(");
      {
         List<PFormal> copy = new ArrayList<PFormal>(node.getFormal());
         int len = copy.size();
         for(int i=0; i<len-1; i++) {
            copy.get(i).apply(this);
            out.print(", ");
         }
         if (len > 0)
            copy.get(len-1).apply(this);
      }
      out.println(") {");
      {
         List<PVarDecl> copy = new ArrayList<PVarDecl>(node.getVarDecl());
         for(PVarDecl e : copy)
         {
            e.apply(this);
         }
      }
      {
         List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
         for(PStmt e : copy)
         {
            e.apply(this);
         }
      }
      indent(); out.println("}");
      outAMethod(node);
   }
}
