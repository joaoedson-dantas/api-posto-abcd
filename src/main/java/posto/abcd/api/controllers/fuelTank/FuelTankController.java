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
import posto.abcd.api.service.fuelTank.FuelTankService;

import java.util.List;

@RestController
@RequestMapping("/fuel-tanks")
public class FuelTankController {

    @Autowired
    private FuelTankService fuelTankService;

    @PostMapping
    public ResponseEntity<FuelTankDataResponse> registerTank(@RequestBody @Valid FuelTankDataRequest fuelTankData, UriComponentsBuilder uriBuilder) {
        var fuelTankEntity = fuelTankService.create(fuelTankData);
        var uri = uriBuilder.path("/fuel-tanks/{id}").buildAndExpand(fuelTankEntity.getId()).toUri();
        return  ResponseEntity.created(uri).body(new FuelTankDataResponse(fuelTankEntity));
    }

    @GetMapping
    public ResponseEntity<Page<FuelTankDataList>> listTanks(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var tanksList = fuelTankService.list(paginacao);
        return ResponseEntity.ok(tanksList);
    }

    @GetMapping("/fueltank/{name}")
    public ResponseEntity<FuelTankEntity> findByFuelType(@PathVariable @Valid String name) {
        var tank = fuelTankService.findByFuelType(name);
        return ResponseEntity.ok(tank);
    }

}
