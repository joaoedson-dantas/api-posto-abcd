package posto.abcd.api.services.fillTank;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fillTank.FillTankDataRequest;
import posto.abcd.api.entity.fillTanks.FillTanksEntity;
import posto.abcd.api.repository.fillTank.FillTankRepository;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

import java.time.LocalDateTime;

@Service
public class FillTankService {

    private final FillTankRepository fillTankRepository;
    private final FuelTankRepository fuelTankRepository;

    public FillTankService(FillTankRepository fillTankRepository, FuelTankRepository fuelTankRepository) {
        this.fillTankRepository = fillTankRepository;
        this.fuelTankRepository = fuelTankRepository;
    }


    @Transactional
    public FillTanksEntity fillTank(FillTankDataRequest fillTankDataRequest) {

        if (!fuelTankRepository.existsById(fillTankDataRequest.fuel_tank_id())) {
            throw new ValidationException("Id do tank informado não existe!");
        }

        if (fillTankDataRequest.liters() <= 0) {
            throw new IllegalArgumentException("A quantidade de litros para abastecimento deve ser positiva.");
        }

        var tank = fuelTankRepository.getReferenceById(fillTankDataRequest.fuel_tank_id());

        tank.toFuel(fillTankDataRequest.liters());

        var fillTanksEntity = new FillTanksEntity(fillTankDataRequest, LocalDateTime.now(), tank);
        return fillTankRepository.save(fillTanksEntity);

    }
}
