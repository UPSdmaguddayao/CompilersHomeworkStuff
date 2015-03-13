package symtable;

/**
 * A MethodClassException is thrown when two methods in a given program
 * are found to have the same name, even if their signatures differ.
 * @author Brad Richards
 */
@SuppressWarnings("serial")
public class MethodClashException extends java.lang.Exception {
   public MethodClashException(String msg) {
      super(msg);
   }
}
