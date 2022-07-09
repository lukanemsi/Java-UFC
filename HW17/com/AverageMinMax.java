package com.company.com;

/** მეხუთე და მეექვსე ტასკის კლასი*/
public class AverageMinMax<U>
{
    private final U min;
    private final U max;
    private final double average;
    public AverageMinMax(U max,U min, double average)
    {
        this.max = max;
        this.min = min;
        this.average = average;
    }

    public U getMin() {
        return min;
    }

    public U getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }
}
