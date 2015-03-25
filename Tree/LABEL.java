package Tree;
import Arch.Label;

/**
 * A LABEL statement attaches a label to a particular point in the 
 * IRT program.  This label can then be used as the target for a
 * jump or call.
 */
public class LABEL extends Stm { 
   public Label label;
   
   /**
    * The constructor creates a LABEL IRT node out of an Arch.Label
    * instance.  The Label class in Arch gives us methods for generating
    * unique labels of the form L1, L2, etc.  These are essentially Java
    * Strings, and need to be wrapped in a LABEL node to make them part
    * of an IRT tree.
    * @param l  The Arch.Label to be used as this LABEL's name
    */
   public LABEL(Label l) {label=l;}
   public ExpList kids() {return null;}
   public Stm build(ExpList kids) {
      return this;
   }
   public Stm link = null;
   public Stm next() { return link; }
}

