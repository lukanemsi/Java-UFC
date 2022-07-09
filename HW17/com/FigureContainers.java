package com.company.com;

import java.util.*;

public class FigureContainers<T extends Figure>
{
    /**
     * დამხმარე კლასი რადგან ფაილიდან ამოკითხვის შემდეგ, ერთი მეთოდით მარტივად დავაბრუნო ორივე figure კლასის ArrayList და
     * TreeSet.
     * */
    private ArrayList<T> figureList;
    private TreeSet<T> figureTreeSet;
    private TreeMap<T,Integer> figureMapToLines;

    public ArrayList<T> getFigureList()
    {
        return figureList;
    }

    public void setFigureList(ArrayList<T> figureList)
    {
        this.figureList = figureList;
    }

    public TreeSet<T> getFigureTreeSet()
    {
        return figureTreeSet;
    }

    public void setFigureTreeSet(TreeSet<T> figureTreeSet)
    {
        this.figureTreeSet = figureTreeSet;
    }

    public TreeMap<T, Integer> getFigureMapToLines()
    {
        return figureMapToLines;
    }

    public void setFigureMapToLines(TreeMap<T, Integer> figureMapToLines)
    {
        this.figureMapToLines = figureMapToLines;
    }
}
