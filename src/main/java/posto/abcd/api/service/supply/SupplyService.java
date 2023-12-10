package posto.abcd.api.service.supply;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;
import posto.abcd.api.entity.supply.SupplyEntity;
import posto.abcd.api.repository.fuelPump.FuelPumpRespository;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;
import posto.abcd.api.repository.supply.SupplyRepository;
import posto.abcd.api.service.fuelTank.FuelTankService;
import posto.abcd.api.service.globalSettings.GlobalSettingsService;

import java.math.BigDecimal;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;
    private final FuelPumpRespository fuelPumpRespository;
    private final FuelTankRepository fuelTankRepository;
    private final FuelTankService fuelTankService;
    private final GlobalSettingsService globalSettingsService;

    public SupplyService(SupplyRepository supplyRepository, FuelPumpRespository fuelPumpRespository, FuelTankRepository fuelTankRepository, FuelTankService fuelTankService, GlobalSettingsService globalSettingsService) {
        this.supplyRepository = supplyRepository;
        this.fuelPumpRespository = fuelPumpRespository;
        this.fuelTankRepository = fuelTankRepository;
        this.fuelTankService = fuelTankService;
        this.globalSettingsService = globalSettingsService;
    }

    @Transactional
    public SupplyEntity supplyFuel(SupplyDataResquet supplyDataResquet) {


        if (!fuelPumpRespository.existsById(supplyDataResquet.fuel_pomp_id())) {
            throw new ValidationException("Id do bomba informado não existe!");
        }

        if (supplyDataResquet.liters() <= 0) {
            throw new IllegalArgumentException("A quantidade de litros para abastecimento deve ser positiva.");
        }

        var fuelPumpEntity = fuelPumpRespository.getReferenceById(supplyDataResquet.fuel_pomp_id());

        if (!fuelPumpRespository.existsById(fuelPumpEntity.getId())) {
            throw new ValidationException("A entidade FuelPump não existe no banco de dados.");
        }


        if (!fuelTankRepository.existsById(fuelPumpEntity.getFuelTankEntity().getId())) {
            throw new ValidationException("Id do tank informado não existe!");
        }

        var tank = fuelTankRepository.getReferenceById(fuelPumpEntity.getFuelTankEntity().getId());

        if (supplyDataResquet.liters() > tank.getLiters()) {
            throw new IllegalArgumentException("A quantidade de combustível no tank é insuficiente para o abastecimento");
        }

        tank.drain(supplyDataResquet.liters());

        var price = (long) Integer.parseInt(globalSettingsService.getGlobalSettingsByKey("fuel_price").value());
        var taxAmount = Integer.parseInt(globalSettingsService.getGlobalSettingsByKey("tax_value").value());
        var priceDecimal = new BigDecimal(price);

        var totalFuelPrice = priceDecimal.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(taxAmount).divide(BigDecimal.valueOf(100))));


        var supplyEntity = new SupplyEntity(supplyDataResquet.date(), supplyDataResquet.liters(), price, taxAmount, fuelPumpEntity);
        return supplyRepository.save(supplyEntity);


    }
}
