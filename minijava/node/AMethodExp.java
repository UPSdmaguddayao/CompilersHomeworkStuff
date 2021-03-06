/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import java.util.*;
import minijava.analysis.*;

@SuppressWarnings("nls")
public final class AMethodExp extends PExp
{
    private PExp _obj_;
    private TId _id_;
    private final LinkedList<PExp> _args_ = new LinkedList<PExp>();

    public AMethodExp()
    {
        // Constructor
    }

    public AMethodExp(
        @SuppressWarnings("hiding") PExp _obj_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") List<?> _args_)
    {
        // Constructor
        setObj(_obj_);

        setId(_id_);

        setArgs(_args_);

    }

    @Override
    public Object clone()
    {
        return new AMethodExp(
            cloneNode(this._obj_),
            cloneNode(this._id_),
            cloneList(this._args_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMethodExp(this);
    }

    public PExp getObj()
    {
        return this._obj_;
    }

    public void setObj(PExp node)
    {
        if(this._obj_ != null)
        {
            this._obj_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._obj_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public LinkedList<PExp> getArgs()
    {
        return this._args_;
    }

    public void setArgs(List<?> list)
    {
        for(PExp e : this._args_)
        {
            e.parent(null);
        }
        this._args_.clear();

        for(Object obj_e : list)
        {
            PExp e = (PExp) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._args_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._obj_)
            + toString(this._id_)
            + toString(this._args_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._obj_ == child)
        {
            this._obj_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._args_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._obj_ == oldChild)
        {
            setObj((PExp) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        for(ListIterator<PExp> i = this._args_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PExp) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
