package Arch;

import Tree.Exp;

/** 
 * Instances of the Access abstract class represent stored data 
 * items.  Architecture-specific classes extend this base class 
 * to describe how to access data items in registers, and those
 * stored on the stack.  In each case, the <tt>getTree</tt> 
 * accessors return an IRT fragment that describes the location 
 * of the data item.
 */

public abstract class Access
{
   abstract public Exp getTree();
   abstract public Exp getTree(Exp base);
}
