package pl.zajavka.business.services.utils;

import pl.zajavka.infrastructure.entities.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputMapper {

    public static Map<String, List<String>> simulationDataMapper(List<String> data) {
        return IntStream.iterate(0, p -> p + 2)
                .boxed()
                .limit(3)
                .collect(Collectors.toMap(data::get, i -> List.of(data.get(i + 1).split(";"))));
    }

}
