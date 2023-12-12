package posto.abcd.api.services.fuelTank;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

@Service
public class FindByFuelTypeService {

    @Autowired
    private FuelTankRepository repository;

    public FuelTankEntity findByFuelType(String name) {
        var fuelTanks =  repository.findByName(name.toUpperCase()).orElseThrow(() -> new EntityNotFoundException("Tipo de tank não encontrado: " + name));

        if(fuelTanks == null) {
            throw new EntityNotFoundException();
        }

        return fuelTanks;
    }
}