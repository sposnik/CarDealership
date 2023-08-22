package pl.zajavka.business.managment;

import pl.zajavka.infrastructure.entities.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputMapper {

    public static SalesmanEntity salesmanMapper(List<String> data) {
        return SalesmanEntity.builder()
                .name(data.get(0))
                .surname(data.get(1))
                .pesel(data.get(2))
                .build();
    }

    public static MechanicEntity mechanicMapper(List<String> data) {
        return MechanicEntity.builder()
                .name(data.get(0))
                .surname(data.get(1))
                .pesel(data.get(2))
                .build();
    }

    public static CarToBuyEntity carToBuyMapper(List<String> data) {
        return CarToBuyEntity.builder()
                .vin(data.get(0))
                .brand(data.get(1))
                .model(data.get(2))
                .year(Integer.parseInt(data.get(3)))
                .color(data.get(4))
                .price(new BigDecimal(data.get(5)))
                .build();
    }

    public static ServiceCatalogEntity serviceMapper(List<String> data) {
        return ServiceCatalogEntity.builder()
                .serviceCode(data.get(0))
                .description(data.get(1))
                .price(new BigDecimal(data.get(2)))
                .build();
    }

    public static PartEntity partMapper(List<String> data) {
        return PartEntity.builder()
                .serialNumber(data.get(0))
                .description(data.get(1))
                .price(new BigDecimal(data.get(2)))
                .build();
    }

    public static Map<String, List<String>> simulationDataMapper(List<String> data) {
        return IntStream.iterate(0, p -> p + 2)
                .boxed()
                .limit(3)
                .collect(Collectors.toMap(data::get, i -> List.of(data.get(i + 1).split(";"))));
    }

}
