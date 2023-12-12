package posto.abcd.api.services.fuelTank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.fuelTank.FuelTankDataList;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

@Service
public class ListFuelTankService {


    private final FuelTankRepository fuelTankRepository;

    public ListFuelTankService(FuelTankRepository fuelTankRepository) {
        this.fuelTankRepository = fuelTankRepository;
    }


    public Page<FuelTankDataList> list(Pageable pagination) {
        return fuelTankRepository.findAll(pagination)
                .map(FuelTankDataList::new);
    }

}
