package Tree;

/**
 * A SEQ statement composes two IRT statements, with the expectation
 * that we should execute the left statement, then the right.
 */
public class SEQ extends Stm {
   public Stm left, right;
   /**
    * The constructor composes the two input statements into a SEQ node.
    * @param l  The first Stm
    * @param r  The second Stm
    */
   public SEQ(Stm l, Stm r) { left=l; right=r; }
   public ExpList kids() {throw new Error("kids() not applicable to SEQ");}
   public Stm build(ExpList kids) {throw new Error("build() not applicable to SEQ");}
   public Stm link = null;
   public Stm next() { return link; }
}

