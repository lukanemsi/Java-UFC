package com.company.ufc;

import com.company.Rectangle;

public final class Square extends Rectangle
{
    public Square(float length)
    {
        super(length,length);
    }
    /* access modifier შევუცვალე public_ზე რადგან როდესაც გადაფარულია Main კლასი კითხულობს Square_ის ამ მეთოდს
    * რომელიც სხვა Package_შია და შესაბამისად ერორს მაძლევს, რადგან Main extends არ უკეთებს Square_ს,
    * შემეძლო Override არ მექნა და Main_ი ავტომატურად სუპერის (Rectangles) არეას დაინახავდა და გაეშვებოდა,
    * მაგრამ პირობაშია მოთხოვნილი
    * */
    @Override
    public float area()
    {
        return super.area();
    }
    @Override
    public float perimeter()
    {
        return super.perimeter();
    }

    // area  და perimeter ფუნქციები ხილვადია რადგან Square კლასი Rectangle კლასის შვილობილია, და მეთოდი
    // protected  არის გამოცხადებული
    public void showAreaAndPerimeter()
    {
        System.out.println("Area: " + this.area());
        System.out.println("Perimeter" + this.perimeter());
    }

}
