package com.company;

import java.util.Comparator;

public class TriangleReverseSort implements Comparator<Triangle> {
    @Override
    public int compare(Triangle o1, Triangle o2) 
	{
		if(o1 == null || o2 == null)
			return 0;
        return -Double.compare(o1.perimeter(),o2.perimeter());
    }
}
