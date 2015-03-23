package Mips;
import Arch.Arch;
import Arch.Reg;

/**
 * This class defines some constants that correspond to specific
 * registers on the MIPS (e.g. <code>$sp</code>), as well as defining
 * groups of registers:  Those used for passing arguments, and the 
 * callee-saved and caller-saved registers.  For reference, A MIPS frame 
 * allocates space for
 * <ol>
 * <li> the return address, <code>$ra</code></li>
 * <li> the static link (<code>this</code>)</li>
 * <li> up to four parameters <code>$a0-$a3</code></li>
 * <li> local variables as necessary</li>
 * <li> storage space for callee-saved registers </li>
 * </ol>
 */

public class MipsArch extends Arch {
   public static final Reg ZERO = new Reg("$zero"); // zero reg
   public static final Reg V0 = new Reg("$v0");     // function result
   public static final Reg SL = new Reg("$gp");     // caller-saved, static link
   public static final Reg SP = new Reg("$sp");     // stack pointer
   public static final Reg FP = new Reg("$fp");     // callee-save (frame pointer)
   public static final Reg RA = new Reg("$ra");     // return address
   public static final Reg[] ARGS = {
      new Reg("$a0"), new Reg("$a1"), new Reg("$a2"), new Reg("$a3") };
   public static final Reg[] CALLEE = {
      new Reg("$s0"), new Reg("$s1"), new Reg("$s2"), new Reg("$s3"), new Reg("$s4"), 
      new Reg("$s5"), new Reg("$s6"), new Reg("$s7") };
   public static final Reg[] CALLER = {
      new Reg("$t0"), new Reg("$t1"), new Reg("$t2"), new Reg("$t3"), new Reg("$t4"), 
      new Reg("$t5"), new Reg("$t6"), new Reg("$t7"), new Reg("$t8") };
   
   /**
    * Accessors give another way to get at these constants.
    */
   public Reg FP() { return FP;}
   public Reg SP() { return SP;}
   public Reg RA() { return RA;}
   public Reg RV() { return V0;}
   public Reg SL() { return SL;}
   public Reg[] args() { return ARGS; }
   public Reg[] calleeSaved() { return CALLEE; }
   public Reg[] callerSaved() { return CALLER; }
   
   public int wordSize() { return 4; }
}