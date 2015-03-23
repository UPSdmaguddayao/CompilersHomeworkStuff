package Arch;

/**
 * Arch classes describe the details of a specific architecture.
 * This abstract base class specifies the functionality that all such
 * architecture-specific classes must have, incuding symbolic names
 * for specific registers, arrays of caller- and callee-saved
 * registers on the specific machine, and the names of the argument
 * registers.
 */

public abstract class Arch
{              
   /** Symbolic name for the frame pointer register */
   abstract public Reg FP();
   
   /** Symbolic name for the stack pointer register */
   abstract public Reg SP();
   
   /** Symbolic name for the return address register */
   abstract public Reg RA();
   
   /** Symbolic name for the link ("this") register */
   abstract public Reg SL();
   
   /** Symbolic name for the return value register */
   abstract public Reg RV();
   
   /** Return an array of symbolic names for argument registers */
   abstract public Reg[] args();
   abstract public Reg[] calleeSaved();
   abstract public Reg[] callerSaved();
   
   /** Return the number of bytes in a word for an architecture */
   abstract public int wordSize();
}
