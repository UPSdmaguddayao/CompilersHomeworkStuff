package Tree;

/**
 * The IRT expression nodes all extend this abstract base class.
 * Regardless of their type, expression nodes all share the same
 * basic functionality:  They can be constructed from a list of
 * subexpressions, and can return a list of their subexpressions
 * as well.  (For example, BINOP's <tt>build</tt> method would 
 * take a left and right subtree, and its <tt>kids()</tt> method 
 * would return a list consisting of those two subtrees.)
 */

abstract public class Exp {
   abstract public ExpList kids();
   abstract public Exp build(ExpList kids);
}

