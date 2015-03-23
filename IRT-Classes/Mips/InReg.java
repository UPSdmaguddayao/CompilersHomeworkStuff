package Mips;

import Arch.Reg;
import Tree.Exp;
import Tree.REG;

/**
 * This subclass of Access describes how to get at items in MIPS
 * registers.  We have to support both flavors of getTree accessor,
 * though they both do exactly the same thing --- return an IRT
 * expression that describes which register the data's in.
 */

public class InReg extends Arch.Access
{
   Reg temp;  // The register we're stored in
   
   /**
    * The constructor takes the Arch.Reg register that corresponds to
    * the position of this data item.
    * @param t  The actual Arch.Reg that's holding this item
    */
   public InReg(Reg t) { temp = t; }
   public String toString() { return temp.toString();}
   public Reg getReg() { return temp;}
   
   /**
    * Generate the IRT tree that accesses this item.
    */
   public Exp getTree() { return new REG(temp);}
   
   /**
    * Generate the IRT tree that accesses this item.  Same as
    * <code>getTree()</code> since we're not at an offset from
    * any base address &mdash; we're in a register.
    */
   public Exp getTree(Exp base) { return new REG(temp);}
}
