package Tree;

/** 
 * A data structure for implementing a list of Stms.
 */

public class StmList {
   public Stm head;
   public StmList tail;
   /**
    * The constructor assembles a list of Stm nodes by concatenating
    * a single Stm to an existing list of Stms.
    * @param h  The new head Stm
    * @param t  An existing list of Stms
    */
   public StmList(Stm h, StmList t) {head=h; tail=t;}
}



