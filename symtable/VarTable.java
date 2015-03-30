package symtable; 

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;

import minijava.node.AVarDecl;
import minijava.node.PType;
import minijava.node.PVarDecl;
import minijava.node.TId;

import Mips.InFrame;

import java.lang.String;

/** 
 * A VarTable records name and type information about a <i>collection</i> 
 * of variables.  An exception is thrown if we try to add a duplicate name.
 * @author Brad Richards
 */
public class VarTable {
   HashMap<String, VarInfo> table = new HashMap<String, VarInfo>();
   int offset;
   
   /** 
    * Constructor populates table from an initial list of VarDecls.
    * @param vars  A list of PVarDecl nodes from our AST.
     */
   public VarTable(LinkedList<PVarDecl> vars) throws VarClashException {
	   offset = 8;	//start after the $ra and $gp registers
      AVarDecl temp = null;
       for(int i = 0; i < vars.size(); i++)
       {
          temp = (AVarDecl) vars.get(i);
          put(temp.getId(),temp.getType()); //VarClashes will be done in the put method
       }
   }
   
   /** Allow the option of adding individual entries as well. */
   public void put(TId id, PType type) throws VarClashException {
      String name = id.getText();
      if (table.containsKey(name)) {
         String msg = name + " redeclared on line " + id.getLine();
         throw new VarClashException(msg); // There was a clash
      }
      table.put(name, new VarInfo(type));    // No clash; add new binding
	 table.get(name).setAccess(new InFrame(offset++));	
   }
   
   /** Lookup and return the type of a variable */
   public PType get(String name) {
      return table.get(name).getType(); 
   }
   
   /** Lookup and return a variable's VarInfo record */
   public VarInfo getInfo(String name) {
      return table.get(name);
   }
   
   /** Return all var names in the table */
   public Set<String> getVarNames() {
      return table.keySet();
   }
   
   /** Returns the number of entries in the table */
   public int size() { return table.size(); }

   public boolean inside(String name)
   {
      if (table.containsKey(name))
      {
        return true;
      }
      else return false;
   }
   
   /** Print out the entire contents of the table */
   public void dump() {
      for(Map.Entry<String,VarInfo> entry: table.entrySet())
      {
         String name = entry.toString().substring(0,entry.toString().indexOf("="));
         System.out.println("   "+name+ " : "+ entry.getValue().toString());
      }
   }
   
   public void dumpIRT(boolean dot) {
      //TODO Fill in the guts of this method -- but not until the IRT checkpoint
   }
}
