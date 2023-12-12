package posto.abcd.api.services.supply;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.dtos.fuelTank.FuelTankDataRequest;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataRequest;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.repository.supply.SupplyRepository;
import posto.abcd.api.services.fuelPump.CreateFuelPumpService;
import posto.abcd.api.services.fuelTank.CreateFuelTankService;
import posto.abcd.api.services.fuelTank.ListFuelTankService;
import posto.abcd.api.services.globalSettings.GlobalSettingsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class SupplyServiceTest {

    private ListFuelTankService listFuelTankService;
    private CreateFuelTankService createFuelTankService;
    private CreateFuelPumpService createFuelPumpService;

    public SupplyServiceTest(ListFuelTankService listFuelTankService, CreateFuelTankService createFuelTankService, CreateFuelPumpService createFuelPumpService) {
        this.listFuelTankService = listFuelTankService;
        this.createFuelTankService = createFuelTankService;
        this.createFuelPumpService = createFuelPumpService;
    }



    @Test
    @DisplayName("It should be possible to fuel")
    void supplyFuelSceneOne() {

        // Arrange
        FuelTankDataRequest fuelTankDataRequest = new FuelTankDataRequest("GASOLINA", 1000L);
        var tank = createFuelTankService.create(fuelTankDataRequest);

        FuelPumpDataRequest fuelPumpDataRequest = new FuelPumpDataRequest("Bomba de Gasolina - 1", tank.getId());
        var poump = createFuelPumpService.createPump(fuelPumpDataRequest);

        GlobalSettingsDataRequest globalSettingsFuel = new GlobalSettingsDataRequest("valor do combustível Gasolina", "fuel_price", "5");
        GlobalSettingsDataRequest globalSettingsTax = new GlobalSettingsDataRequest("valor do imposto", "tax_value", "13");
        globalSettingsService.create(globalSettingsFuel);
        globalSettingsService.create(globalSettingsTax);

        SupplyDataResquet supplyDataResquet = new SupplyDataResquet(LocalDateTime.now(), new BigDecimal(200), poump.getId(), "fuel_price");

        // Act
        var response = supplyService.supplyFuel(supplyDataResquet);

        // Assert
        assertEquals(800L, listFuelTankService.findById(response.getFuelPumpEntity().getFuelTankEntity().getId()).getLiters());
        assertNotNull(response.getId());
        var savedSupplyEntity = supplyRepository.findById(response.getId());
        assertTrue(savedSupplyEntity.isPresent());
        assertEquals(response, savedSupplyEntity.get());

    }

    @Test
    @DisplayName("it should not be possible to refuel, insufficient fuel tank")
    void supplyFuelInsufficientFuelOfTankSceneTwo() {

        // Arrange
        FuelTankDataRequest fuelTankDataRequest = new FuelTankDataRequest("GASOLINA", 1L);
        var tank = listFuelTankService.create(fuelTankDataRequest);

        FuelPumpDataRequest fuelPumpDataRequest = new FuelPumpDataRequest("Bomba de Gasolina - 1", tank.getId());
        var poump = createFuelPumpService.createPump(fuelPumpDataRequest);

        GlobalSettingsDataRequest globalSettingsFuel = new GlobalSettingsDataRequest("valor do combustível Gasolina", "fuel_price", "5");
        GlobalSettingsDataRequest globalSettingsTax = new GlobalSettingsDataRequest("valor do imposto", "tax_value", "13");
        globalSettingsService.create(globalSettingsFuel);
        globalSettingsService.create(globalSettingsTax);

        SupplyDataResquet supplyDataResquet = new SupplyDataResquet(LocalDateTime.now(), new BigDecimal(200), poump.getId(), "fuel_price");


        // Assert
        assertThrows(Exception.class, () -> supplyService.supplyFuel(supplyDataResquet));
    }




}