package pl.zajavka.business.DAO;

import java.util.List;

public interface CarDealershipManagementDAO {

    void deleteAll();

    void saveEntities(List<?> entities);
}
