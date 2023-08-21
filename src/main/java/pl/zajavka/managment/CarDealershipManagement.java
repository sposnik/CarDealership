package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import pl.zajavka.DAO.CarDealershipManagementDAO;

import java.util.List;

@AllArgsConstructor
public class CarDealershipManagement {

    private CarDealershipManagementDAO carDealershipManagementDAO;

    private DataPrepareService dataPrepareService;

    public void clear(){
        carDealershipManagementDAO.deleteAll();
    }

    @SneakyThrows
    public void saveAll(){
        var listOfEntitles = DataPrepareService.listsToPersist();
        for (List<?> list : listOfEntitles) {
            carDealershipManagementDAO.saveEntities(list);
        }
    }

}
