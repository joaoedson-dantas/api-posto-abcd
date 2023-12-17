package posto.abcd.api.services.supply;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.entity.supply.SupplyEntity;
import posto.abcd.api.repository.fuelPump.FuelPumpRespository;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;
import posto.abcd.api.repository.supply.SupplyRepository;
import posto.abcd.api.services.globalSettings.GetGlobalSettingsByKeyService;
import posto.abcd.api.services.globalSettings.CreateGlobalSettingsService;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FuelSupplyService {

    private final SupplyRepository supplyRepository;
    private final FuelPumpRespository fuelPumpRespository;
    private final FuelTankRepository fuelTankRepository;
    private final GetGlobalSettingsByKeyService getGlobalSettingsByKeyService;

    public FuelSupplyService(SupplyRepository supplyRepository,
                             FuelPumpRespository fuelPumpRespository,
                             FuelTankRepository fuelTankRepository,
                             CreateGlobalSettingsService createGlobalSettingsService,
                             GetGlobalSettingsByKeyService getGlobalSettingsByKeyService)
    {
        this.supplyRepository = supplyRepository;
        this.fuelPumpRespository = fuelPumpRespository;
        this.fuelTankRepository = fuelTankRepository;
        this.getGlobalSettingsByKeyService = getGlobalSettingsByKeyService;


    }

    @Transactional
    public SupplyEntity supplyFuel(SupplyDataResquet supplyDataResquet) {


        if (!fuelPumpRespository.existsById(supplyDataResquet.fuel_pomp_id())) {
            throw new ValidationException("Id do bomba informado não existe!");
        }

        if (supplyDataResquet.liters().floatValue() <= 0) {
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

        if (supplyDataResquet.liters().doubleValue() > tank.getLiters()) {
            throw new IllegalArgumentException("A quantidade de combustível no tank é insuficiente para o abastecimento");
        }

        // pegando a configuração do combustível para identificar qual tipo de combustível e seu devido valor;
        var fuelSettings = getGlobalSettingsByKeyService.getByKey(supplyDataResquet.fuel_key());
        var taxValue = getGlobalSettingsByKeyService.getByKey("tax-value");

        var pricePerLiter = new BigDecimal(fuelSettings.value());
        var liters = BigDecimal.valueOf(supplyDataResquet.liters().doubleValue());

        var priceInAmount = pricePerLiter.multiply(liters);
        var taxAmount = new BigDecimal(taxValue.value());
        var totalFuelPrice = priceInAmount.add(priceInAmount.multiply(taxAmount.divide(BigDecimal.valueOf(100))));

        totalFuelPrice = totalFuelPrice.setScale(2, RoundingMode.HALF_UP);

        var supplyEntity = new SupplyEntity(supplyDataResquet.date(), supplyDataResquet.liters(), totalFuelPrice, taxAmount.intValue(), fuelPumpEntity);
        tank.drain(supplyDataResquet.liters().longValue());
        return supplyRepository.save(supplyEntity);
    }
}

