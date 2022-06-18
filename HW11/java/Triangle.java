public class Triangle extends Figure
{

    private double a;
    private double b;
    private double c;
    private static int counter;
    public Triangle(double a,double b,double c){
        this.a = a;
        this.b = b;
        this.c = c;
        //validate();
        counter++;
        if(counter > 10)
            throw new TriangleLimitException("You are not allow to create more than 10 instanceof Triangle object");
    }

    @Override
    public double area()
    {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public void validate() throws TriangleValidateException,ValidateException
    {
        if(!(this.a + this.b > c & this.a + this.c > b & this.c + this.b > a) || a <= 0 || b <= 0 || c <= 0)
            throw new TriangleValidateException("No such Triangle exists!");
    }

    @Override
    public String toString() {
        return "Triangle( " + a +  ", " +b +
                ", " + c +
                " )";
    }
}
