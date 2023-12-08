package posto.abcd.api.service.fuelTank;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fuelTank.FuelTankDataRequest;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuelTankService {


    private final FuelTankRepository fuelTankRepository;

    public FuelTankService(FuelTankRepository fuelTankRepository) {
        this.fuelTankRepository = fuelTankRepository;
    }


    @Transactional
    public FuelTankEntity create(FuelTankDataRequest fuelTankDataRequest) {
        var fuelTankEntity = new FuelTankEntity(fuelTankDataRequest, LocalDateTime.now());
        fuelTankRepository.save(fuelTankEntity);
        return fuelTankEntity;
    }

    public List<FuelTankEntity> list() {
        var fuelTanks =  fuelTankRepository.findAll();

        if (fuelTanks == null) {
            throw new EntityNotFoundException();
        }

        return fuelTanks;
    }

    public FuelTankEntity findByFuelType(String name) {
        var fuelTanks =  fuelTankRepository.findByName(name.toUpperCase()).orElseThrow(() -> new EntityNotFoundException("Tipo de tank não encontrado: " + name));

        if(fuelTanks == null) {
            throw new EntityNotFoundException();
        }

        return fuelTanks;
    }

}
