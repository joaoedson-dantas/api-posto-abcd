package posto.abcd.api.services.fuelTank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.infra.exceptions.TankAlreadyExistsException;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

import java.util.Optional;

@Service
public class FindByFuelTypeService {

    @Autowired
    private FuelTankRepository repository;

    public Optional<FuelTankEntity> findByFuelType(String name) {

        var fuelTanks =  repository.findByName(name.toUpperCase());

        if(fuelTanks.isPresent()) {
            throw new TankAlreadyExistsException(name);
        }

        return repository.findByName(name);
    }
}