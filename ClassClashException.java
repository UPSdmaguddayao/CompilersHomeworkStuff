package symtable;

/**
 * A ClassClashException is thrown when two classes in a given program
 * are found to have the same name.
 * @author Brad Richards
 */
@SuppressWarnings("serial")
public class ClassClashException extends java.lang.Exception {
   public ClassClashException(String msg) {
      super(msg);
   }
}
