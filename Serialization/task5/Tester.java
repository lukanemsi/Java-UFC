package Serialization;

import java.io.Serializable;

public class Tester  implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int integerTest;
    private String stringTest;

    public Tester(int integerTest, String stringTest) {
        this.integerTest = integerTest;
        this.stringTest = stringTest;
    }

    @Override
    public String toString() {
        return "Tester{" +
                "integerTest=" + integerTest +
                ", stringTest='" + stringTest + '\'' +
                '}';
    }
}
