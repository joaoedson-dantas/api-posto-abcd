package posto.abcd.api.services.fuelTank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fuelTank.FuelTankDataRequest;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.infra.exceptions.TankAlreadyExistsException;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CreateFuelTankService {

    @Autowired
    private FuelTankRepository repository;

    @Autowired
    private FindByFuelTypeService service;

    @Transactional
    public FuelTankEntity create(FuelTankDataRequest fuelTankDataRequest) {


        var tankExists = service.findByFuelType(fuelTankDataRequest.name().toUpperCase());


        if (Objects.equals(tankExists.getName(), fuelTankDataRequest.name().toUpperCase())) {
            throw  new TankAlreadyExistsException(fuelTankDataRequest.name());
        }

        var fuelTankEntity = new FuelTankEntity(fuelTankDataRequest, LocalDateTime.now());
        repository.save(fuelTankEntity);
        return fuelTankEntity;
    }
}
