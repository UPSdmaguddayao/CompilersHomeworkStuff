package Tree;
import Arch.Label;

/**
 * NAME expression are similar to LABELs in that they represent a location
 * within an IRT program, but we use NAMEs for procedures and LABELs for
 * jumps within a procedure.  This distinction is useful, since in one case
 * we need to store a return address in a register, and in the other we don't.
 */
public class NAME extends Exp {
   public Label label;
   /**
    * Builds a NAME node representing a procedure whose name is the Label
    * passed as argument.
    * @param l  The Label to be used as the name of this procedure
    */
   public NAME(Label l) {label=l;}
   public ExpList kids() {return null;}
   public Exp build(ExpList kids) {return this;}
}

