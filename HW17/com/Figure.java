package com.company.com;

public abstract class Figure implements Comparable<Figure>
{
    public abstract double perimeter();
    public abstract double area();

    @Override
    public int compareTo(Figure other) {
        if(other == null)
            return 1;
        return Double.compare(this.perimeter(), other.perimeter());
    }
}
