package symtable; 

import java.util.LinkedList;
import java.util.HashMap;

import minijava.node.AFormal;
import minijava.node.PFormal;
import minijava.node.PType;
import minijava.node.PVarDecl;
import minijava.node.TId;

/** 
 * A MethodInfo instance records information about a single MiniJava method.
 * It contains references to the method's return type, formal parameters, and
 * its local variables, in addition to the method's name.  
 * @author Brad Richards
 */
public class MethodInfo {
   // ClassInfo parent;
   private PType retType;
   private TId name;
   private VarTable formals;
   private VarTable locals;     // Contains entries for parameters as well
   private ClassInfo enclosing; // The class in which method is actually defined
          
  private HashMap<String,Integer> formalCheck;  
   /*
    * Stuff we'll add for the IRT phase
    *
   MethodIRTinfo info;
   public MethodIRTinfo getInfo() { return info; }
   public void setInfo(MethodIRTinfo i) { info = i; }
    */ 
  
   /** 
    * The constructor stores away references to the return type and formals,
    * and builds a VarTable containing both the local variables and the 
    * formals.  If variable name clashes are found (within locals, formals,
    * or across locals and formals) we throw a VarClashException.
    * @param retType  The method's return type
    * @param name     The method's name (a TId, not a String)
    * @param formals  A list of the method's formal variables (params)
    * @param locals   A list of the method's local variables
    */
   public MethodInfo(PType retType, TId name,
                     LinkedList<PFormal> frmls,
                     LinkedList<PVarDecl> locals) throws VarClashException {
						 
      this.retType = retType;
      this.name = name;
      this.formals = new VarTable();
      this.locals = new VarTable(locals);
      formalCheck = new HashMap<String,Integer>();
      AFormal temp;
      String formalName;
      while(frmls.size() != 0)
      {
        temp = (AFormal)frmls.pop();
        formalName = temp.getId().getText();
        if(formalCheck.containsKey(formalName) || this.locals.inside(formalName))
        {
          String msg = formalName + " redeclared on line " + temp.getId().getLine();
          throw new VarClashException(msg);
        }
        else
        {
          formalCheck.put(formalName,0);
		  formals.put(temp.getId(), temp.getType());
        }
      }
      
   }

   /* Accessors */   
   public TId getName() { return name; }
   public PType getRetType() { return retType; }
   public VarTable getFormals() { return formals; }
   public VarTable getLocals() { return locals; }
   
   /** Print info about the return type, formals, and local variables.
    * It's OK if the formals appear in the local table as well.  In fact,
    * it's a <i>good</i> thing since this output will help us debug later if 
    * necessary, and we'll want to see exactly what's in the VarTable.
    */
   public void dump() {
      /*System.out.print(name.toString()+" (");
      for(int i = 0; i < formals.size(); i++)
      {
        AFormal frm = (AFormal) formals.get(i);
        //prints out the formals <---Note, pay attention to AFormal, not PFormal.  It has a toString Method
        System.out.print(" "+frm.toString() + ":"+ frm.getType());
      }
      System.out.println(" ) : " + Types.toStr(retType));  */
	  System.out.print(name.toString()+" (");
	  formals.dump();
	  System.out.println(" ) : " + Types.toStr(retType));
      locals.dump();  //local variables will dump
    }
   
   public void dumpIRT(boolean dot) {
      //TODO Fill in the guts of this method -- but once we get to the IRT checkpoint
   }
}
