package Mips;

import Tree.BINOP;
import Tree.CONST;
import Tree.Exp;
import Tree.MEM;

/**
 * The InFrame subclass of Access describes how to get at items on the
 * MIPS stack.  If we're not given a base address, we just generate a
 * constant corresponding to the offset, otherwise we add the offset to
 * a base address to compute a new memory address.
 */

public class InFrame extends Arch.Access
{
   private int offset;
   public InFrame(int offset)
   {
      this.offset = offset;
   }
   
   public int getOffset() { return offset; }
   public String toString() { return ""+offset; }
   
   /**
    * Construct a simple IRT tree consisting of the offset used to get
    * to this item on the stack.
    */
   public Exp getTree() { return new CONST(offset); }
   
   /**
    * This version of getTree is passed a register holding a base
    * address, and builds a MEM construct that refers to this item on
    * the stack (or heap).  It adds the appropriate offset to the
    * base address, and wraps the corresponding memory address in a
    * MEM node.
    */
   public Exp getTree(Exp base) 
   {
      return new MEM(new BINOP(BINOP.PLUS, base, new CONST(offset)));
   }
}
