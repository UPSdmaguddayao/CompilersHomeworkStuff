package Tree;

/**
 * A data structure for representing lists of Exps.
 */

public class ExpList {
   public Exp head;
   public ExpList tail;
   /**
    * The constructor creates a longer list from a single expression
    * and a list of expressions.
    * @param h  The expression to be put at the head of the list
    * @param t  An existing list to be used as the tail
    */
   public ExpList(Exp h, ExpList t) {head=h; tail=t;}
}



