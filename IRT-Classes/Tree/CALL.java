package Tree;

/**
 * Call nodes represent the invocation of a method.  It's an Exp since 
 * the method may have a return value.  No information on the "view shift"
 * or stack layout is recorded here &mdash; its sole purpose is to identify
 * the assembly routine being called and the arguments that are passed.
 */
public class CALL extends Exp {
   public Exp func;
   public ExpList args;
   /**
    * The constructor expects an expression representing the procedure to
    * be called (a NAME node, typically), as well as a list of expressions
    * to be used as arguments.
    * @param f  The procedure to be called.
    * @param a  A list of argument expressions
    */
   public CALL(Exp f, ExpList a) {func=f; args=a;}
   public ExpList kids() {return new ExpList(func,args);}
   public Exp build(ExpList kids) {
      return new CALL(kids.head,kids.tail);
   }
   
}

