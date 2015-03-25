package Tree;

/**
 * A COMMENT statement allows us to build comments into the IRT.  These
 * nodes are technically statements, but have no executable content.  The
 * text of the comment can be displayed during printing of the IRT, and 
 * can also be inserted as a comment into the assembly code.  Trust me,
 * this will make it <i>much</i> easier to understand and debug both.
 */
public class COMMENT extends Stm { 
   public String text;
   /**
    * The constructor takes a Java String to be used as a comment.
    * @param s  The comment.
    */
   public COMMENT(String s) {text=s;}
   public ExpList kids() {return null;}
   public Stm build(ExpList kids) {
      return this;
   }
   public Stm link = null;
   public Stm next() { return link; }
}

