package Tree;

/**
 * A MEM expression represents the <i>contents</i> of a word of memory
 * at the specified address.  A MEM expression can be used on either
 * the left-hand side or the right-hand side of a MOVE statement, and
 * can means a read from this location or a write to it depending upon
 * context.
 */
public class MEM extends Exp {
   public Exp exp;
   /**
    * The constructor expects an expression that describes the address
    * of the memory item being specified.
    * @param e  An expression to be used as a memory address
    */
   public MEM(Exp e) {exp=e;}
   public ExpList kids() {return new ExpList(exp,null);}
   public Exp build(ExpList kids) {
      return new MEM(kids.head);
   }
}

