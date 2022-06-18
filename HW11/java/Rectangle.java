public class Rectangle extends Figure
{
    private double length;
    private double width;
    public Rectangle(double length,double width) {
        this.length = length;
        this.width = width;
        //validate();
        if(this.area() > 10000.0)
            throw new AreaTooLargeException("Rectangle area Cant be bigger than 10.000");
    }
    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2*(length + width);
    }

    @Override
    public void validate() throws RectangleValidateException {
        if(this.length <= 0 || this.width <= 0)
            throw new RectangleValidateException("No such Rectangle exists!");
    }

    @Override
    public String toString() {
        return "Rectangle{ " +
                "l=" + length +
                ", w=" + width +
                " }";
    }
}
