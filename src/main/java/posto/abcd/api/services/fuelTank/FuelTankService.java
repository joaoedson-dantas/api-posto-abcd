package posto.abcd.api.services.fuelTank;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fuelTank.FuelTankDataList;
import posto.abcd.api.dtos.fuelTank.FuelTankDataRequest;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

import java.time.LocalDateTime;

@Service
public class FuelTankService {


    private final FuelTankRepository fuelTankRepository;

    public FuelTankService(FuelTankRepository fuelTankRepository) {
        this.fuelTankRepository = fuelTankRepository;
    }


    @Transactional
    public Page<FuelTankDataList> list(Pageable pagination) {
        return fuelTankRepository.findAll(pagination)
                .map(FuelTankDataList::new);
    }



}
