package Arch;

/**
 * The Reg class generates assembly language registers
 * for use in IRT expressions.  Each register created is uniquely
 * numbered, though they can be given names instead if desired.
 */

public class Reg  {
   public static int min = 8;
   public static int max = 25;
   protected static int count = min;  // How many have been created?
   protected int num;                 // This register's number
   protected String name;             // A specific name, if one's been given
   
   /** 
    * toString returns the register's name, if it was given one, otherwise
    * it returns a generic string describing the register by number.
    */
   public String toString() {
      if (name == null)
         return "$" + num;
      else
         return name;
   }
   
   /** Create a numbered register */
   public Reg() { 
      num=count++; 
      if (num > max)
         System.err.println("Warning: exceeded number of actual MIPS registers");
   }
   
   /** Create a named register */
   public Reg(String name) {
      this.name = name;
   }
   
   /** Reset the numbering scheme.  Next register allocated
    * will be 0 again.
    */
   public static void reset() { count = min; } // Use with care

   /** Returns true if the argument is a register with the same
    * name as this one.
    */
   public boolean equals(Object o) {
      return (o instanceof Reg && o.toString().equals(this.toString()));
   }
}