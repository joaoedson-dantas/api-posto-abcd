package posto.abcd.api.controllers.fuelTank;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.fuelTank.FuelTankDataList;
import posto.abcd.api.dtos.fuelTank.FuelTankDataRequest;
import posto.abcd.api.dtos.fuelTank.FuelTankDataResponse;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.services.fuelTank.CreateFuelTankService;
import posto.abcd.api.services.fuelTank.FindByFuelTypeService;
import posto.abcd.api.services.fuelTank.ListFuelTankService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fuel-tanks")
@CrossOrigin(origins = "*")
public class FuelTankController {

    @Autowired
    private ListFuelTankService listFuelTankService;

    @Autowired
    private CreateFuelTankService createFuelTankService;

    @Autowired
    private FindByFuelTypeService findByFuelType;

    @PostMapping
    public ResponseEntity<FuelTankDataResponse> registerTank(@RequestBody @Valid FuelTankDataRequest fuelTankData, UriComponentsBuilder uriBuilder) {
        var fuelTankEntity = createFuelTankService.create(fuelTankData);
        var uri = uriBuilder.path("/fuel-tanks/{id}").buildAndExpand(fuelTankEntity.getId()).toUri();
        return  ResponseEntity.created(uri).body(new FuelTankDataResponse(fuelTankEntity));
    }

    @GetMapping
    public ResponseEntity<List<FuelTankDataList>> listTanks() {
        var tanksList = listFuelTankService.list().stream().toList();
        return ResponseEntity.ok(tanksList);
    }

    @GetMapping("/fueltank/{name}")
    public ResponseEntity<Optional<FuelTankEntity>> findByFuelType(@PathVariable @Valid String name) {
        var tank = findByFuelType.findByFuelType(name);
        return ResponseEntity.ok(tank);
    }

}
