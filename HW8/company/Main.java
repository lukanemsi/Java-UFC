package com.company;


import java.util.*;

public class Main{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        /**task 1/2/3/4 */
        Rectangle[] rectangles =
                {
                        new Rectangle(20,10),
                        new Rectangle(10,20),
                        new Rectangle(10,5),
                        new Rectangle(3,4),
                        new Rectangle(5,4),
                        new Rectangle(20,30),
                        new Rectangle(15,10)
                };
        printRectangleArray(rectangles);

        System.out.println("------------------");
        // პირველი მეთოდით სორტირება
        Arrays.sort(rectangles);
        printRectangleArray(rectangles);


        System.out.println("------------------");
        //მეორე მეთოდით სორტირება
        Arrays.sort(rectangles,new RectangleAreaComparator());
        printRectangleArray(rectangles);

        //comparable და RectangleAreaComparator ჯერჯერობით სხვაობას არ იძლევა რადგან სორტირებისას ერთნაირად ჯდება, მაგრამ TreeSet ში იძლევა სხვაობას

        System.out.println("------------------");
        Rectangle findWArea20 = null;
        for (int i = 0; i < rectangles.length; i++) {
            if(rectangles[i].area() == 20)
                findWArea20 = rectangles[i];
        }
        System.out.println(findWArea20);

        System.out.println("Task 1/2/3/4 FINISHED!");


        /**task 5*/
        ArrayList rectangleList = new ArrayList();
        rectangleList.add(new Rectangle(2,3));
        rectangleList.add(new Rectangle(20,30));
        rectangleList.add(new Rectangle(20,30));
        rectangleList.add(new Rectangle(10,15));
        rectangleList.add(new Rectangle(1,5));
        rectangleList.add(new Rectangle(3,4));
        rectangleList.add(new Rectangle(3,4.5));


        Collections.sort(rectangleList,new RectanglePerimeterRevComparator());
        System.out.println("------------------");
        System.out.println(rectangleList);

        System.out.println("------------------");
        Rectangle recWithPer15 = null;
        Rectangle recWithL3W4 = null;
        for (Object rec: rectangleList)
        {
            if(rec instanceof Rectangle)
            {
                Rectangle r = (Rectangle) rec;
                if (r.perimeter() == 15)
                    recWithPer15 = r;
                if (r.getLength() == 3 && r.getWidth() == 4)
                    recWithL3W4 = r;

            }
        }
        System.out.println(recWithL3W4);
        System.out.println(recWithPer15);



        System.out.println("------------------");
        Iterator iter = rectangleList.iterator();
        recWithPer15 = null;
        recWithL3W4 = null;
        while(iter.hasNext())
        {
            Object next = iter.next();
            if(next instanceof Rectangle)
            {
                Rectangle rec = ((Rectangle)next);
                if(rec.getWidth() == 4 && rec.getLength() == 3 )
                    recWithL3W4 = rec;
                if(rec.perimeter() == 15)
                    recWithPer15 = rec;
            }
        }
        System.out.println(recWithL3W4);
        System.out.println(recWithPer15);
        System.out.println("Task 5 FINISHED!");

        /**task 6*/
        System.out.println("------------------");
        TreeSet treeSet = new TreeSet();
        treeSet.addAll(rectangleList);
        System.out.println(treeSet);
        System.out.println("Task 6 FINISHED!");

        /**task 7*/
        System.out.println("------------------");
        TreeSet treeSet1 = new TreeSet(new RectanglePerimeterRevComparator());
        treeSet1.addAll(rectangleList);
        System.out.println(treeSet1);
        System.out.println("Task 7 FINISHED!");

        /**task 8*/
        System.out.println("------------------");
        HashSet hashSet = new HashSet();
        hashSet.add(new Rectangle(3,4));
        hashSet.add(new Rectangle(3,4));
        hashSet.add(new Rectangle(4,3));
        hashSet.add(new Rectangle(10,20));
        hashSet.add(new Rectangle(3,4));
        System.out.println(hashSet);
        System.out.println("Task 8 FINISHED!");

        /**task 10*/
        System.out.println("------------------");
        TreeMap treeMap = new TreeMap();
        treeMap.put(new Rectangle(3,4),'C');
        treeMap.put(new Rectangle(10,20),'B');
        treeMap.put(new Rectangle(30,40),'A');
        System.out.println(treeMap);
        Object save = treeMap.put(new Rectangle(3,4),'D');
        System.out.println(save);
        Object delSave = treeMap.remove(new Rectangle(3,4));
        System.out.println(delSave);
        delSave = treeMap.remove(new Rectangle(4,3));
        System.out.println(delSave); // null რადგან პერიმეტრი ერთი აქვთ მაგრამ გვერდების მნიშვნელობები გადანაცვლებულია
        System.out.println("Task 10 FINISHED!");

        /**task 11*/
        System.out.println("------------------");
        HashMap hashMap = new HashMap();
        hashMap.put(new Rectangle(3,4),'C');
        hashMap.put(new Rectangle(10,20),'B');
        hashMap.put(new Rectangle(30,40),'K');
        hashMap.put(new Rectangle(30,40),'A');
        hashMap.put(new Rectangle(4,3),'D');
        System.out.println(hashMap);

        Set keySet = hashMap.keySet();
        System.out.println(keySet);

        Collection values = hashMap.values();
        System.out.println(values);

        System.out.println("------------------");
        Set entrySet = hashMap.entrySet();
        for (Object obj: entrySet)
        {
            System.out.println(obj);
        }

        System.out.println("------------------");
        Iterator iterator = hashMap.keySet().iterator();
        while (iterator.hasNext())
        {
            Object next = iterator.next();
            System.out.println( next + "=" + hashMap.get(next));
        }
        System.out.println("Task 11 FINISHED!");
    }
    public static void printRectangleArray(Rectangle[] rectangles)
    {
        int i = 0;
        for (Rectangle rec: rectangles)
        {
            if(i == 0)
                System.out.print("[" + rec + ", ");
            else if(i == rectangles.length - 1)
                System.out.print(rec + "]");
            else
                System.out.print(rec + ",");
            i++;
        }
        System.out.println("");
    }
}