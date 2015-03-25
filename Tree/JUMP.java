package Tree;
import Arch.Label;
import Arch.LabelList;

/**
 * JUMP statements unconditionally transfer control to an address in
 * the program.  We'll typically use the one-argument constructor,
 * which takes a Label statement as its argument and uses that label
 * as the destination of the jump.  
 */
public class JUMP extends Stm {
   public Exp exp;
   public LabelList targets;
   
   /**
    * The two-argument constructor takes an expression and a list of 
    * possible destinations.  The expression determines which of the
    * destinations we actually select, as in a switch statement.
    * @param e  An expression used to select a destination
    * @param t  A list of possible destinations
    */
   public JUMP(Exp e, LabelList t) {exp=e; targets=t;}
   
   /**
    * The one-argument constructor builds a JUMP node that has only a
    * single destination, and represents an unconditional jump to that
    * label.
    * @param target  A label describing the destination
    */
   public JUMP(Label target) {
      this(new NAME(target), new LabelList(target,null));
   }
   public ExpList kids() {return new ExpList(exp,null);}
   public Stm build(ExpList kids) {
      return new JUMP(kids.head,targets);
   }
   public Stm link = null;
   public Stm next() { return link; }
}

