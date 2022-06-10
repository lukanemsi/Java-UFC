package com.company;

import java.util.Comparator;

public class RectangleAreaComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Rectangle && o2 instanceof Rectangle)
        {
            Rectangle other1 = (Rectangle) o1;
            Rectangle other2 = (Rectangle) o2;
            return Double.compare(other1.area(),other2.area());
        }
        return 0;
    }
}
