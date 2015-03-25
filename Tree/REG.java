package Tree;
import Arch.Reg;

/**
 * A REG expression represents the value stored in a particular register.
 */
public class REG extends Exp {
   public Reg reg;
   /**
    * The constructor creates a REG node out of an Arch.Reg instance.
    * The Reg class in Arch gives us methods for generating MIPS-specific
    * register names.  These are Java Strings, and need to be wrapped in 
    * a REG node to make them part of an IRT tree.
    * @param r  The Arch.Reg that corresponds to this REG node
    */
   public REG(Reg r) {reg = r;}
   public ExpList kids() {return null;}
   public Exp build(ExpList kids) {return this;}
}

