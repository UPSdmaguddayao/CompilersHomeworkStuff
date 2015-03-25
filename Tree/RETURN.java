package Tree;

/**
 * This was added to the collection of IRT nodes so that we could
 * build fully executable programs in IRT.  It's used to identify
 * the returned expression so that the CALL knows where to find its
 * final value.
 */
public class RETURN extends Stm {
   public Exp ret;
   /**
    * The constructor builds a RETURN node containing the Exp to
    * use as the enclosing method's return value.
    * @param r  The return Exp
    */
   public RETURN(Exp r) {ret=r;}
   public ExpList kids() {return new ExpList(ret,null);}
   public Stm build(ExpList kids) {
      return new RETURN(kids.head);
   }
   public Stm link = null;
   public Stm next() { return link; }
}

