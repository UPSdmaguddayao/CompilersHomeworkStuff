package symtable; 

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;

import minijava.node.PMethod;
import minijava.node.AMethod;
import minijava.node.PFormal;
import minijava.node.AFormal;
import minijava.node.PType;
import minijava.node.PVarDecl;
import minijava.node.TId;
import Mips.InFrame;

/** 
 * This class maintains information on a <i>collection</i> of methods.  It
 * maps method names to MethodInfo records.
 * @author Brad Richards
 */
public class MethodTable {
   private final int FORMAL_MAX = 4;
   private HashMap<String, MethodInfo> table = new HashMap<String, MethodInfo>();
   int offset;
   
   /** 
    * The constructor is passed a list of PMethod nodes as constructed
    * by the parser.  It adds entries for each method in the list via 
    * the local put() method. 
    * @param methods A list of PMethod nodes
    */
   public MethodTable(LinkedList<PMethod> methods) throws Exception {
    //Check AMethod
      String name = "";
      AMethod temp = null;
      for(int i = 0; i < methods.size(); i++)
      {
        temp = (AMethod)methods.get(i);
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
					   
	   offset = 8; //start after the $ra and $gp registers
      String name = id.getText();
       if (table.containsKey(name)) {
        String msg = name + " redeclared on line " + id.getLine();
        throw new MethodClashException(msg); // There was a clash
      }
      table.put(name, new MethodInfo(retType,id,formals,locals)); //this is where VarClashExceptions will occur
	  
	  //Set the Accessors for FORMALS
	  VarTable frmls = table.get(name).getFormals();
	  
	  //Restrict the number of args
	  if (frmls.size() > FORMAL_MAX){ System.err.println("You can't have more than four arguments for a method!");}
	  
	  Set<String> fSet = frmls.getVarNames();
	  for(String v : fSet){
		// find the pair which matches this arg
		//set its access
		frmls.getInfo(v).setAccess(new InFrame(offset));
		offset += 4;
	  } 
	  
	  //Set the Accessors for LOCALS
	  VarTable lcls = table.get(name).getLocals();
	  Set<String> lSet = lcls.getVarNames();
	  for(String v : lSet){
		// find the pair which matches this arg
		//set its access
		lcls.getInfo(v).setAccess(new InFrame(offset));
		offset += 4;
	  } 
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
      for(Map.Entry<String,MethodInfo> entry: table.entrySet())
      {
         entry.getValue().dump();
      }
   }
   
   public void dumpIRT(boolean dot) {
      //TODO Fill in the guts of this method -- but not until IRT checkpoint
	  System.out.println("MethodTable: dumpIRT: ");
      for(Map.Entry<String,MethodInfo> entry: table.entrySet())
      {
         entry.getValue().dumpIRT(dot);
      }
   }
}
