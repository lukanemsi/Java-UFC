public class Rectangle extends Figure{
    private double length;
    private double width;
    private static int counter;
    public Rectangle(double length,double width) throws ValidateException {
        this.length = length;
        this.width = width;
        validate();
        counter ++;
        if(counter > 5)
            throw new RectangleLimitException("You are not allow to create more than 5 instanceof Rectangle object");
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
    public void validate() throws ValidateException {
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
