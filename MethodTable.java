package symtable; 

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;

import minijava.node.AMethod;
import minijava.node.PFormal;
import minijava.node.PMethod;
import minijava.node.PType;
import minijava.node.PVarDecl;
import minijava.node.TId;

/** 
 * This class maintains information on a <i>collection</i> of methods.  It
 * maps method names to MethodInfo records.
 * @author Brad Richards
 */
public class MethodTable {
   private HashMap<String, MethodInfo> table = new HashMap<String, MethodInfo>();
   
   /** 
    * The constructor is passed a list of PMethod nodes as constructed
    * by the parser.  It adds entries for each method in the list via 
    * the local put() method. 
    * @param methods A list of PMethod nodes
    */
   public MethodTable(LinkedList<PMethod> methods) throws Exception {
    //Check AMethod
      String name = "";
      PMethod temp = null;
      for(int i = 0; i < methods.size(); i++)
      {
        temp = methods.get(i);
        put(temp.getId(),temp.getType(),temp.getFormal(),temp.getVarDecl()); //let errors be handled by the put method
      }
   }
   
   /** 
    * This method adds a single entry to the table, with the method name as 
    * key and the appropriate MethodInfo structure as value.  If the method 
    * name already appears in the table, it should throw a MethodClashException.  
    * We might also encounter a VarClashException while building the MethodInfo 
    * structure, so either could be thrown by put().
    * @param id   The method's name (a TId, not a String)
    * @param retType  The method's return type
    * @param formals  A list of the method's formal variables (params)
    * @param locals   A list of the method's local variables
    */
   public void put(TId id, PType retType, 
                   LinkedList<PFormal> formals,
                   LinkedList<PVarDecl> locals) throws Exception {
      String name = id.getText();
       if (table.containsKey(name)) {
        String msg = name + " redeclared on line " + id.getLine();
        throw new MethodClashException(msg); // There was a clash
      }
      table.put(name, new MethodInfo(retType,name,formals,locals)); //this is where VarClashExceptions will occur
   }
   
   /** Lookup and return the MethodInfo for the specified method */
   public MethodInfo get(String name) {
      return table.get(name);
   }
      
   /** Return all method names in the table */
   public Set<String> getMethodNames() {
      return table.keySet();
   }
   
   /** 
    * Print out info on all methods in the table.  Don't forget that
    * MethodInfo structures already know how to dump themselves.
    */
   public void dump() {
      //TODO Fill in the guts of this method.
      for(Map.Entry<String,ClassInfo> entry: table.entrySet())
      {
         entry.getValue().dump()
      }
   }
   
   public void dumpIRT(boolean dot) {
      //TODO Fill in the guts of this method -- but not until IRT checkpoint
   }
}
