package symtable;

import java.util.LinkedList;
import java.util.Set;

import minijava.node.PMethod;
import minijava.node.PVarDecl;
import minijava.node.TId;
import Mips.InFrame;

import Mips.MipsArch;  // These two are needed for the IRT phase
import Arch.*;
import Tree.*;

public class ClassIRTinfo {

    int instanceVars;//holds number of instance variables
    ESEQ ExpressionTree;  //holds
    public ClassIRTinfo(Reg register, int inVars)
    {
        MOVE[] moveArray;
        instanceVars = inVars;
        if(inVars == 0)
        {
            ExpressionTree = new ESEQ(
                                    new MOVE(
                                        new REG(register),new CONST(0)),
                                    new REG(register));
        }
        else
        {
            moveArray = new MOVE[inVars];
            int offSet = 0;
            for(int i = 0; i < inVars; i++)
            {
                moveArray[i] = createMem(register,offSet);
                offSet += 4;
            }

            MOVE allocate = new MOVE(new REG(register),new CALL(new NAME(new Label("malloc")),new ExpList(new CONST(offSet),null))); //create a move to use in the overall sequence
            ExpressionTree = new ESEQ(createSeq(allocate,moveArray,inVars),new REG(register));
        }
    }

    public SEQ createSeq(MOVE allocate, MOVE[] array, int size)
    {
        if(size == 1)
        {
            return new SEQ(allocate,array[size-1]); //base case
        }
        else
        {
            return new SEQ(createSeq(allocate,array,size-1),array[size-1]); //recursion
        }
    }
    public MOVE createMem(Reg register, int offSet)
    {
        return new MOVE(new MEM(new BINOP(BINOP.PLUS, new REG(register),new CONST(offSet))),new CONST(0));

    }

    /*
    Used to pass items from SuperClass
    */
    public int returnVars() 
    {
        return instanceVars;
    }

    public void dumpIRT(boolean dot) 
        {
            Print.prExp(ExpressionTree);
        } 
}
