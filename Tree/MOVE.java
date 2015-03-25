package Tree;

/** 
 * A MOVE statement is essentially an assignment statement.  The
 * first expression represents the destination, which can be a MEM or
 * REGister, and the second is the expression whose value should 
 * be stored there (which could be a constant, MEM, or other Exp).
 */
public class MOVE extends Stm {
   public Exp dst, src;
   /**
    * The constructor builds a MOVE node that represents to transfer of
    * a data value from source (s) to destination (d).
    * @param d  An expression describing the destination
    * @param s  An expression describing the source
    */
   public MOVE(Exp d, Exp s) {dst=d; src=s;}
   public ExpList kids() {
      if (dst instanceof MEM)
         return new ExpList(((MEM)dst).exp, new ExpList(src,null));
      else return new ExpList(src,null);
   }
   public Stm build(ExpList kids) {
      if (dst instanceof MEM)
         return new MOVE(new MEM(kids.head), kids.tail.head);
      else return new MOVE(dst, kids.head);
   }
   public Stm link = null;
   public Stm next() { return link; }
}

