package symtable;

import java.util.LinkedList;
import java.util.Set;

import minijava.node.PMethod;
import minijava.node.PVarDecl;
import minijava.node.TId;
import Mips.InFrame;

import Mips.MipsArch;  // These two are needed for the IRT phase
import Arch.*;

public class ClassIRTInfo {

    public void dump() {
        System.out.println("-------------------------------------\nClass: " +
            className.toString()); 
        if(superClass != null)
        {
            System.out.print(" Superclass Name: " + superClass.toString()); 
        }
        System.out.println("\n-------------------------------------" );
        vars.dump();
        methods.dump();
        System.out.println("\n");
    } 

    public void dumpIRT(boolean dot) {
        // TODO:  You'll complete this one on the next checkpoint
        System.out.println("-------------------------------------\nClassInfo dumpIRT:" +
            className.toString()); 
        if(superClass != null)
        {
            System.out.print(" Extends: " + superClass.toString()); 
        }
        System.out.println("\n-------------------------------------" );
        System.out.println("Instance var accessors:");
        vars.dumpIRT(dot);

        methods.dumpIRT(dot);
System.out.println("	meths done");
    } 
}
