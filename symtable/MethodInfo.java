package symtable; 

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;

import minijava.node.PMethod;
import minijava.node.AMethod;
import minijava.node.PFormal;
import minijava.node.AFormal;
import minijava.node.PType;
import minijava.node.PVarDecl;
import minijava.node.TId;
import Mips.InFrame;

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
   private final int FORMAL_MAX = 4;
          
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
                     LinkedList<PVarDecl> locals) throws VarClashException 
  { //formals are parameters.  Locals are local variables in the methods
			int offset = 8;
      this.retType = retType;
      this.name = name;
      this.formals = new VarTable();
      formalCheck = new HashMap<String,Integer>();
      AFormal temp;
      String formalName;
      if (frmls.size() > FORMAL_MAX){ System.err.println("You can't have more than four arguments for a method!");}
      while(frmls.size() != 0) //putting formals in first
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
      this.locals = new VarTable(locals);
    
    //Begin setting formals
    Set<String> fSet = this.formals.getVarNames();
    for(String v : fSet){
    // find the pair which matches this arg
    //set its access
    this.formals.getInfo(v).setAccess(new InFrame(offset));
    offset += 4;
    } 
    
    //Set the Accessors for LOCALS
    Set<String> lSet = this.locals.getVarNames();
    for(String v : lSet){
    // find the pair which matches this arg
    //set its access
    this.locals.getInfo(v).setAccess(new InFrame(offset));
    offset += 4;
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
	  System.out.print(name.toString()+" (");
	  formals.dump();
	  System.out.println(" ) : " + Types.toStr(retType));
      locals.dump();  //local variables will dump
    }
   
   public void dumpIRT(boolean dot) {
      //TODO Fill in the guts of this method -- but once we get to the IRT checkpoint
	  
   }
}
