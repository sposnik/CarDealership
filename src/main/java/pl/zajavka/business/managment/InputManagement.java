package pl.zajavka.business.managment;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class InputManagement {

    private static final String FILE_PATH = "./src/main/resources/car-dealership-traffic-simulation.md";

    public static List<String> inputData;

    static {
        try {
            inputData = getInputData();
        } catch (Exception e) {
            System.err.println("Problem while reading simulation file: " + e.getMessage());
        }
    }


    public static List<String> getInputData() throws IOException {

        inputData = Files.readAllLines(Path.of(FILE_PATH))
                .stream()
                .filter(line -> !line.startsWith("[//]: #"))
                .filter(line -> !line.isEmpty()).toList();

        return inputData;
    }

    public static Map<String, List<List<String>>> getInitialInputData() throws IOException {

        return inputData
                .stream()
                .filter(line -> line.startsWith("INIT -> "))
                .map(line -> line.split(" -> "))
                .collect(
                        Collectors.groupingBy(
                                l -> Arrays.asList(l).get(1),
                                Collectors.mapping(
                                        l -> Arrays.stream(Arrays.asList(l).get(2).split(";")).toList(),
                                        Collectors.toList()
                                )
                        )
                );
    }
    public static Map<String, List<List<String>>> getSimulationInputData() throws IOException {

        return inputData
                .stream()
                .filter(line -> !line.startsWith("INIT -> "))
                .map(line -> line.split(" -> "))
                .collect(
                        Collectors.groupingBy(
                                l -> Arrays.asList(l).get(0),
                                Collectors.mapping(
                                        l -> Arrays.stream(l).skip(1).toList(),
                                        Collectors.toList()
                                )
                        )
                );
    }


    public static <T> List<T> listOfData(Map<String, List<List<String>>> initData,
                                         Keys key,
                                         Function<List<String>, T> mapper) {

        return initData.entrySet().stream()
                .filter(entry -> entry.getKey().equals(key.toString()))
                .flatMap((entry -> entry.getValue().stream().map(mapper)))
                .toList();
    }

}
