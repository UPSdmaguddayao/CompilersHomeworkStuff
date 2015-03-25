package Tree;

/**
 * The BINOP expression represents all of the binary operators in 
 * our IRT language:  +, -, *, /, &&, ||, shifts, and xor.  It defines
 * constants for each of these specific operators.
 */
public class BINOP extends Exp {
   public int binop;
   public Exp left, right;
   /**
    * The constructor takes a constant specifying which operator is being
    * applied, and left and right subexpressions (IRT trees).
    * @param b  One of the operator constants
    * @param l  The left subexpression (IRT tree)
    * @param r  The right subexpression (IRT tree)
    */
   public BINOP(int b, Exp l, Exp r) {
      binop=b; left=l; right=r; 
   }
   /**
    * Constants for representing the kind of binary operator.
    */
   public final static int PLUS=0, MINUS=1, MUL=2, DIV=3, 
      AND=4,OR=5,LSHIFT=6,RSHIFT=7,ARSHIFT=8,XOR=9;
   public ExpList kids() {return new ExpList(left, new ExpList(right,null));}
   public Exp build(ExpList kids) {
      return new BINOP(binop,kids.head,kids.tail.head);
   }
}

