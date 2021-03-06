package symtable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;

import minijava.node.PMethod;
import minijava.node.PVarDecl;
import minijava.node.TId;

/** 
 * A ClassTable records information about a COLLECTION of class definitions.
 */
public class ClassTable {
   HashMap<String, ClassInfo> table = new HashMap<String, ClassInfo>();
   
   /** 
    * This method adds a new table entry for class "id".  It will throw a
    * ClassClashException if the new class name is already in the table,
    * and might also pass along Var or Method clash exceptions encountered
    * while processing the lists of instance variables and methods.  (This
    * method doesn't inspect the lists, but the constructor for ClassInfo
    * does.)
    * 
    * @param id         The name of the class (a TId, not a String)
    * @param extendsId  The name of its superclass (or null)
    * @param vars       A list of the class's instance variables
    * @param methods    A list of the methods in the class
    */
   public void put(TId id, 
                   TId extendsId,
                   LinkedList<PVarDecl> vars,
                   LinkedList<PMethod> methods) throws Exception {
      String name = id.getText();
      //TODO Fill in the guts of this method.
       if (table.containsKey(name)) {
        String msg = name + " redeclared on line " + id.getLine();
        throw new ClassClashException(msg); // There was a clash
      }
      table.put(name, new ClassInfo(id,extendsId,vars,methods)); //creates a new classinfo and places it inside the table

   }
   
   public void putMain(String className, String methodName) throws Exception {
      
   }
   
   /** Lookup and return the ClassInfo record for the specified class */
   public ClassInfo get(String id) {
      return table.get(id);
   }
   
   /** Return all method names in the table */
   public Set<String> getClassNames() {
      return table.keySet();
   }
   
   /** dump prints info on each of the classes in the table */
   public void dump() {
    for(Map.Entry<String,ClassInfo> entry: table.entrySet())
      {
         entry.getValue().dump();
      }
   }
   
      
   /** dump prints info on each of the classes in the table and 
    * displays IRT info as well. 
    * @param dot	Are we generating output for dot?
    */
   public void dumpIRT(boolean dot) {
	  //System.out.println("ClassTable dumpIRT: ");
    ClassInfo check;
    ClassInfo superClass;
    for(Map.Entry<String,ClassInfo> entry: table.entrySet())
      {
        check = entry.getValue();
        if(check.getSuper()!= null)
        {
          superClass = get(check.getSuper().getText());
          entry.getValue().allocateInstanceOffsets(superClass.getIRTinfo().returnVars() * 4);
        }
         entry.getValue().dumpIRT(dot);
      }
   }
}
