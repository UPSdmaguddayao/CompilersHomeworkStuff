package Tree;
import Arch.Label;

/**
 * The CJUMP statement represents all of the conditional jumps in
 * the IRT language.  As with BINOP, constants are used to describe
 * specific comparisons (EQ, NE, LT, etc).  The constructor takes one
 * of these constants, two expressions (the values being compared),
 * and labels describing the jump destinations for true and false 
 * outcomes.
 */
public class CJUMP extends Stm {
   public int relop;
   public Exp left, right;
   public Label iftrue, iffalse;
   /**
    * The constructor takes a constant describing the comparison used
    * in this conditional jump, expressions for the values being compared,
    * and two destination labels.
    * @param rel  The relational operator
    * @param l  The left expression
    * @param r  The right expression
    * @param t  The label to which we should jump if the test is true
    * @param f  The label to which we jump if the test is false
    */
   public CJUMP(int rel, Exp l, Exp r, Label t, Label f) {
      relop=rel; left=l; right=r; iftrue=t; iffalse=f;
   }
   /** 
    * Constants for representing the relational operator.
    */
   public final static int EQ=0, NE=1, LT=2, GT=3, LE=4, GE=5,
      ULT=6, ULE=7, UGT=8, UGE=9;
   public ExpList kids() {return new ExpList(left, new ExpList(right,null));}
   public Stm build(ExpList kids) {
      return new CJUMP(relop,kids.head,kids.tail.head,iftrue,iffalse);
   }
   public Stm link = null;
   public Stm next() { return link; }
   /**
    * A routine for inverting the relational operator.  For example,
    * the opposite of EQ is NE.  The opposite of GE (>=) is LT (<).
    * @param relop  A constant representing a relational operator
    * @return  A constant representing the opposite operator
    */
   public static int notRel(int relop) {
      switch (relop) {
         case EQ:  return NE;
         case NE:  return EQ;
         case LT:  return GE;
         case GE:  return LT;
         case GT:  return LE;
         case LE:  return GT;
         case ULT: return UGE;
         case UGE: return ULT;
         case UGT: return ULE;
         case ULE: return UGT;
         default: throw new Error("bad relop in CJUMP.notRel");
      }
   }
}

