import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class Main {
    public static void main(String[] args) {
        /** task 1,2 */
        Path filePath = Path.of("./Triangles_in.dat");
        var triangleList = new ArrayList<Triangle>();
        try {
            Files.readString(filePath)
                    .lines()
                    .filter(Predicate.not(String::isBlank))
                    .map(String::strip)
                    .filter((var line) -> line.matches("[.0-9]+-[.0-9]+-[.0-9]+"))
                    .map((var line) ->
                    {
                        var split = line.split("-");
                        return new Triangle(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
                    })
                    .filter(not(Triangle::isRightTriangle))
                    .forEach(triangleList::add);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /** task 3 */
        // ყველა სამკუთხედის ამოწერა
        var allTriangles = new ArrayList<Triangle>();
        try {
            Files.readString(filePath)
                    .lines()
                    .filter(not(String::isBlank))
                    .map(String::strip)
                    .filter((var line) -> line.matches("[.0-9]+-[.0-9]+-[.0-9]+"))
                    .map((var line) ->
                    {
                        var split = line.split("-");
                        return new Triangle(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
                    })
                    .forEach(allTriangles::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var rightAngled = allTriangles.stream().filter(Triangle::isRightTriangle).findFirst().orElseThrow();


        /** task 4 */
        var unmodifiable = triangleList.stream().collect(Collectors.toUnmodifiableList());
        var triangleArray = unmodifiable.stream().toArray(Triangle[]::new);

        /** task 5 */
        Path path = Path.of("./Triangles_out.dat");
        try {
            Files.writeString
                    (path,
                    unmodifiable.stream().map(Triangle::toString).reduce("", (var tr1, var tr2) -> tr1 + tr2 + "\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}