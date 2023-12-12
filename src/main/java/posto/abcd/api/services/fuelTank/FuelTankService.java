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
    public FuelTankEntity create(FuelTankDataRequest fuelTankDataRequest) {
        var fuelTankEntity = new FuelTankEntity(fuelTankDataRequest, LocalDateTime.now());
        fuelTankRepository.save(fuelTankEntity);
        return fuelTankEntity;
    }
    @Transactional
    public Page<FuelTankDataList> list(Pageable pagination) {
        return fuelTankRepository.findAll(pagination)
                .map(FuelTankDataList::new);
    }


    @Transactional
    public FuelTankEntity findByFuelType(String name) {
        var fuelTanks =  fuelTankRepository.findByName(name.toUpperCase()).orElseThrow(() -> new EntityNotFoundException("Tipo de tank não encontrado: " + name));

        if(fuelTanks == null) {
            throw new EntityNotFoundException();
        }

        return fuelTanks;
    }
    @Transactional
    public FuelTankEntity findById(Long id) {
        var fuelTank = fuelTankRepository.getReferenceById(id);

        if (fuelTank.getId() == null) {
            throw new EntityNotFoundException();
        }
        return fuelTank;
    }
}
