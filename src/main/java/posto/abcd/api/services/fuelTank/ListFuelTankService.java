package posto.abcd.api.services.fuelTank;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.fuelTank.FuelTankDataList;
import posto.abcd.api.dtos.fuelTank.FuelTankDataResponse;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

import java.util.List;

@Service
public class ListFuelTankService {


    private final FuelTankRepository fuelTankRepository;

    public ListFuelTankService(FuelTankRepository fuelTankRepository) {
        this.fuelTankRepository = fuelTankRepository;
    }


    public List<FuelTankDataList> list() {
        return fuelTankRepository.findAll()
                .stream().map(FuelTankDataList::new).toList();
    }

}
