package symtable;

import minijava.node.PType;
//import Arch.Access;

/** 
 * VarInfo records information about a single local variable.  We need
 * to know its type, for typechecking purposes, and we also store IRT
 * related info (the IRT code required to access it) -- or at least we
 * will once we get to the IRT checkpoint...
 */
public class VarInfo {
   private PType type;
   
   public VarInfo(PType t) { type = t; }
   public PType getType() { return type; }
   public String toString() { return Types.toStr(type); }
  
   /* 
    * Stuff to add once we got the the IRT phase

   private Access acc;
   public Access getAccess() { return acc; }
   public void setAccess(Access a) { acc = a; }
   */  
}
