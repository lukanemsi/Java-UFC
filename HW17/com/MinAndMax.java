package com.company.com;

public class MinAndMax<T>
{
    /**
     * დამხმარე კლასი რადგან ერთიანად დავაბრუნო ნებისმიერი ტიპის მინიმუმი და მაქსიმუმი მნიშვნელობა
     * */
    private final T min;
    private final T max;

    public MinAndMax(T max, T min)
    {
        this.max = max;
        this.min = min;
    }
    public T getMin() {
        return min;
    }
    public T getMax() {
        return max;
    }


}
