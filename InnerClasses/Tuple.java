package com.company.InnerClasses;

public class Tuple <T,U>
{
    T t;
    U u;
    public Tuple(T t, U u)
    {
        this.t = t;
        this.u = u;
    }

    public T getFirst() {
        return t;
    }

    public U getSecond() {
        return u;
    }
}
