package Tree;

/**
 * EXPR nodes "make statements out of expressions".  It allows us
 * to evaluate an expression (presumably for its side effects), and
 * discard the result. 
 */
public class EXPR extends Stm {
   public Exp exp; 
   /**
    * The constructor takes a single expression, and embeds it within
    * an EXPR node.
    * @param e  An expression
    */
   public EXPR(Exp e) {exp=e;}
   public ExpList kids() {return new ExpList(exp,null);}
   public Stm build(ExpList kids) {
      return new EXPR(kids.head);
   }
   public Stm link = null;
   public Stm next() { return link; }
}

