package Tree;

/** 
 * A CONST expression represents a signed integer constant.
 */
public class CONST extends Exp {
   public int value;
   /**
    * The constructor takes the integer value to be used as constant.
    * @param v  The constant value.
    */
   public CONST(int v) {value=v;}
   public ExpList kids() {return null;}
   public Exp build(ExpList kids) {return this;}
}

