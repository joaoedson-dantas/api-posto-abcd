package posto.abcd.api.services.fuelPump;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataResponse;

import posto.abcd.api.entity.fuelPump.FuelPumpEntity;
import posto.abcd.api.repository.fuelPump.FuelPumpRespository;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

@Service

public class CreateFuelPumpService {

    private final FuelPumpRespository fuelPumpRespository;
    private final FuelTankRepository fuelTankRepository;

    public CreateFuelPumpService(FuelPumpRespository fuelPumpRespository, FuelTankRepository fuelTankRepository) {
        this.fuelPumpRespository = fuelPumpRespository;
        this.fuelTankRepository = fuelTankRepository;
    }

    @Transactional
    public FuelPumpEntity createPump(FuelPumpDataRequest fuelPumpDataRequest) {


        if (!fuelTankRepository.existsById(fuelPumpDataRequest.fuel_tank_id())) {
            throw new ValidationException("Id do tank informado não existe!");
        }

        var tank = fuelTankRepository.getReferenceById(fuelPumpDataRequest.fuel_tank_id());
        var fuelPumpEntity = new FuelPumpEntity(fuelPumpDataRequest, tank);

        return fuelPumpRespository.save(fuelPumpEntity);
    }
}
