package posto.abcd.api.service.fuelPump;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;
import posto.abcd.api.repository.fillTank.FillTankRepository;
import posto.abcd.api.repository.fuelPump.FuelPumpRespository;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

@Service
public class FuelPumpService {

    private final FuelPumpRespository fuelPumpRespository;
    private final FuelTankRepository fuelTankRepository;

    public FuelPumpService(FuelPumpRespository fuelPumpRespository, FuelTankRepository fuelTankRepository) {
        this.fuelPumpRespository = fuelPumpRespository;
        this.fuelTankRepository = fuelTankRepository;
    }

    public FuelPumpEntity createPump(FuelPumpDataRequest fuelPumpDataRequest) {

        System.out.println(fuelTankRepository.existsById(fuelPumpDataRequest.fuel_tank_id()));
        if (!fuelTankRepository.existsById(fuelPumpDataRequest.fuel_tank_id())) {
            throw new ValidationException("Id do tank informado não existe!");
        }

        var tank = fuelTankRepository.getReferenceById(fuelPumpDataRequest.fuel_tank_id());
        var fuelPumpEntity = new FuelPumpEntity(fuelPumpDataRequest, tank);

        return fuelPumpRespository.save(fuelPumpEntity);
    }
}
