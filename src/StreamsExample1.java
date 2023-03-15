import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsExample1 {

    public static void main(String[] args) throws IOException {

        // single line print stream
        IntStream
                .range(1, 10)
                .forEach(System.out::print);

        System.out.println();
        System.out.println("*******");

        // next line print stream
        IntStream
                .range(1, 10)
                .forEach(System.out::println);

        System.out.println("*******");

        // Integer stream with skip. Skip first 5 elements of stream. Print 6-9
        IntStream
                .range(1, 10)
                .skip(5)
                .forEach(System.out::println);

        System.out.println("*******");

        // sum Integer elements of stream.
        System.out.println(IntStream
                .range(1, 5)
                .sum());

        System.out.println("*******");

        // Stream.of , sorted, findFirst
        Stream.of(
                        "E", "D", "C", "B"
                ).sorted()
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("*******");

        // Stream.of , sorted, findFirst on array list
        List<String> array = Arrays.asList("E", "D", "C", "B");
        array.stream()
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("*******");

        // average of squares of an int array
        int[] intArray = new int[]{1, 2, 3, 4};
        Arrays.stream(intArray)
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        System.out.println("*******");

        // average of squares of an int array
        Arrays.stream(
                        new int[]{1, 2, 3, 4}
                ).map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        System.out.println("*******");

        // Stream from list, filter and print
        array.stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("E"))
                .forEach(System.out::println);

        System.out.println("*******");

        // stream rows from text file, sort, filter, and print
        Stream<String> newFile1 = Files.lines(Paths.get("src/new.txt"));
        newFile1
                .sorted()
                .filter(x -> x.length() > 2)
                .forEach(System.out::println);
        newFile1.close();

        System.out.println("*******");

        // stream rows from text file and save to list
        List<String> newFile2 = Files.lines(Paths.get("src/new.txt"))
                .filter(x -> x.contains("E"))
                .collect(Collectors.toList());
        newFile2.forEach(System.out::println);

        System.out.println("*******");

        // stream rows from text file, filter and save to list
        Stream<String> newFile3 = Files.lines(Paths.get("src/new.txt"));
        newFile3
                .filter(x -> x.contains("E"))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("*******");

        // stream rows from csv file and count
        Stream<String> rows1 = Files.lines(Paths.get("src/data.txt"));
        int rowCount1 = (int) rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount1 + " rows.");
        rows1.close();

        System.out.println("*******");

        // stream rows from csv file and count
        Stream<String> rows2 = Files.lines(Paths.get("src/data.txt"));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
        rows2.close();

        System.out.println("*******");

        // stream rows from csv file and store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get("src/data.txt"));
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                                x -> x[0],
                                x -> Integer.parseInt(x[1])
                        )
                );
        rows3.close();
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }

        System.out.println("*******");

        // reduction sum
        double total = Stream.of(
                7.3, 1.5, 4.8
        ).reduce(0.0, (Double a, Double b) -> a+b);
        System.out.println("Total = "+total);

        System.out.println("*******");

        // reduction - summary etiquette's
        IntSummaryStatistics summary = IntStream.of(
                7, 2, 19, 88, 73, 4, 10
        ).summaryStatistics();
        System.out.println(summary);
    }
}
