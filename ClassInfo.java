package symtable;

import java.util.LinkedList;

import minijava.node.PMethod;
import minijava.node.PVarDecl;
import minijava.node.TId;

//import Mips.MipsArch;  // These two are needed for the IRT phase
//import Arch.*;

/** 
 * A ClassInfo instance records infofmation about a single class.  It stores 
 * the name of its superclass (or null if there isn't one), a VarTable containing 
 * the class's instance variables, and a MethodTable containing information on 
 * the methods in the class, in addition to the name of the class itself.
 * 
 * @author Brad Richards
 */
 
public class ClassInfo {
   TId className;         // TId holding our name, line number, etc.
   TId superClass;        // Our superclass, if we have one
   VarTable vars;         // A VarTable holding info on all instance vars
   MethodTable methods;   // Table of info on methods

/*
   We'll add these once we get to the IRT phase.  The IRTinfo object records
   the total number of words required for the instance variables in a class
   (including those we inherit).  
   
   ClassIRTinfo info;
   public ClassIRTinfo getIRTinfo() { return info; }
   public void setIRTinfo(ClassIRTinfo i) { info = i; }
*/
  
  
   /** 
    * The constructor takes all info associated with a subclass definition,
    * but can be passed null for unused fields in the case of a base or main
    * class declaration.  Names are passed as TId rather than String so we 
    * can retrieve line number, etc, from the token if necessary.
    * @param className  The name of the class
    * @param superClass The name of its superclass
    * @param vars       A list of all instance vars in the class
    * @param methods    A list of method descriptors
    */
   public ClassInfo(TId className, TId superClass,
                    LinkedList<PVarDecl> vars,
                    LinkedList<PMethod> methods) throws Exception { 
      this.className = className;
      this.superClass = superClass;
      this.vars = new VarTable(vars);           // Populate table from list
      this.methods = new MethodTable(methods);  // Ditto.
   }
   
   public TId getName() { return className; }
   public TId getSuper() { return superClass; }
   public VarTable getVarTable() { return vars; }
   public MethodTable getMethodTable() { return methods; }
   
   public void dump() {
		// TODO:  Fill in the guts here
   } 
   
   public void dumpIRT(boolean dot) {
		// TODO:  You'll complete this one on the next checkpoint
   } 
}
