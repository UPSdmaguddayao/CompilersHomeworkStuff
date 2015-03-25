package Tree;

/**
 * ESEQ expressions represent a "statement + expression" sequence like
 * those in the straight-line language from the book.  The constructor
 * takes a statement and an expression, and it's understood that the
 * statement is to be executed first, then the expression is evaluated 
 * to produce the value of the overall expression.
 */
public class ESEQ extends Exp {
   public Stm stm;
   public Exp exp;
   /**
    * The constructor takes a single statement and a single expression.  Note
    * that the Stm could be a SEQ, thereby allowing multiple statements before
    * the expression to be evaluated.
    * @param s  A statement node
    * @param e  An expression to be evaluated after the statement(s) are executed
    */
   public ESEQ(Stm s, Exp e) {stm=s; exp=e;}
   public ExpList kids() {throw new Error("kids() not applicable to ESEQ");}
   public Exp build(ExpList kids) {throw new Error("build() not applicable to ESEQ");}
}

