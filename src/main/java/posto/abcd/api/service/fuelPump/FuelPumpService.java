package posto.abcd.api.service.fuelPump;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataResponse;
import posto.abcd.api.dtos.fuelPump.FuelPumpIdRequest;
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

    @Transactional
    public FuelPumpEntity createPump(FuelPumpDataRequest fuelPumpDataRequest) {


        if (!fuelTankRepository.existsById(fuelPumpDataRequest.fuel_tank_id())) {
            throw new ValidationException("Id do tank informado não existe!");
        }

        var tank = fuelTankRepository.getReferenceById(fuelPumpDataRequest.fuel_tank_id());
        var fuelPumpEntity = new FuelPumpEntity(fuelPumpDataRequest, tank);

        return fuelPumpRespository.save(fuelPumpEntity);
    }

    public Page<FuelPumpDataResponse> GetAllPumpsFuel(Pageable pagination) {
        return fuelPumpRespository.findAll(pagination).map(FuelPumpDataResponse::new);
    }

    public FuelPumpEntity getPumpById(Long fuelPumpIdRequest) {

        if (!fuelPumpRespository.existsById(fuelPumpIdRequest)) {
            throw new ValidationException("Id da bomba informada não existe!");
        }

        return fuelPumpRespository.getReferenceById(fuelPumpIdRequest);
    }
}
