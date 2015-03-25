package Tree;

/**
 * The IRT statement nodes all extend this abstract base class.
 * Regardless of their type, statement nodes all share the same
 * basic functionality:  They can be constructed from a list of
 * subexpressions, and can return a list of their subexpressions
 * as well.  (For example, SEQ's build method would take a left
 * and right subtree, and its kids() method would return a list
 * consisting of those two subtrees.)  Stm nodes also must have
 * a next() method that links to the next Stm in a program, for
 * use in the simulator.
 */

abstract public class Stm {
   abstract public ExpList kids();
   abstract public Stm build(ExpList kids);
   abstract public Stm next();
}

