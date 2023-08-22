package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.CarDealershipManagementDAO;

import java.util.List;

@Service
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
