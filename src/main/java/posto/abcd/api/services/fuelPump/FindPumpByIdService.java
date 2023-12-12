package posto.abcd.api.services.fuelPump;


import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;
import posto.abcd.api.repository.fuelPump.FuelPumpRespository;

@Service
public class FindPumpByIdService {

    @Autowired
    private FuelPumpRespository respository;

    public FuelPumpEntity getPumpById(Long fuelPumpIdRequest) {

        if (!respository.existsById(fuelPumpIdRequest)) {
            throw new ValidationException("Id da bomba informada não existe!");
        }

        return respository.getReferenceById(fuelPumpIdRequest);
    }
}
