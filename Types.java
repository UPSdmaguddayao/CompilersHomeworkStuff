package symtable;

import minijava.node.*;

/**
 * This class provides some static constants and methods for dealing
 * with type information.
 * 
 * @author Brad Richards
 */

public class Types {
   /** The canonical form for boolean type names */
   public static final String BOOL = "boolean";
   /** The canonical form for int type names */
   public static final String INT = "int";
   /** The canonical form for int array type names */
   public static final String INTARRAY = "int[]";
  
   /**
    * Converts a type descriptor from our AST to its string representation.
    * @param type  A PType node from the AST
    * @return  The corresponding type string
    */
   public static String toStr(PType type) {
      if (type instanceof ABoolType)
         return BOOL;
      else if (type instanceof AIntType)
         return INT;
      else if (type instanceof AIntArrayType)
         return INTARRAY;
      else if (type instanceof AUserType) 
         return ((AUserType)type).getId().getText();
      else
         return "unknown";
   }
      
   /**
    * Returns true if the two type descriptors have the same type.  It
    * converts both to their string representations and compares the
    * two strings.
    * @param t1  The first type descriptor
    * @param t2  The second type descriptor
    * @return  True if the types are the same, false otherwise.
    */
   public static boolean sameType(PType t1, PType t2) {
      return (toStr(t1).equals(toStr(t2)));
   }
}
