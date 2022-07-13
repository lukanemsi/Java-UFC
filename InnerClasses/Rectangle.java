package com.company.InnerClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Rectangle extends Figure {
    private double length;
    private double width;
    private Rectangle[] rectangles;

    public Rectangle(double length, double width) {
        this.width = width;
        this.length = length;
    }

    public Rectangle(String path) throws FigureValidatorException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            List<String> lines = reader.lines().collect(Collectors.toList());
            List<String> validLines = lines.stream().map(String::trim).filter(this::validate).collect(Collectors.toList());

            class RectangleValidator extends FigureValidator
            {

                @Override
                public void validate(String figureRegex) throws FigureValidatorException
                {
                        String[] numbers = figureRegex.split("#");
                        if(Double.parseDouble(numbers[0]) <= 0 || Double.parseDouble(numbers[1]) <= 0)
                            throw new FigureValidatorException("Invalid Edge");
                }
            }
            RectangleValidator validator = new RectangleValidator();
            for(String string: validLines)
            {
                validator.validate(string);
            }
            rectangles = new Rectangle[validLines.size()];
            for (int i = 0; i < rectangles.length; i++)
            {
                List<Double> num = Arrays.stream(validLines.get(i).split("#")).map(Double::valueOf).collect(Collectors.toList());
                rectangles[i] = new Rectangle(num.get(0),num.get(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String rectangle) {
        Pattern pattern = Pattern.compile("-?[.0-9]+#-?[.0-9]+");
        Matcher matcher = pattern.matcher(rectangle);
        return matcher.matches();
    }

    public Rectangle[] getRectangles() {
        return rectangles;
    }
    @Override
    public double perimeter() {
        return (width + length) * 2;
    }

    @Override
    public double area() {
        return width * length;

    }

    @Override
    public String toString() {
        return "Rectangle[" +
                 length +
                ", " + width +
                ']';
    }
}
