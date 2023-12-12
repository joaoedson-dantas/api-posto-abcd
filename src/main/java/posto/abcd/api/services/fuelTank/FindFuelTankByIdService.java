package posto.abcd.api.services.fuelTank;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

@Service
public class FindFuelTankByIdService {

    @Autowired
    private FuelTankRepository respository;

    public FuelTankEntity findById(Long id) {
        var fuelTank = respository.getReferenceById(id);

        if (fuelTank.getId() == null) {
            throw new EntityNotFoundException();
        }
        return fuelTank;
    }
}
