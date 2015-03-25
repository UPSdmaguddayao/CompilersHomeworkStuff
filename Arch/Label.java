package Arch;

/**
 * A Label represents an assembly-language address.
 */

public class Label  {
   protected String name;
   protected static int count;
   
   /**
    * a printable representation of the label, for use in assembly 
    * language output.
    */
   public String toString() { return name; }
   
   /**
    * Makes a new label that prints as "name".
    * Warning: avoid repeated calls to <tt>new Label(s)</tt> with
    * the same name <tt>s</tt>.
    */
   public Label(String n) {
      name=n;
   }
   
   /**
    * Makes a new label with an arbitrary name.
    */
   public Label() {
      this("L" + count++);
   }
   
}
