package Arch;

/** 
 * A data structure used to build lists of Labels.  
 */

public class LabelList {
   public Label head;
   public LabelList tail;
   
   /**
    * The constructor assembles a list of Label nodes by concatenating
    * a single Label to an existing list of Labels.
    * @param h  The new head Label
    * @param t  An existing list of Labels
    */
   public LabelList(Label h, LabelList t) {head=h; tail=t;}
}

