package posto.abcd.api.service.supply;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.entity.supply.SupplyEntity;
import posto.abcd.api.repository.fuelPump.FuelPumpRespository;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;
import posto.abcd.api.repository.supply.SupplyRepository;
import posto.abcd.api.service.fuelPump.FuelPumpService;
import posto.abcd.api.service.fuelTank.FuelTankService;
import posto.abcd.api.service.globalSettings.GlobalSettingsService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;
    private final FuelPumpRespository fuelPumpRespository;
    private final FuelTankRepository fuelTankRepository;
    private final FuelPumpService fuelPumpService;
    private final FuelTankService fuelTankService;
    private final GlobalSettingsService globalSettingsService;

    public SupplyService(SupplyRepository supplyRepository, FuelPumpRespository fuelPumpRespository, FuelTankRepository fuelTankRepository, FuelPumpService fuelPumpService, FuelTankService fuelTankService, GlobalSettingsService globalSettingsService) {
        this.supplyRepository = supplyRepository;
        this.fuelPumpRespository = fuelPumpRespository;
        this.fuelTankRepository = fuelTankRepository;
        this.fuelPumpService = fuelPumpService;
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

        var pump = fuelPumpService.getPumpById(supplyDataResquet.fuel_pomp_id());

        if (!fuelTankRepository.existsById(pump.getFuelTankEntity().getId())) {
            throw new ValidationException("Id do tank informado não existe!");
        }

        var tank = fuelTankService.findById(pump.getFuelTankEntity().getId());

        if (supplyDataResquet.liters() > tank.getLiters()) {
            throw new IllegalArgumentException("A quantidade de combustível no tank é insuficiente para o abastecimento");
        }

        tank.drain(supplyDataResquet.liters());

        BigDecimal price = new BigDecimal(globalSettingsService.getGlobalSettingsByKey("fuel_price").value());
        BigDecimal tax =  new BigDecimal(globalSettingsService.getGlobalSettingsByKey("tax_value").value());

        var taxPercentage = BigDecimal.valueOf(tax.byteValueExact()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);

        BigDecimal taxAmount = price.multiply(taxPercentage);
        BigDecimal totalFuelPrice = price.add(taxAmount);

        System.out.println("O valor da taxa de porcentagem é de: " + taxAmount);
        System.out.println("Preço do combustível com imposto de 13%: " + totalFuelPrice);

        var supplyEntity = new SupplyEntity(supplyDataResquet, price, tax, pump);
        return supplyRepository.save(supplyEntity);

    }
}
