package posto.abcd.api.controllers.fuelTank;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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
    public ResponseEntity registerTank(@RequestBody @Valid FuelTankDataRequest fuelTankData, UriComponentsBuilder uriBuilder) {
        var fuelTankEntity = fuelTankService.create(fuelTankData);
        var uri = uriBuilder.path("/fuel-tanks/{id}").buildAndExpand(fuelTankEntity.getId()).toUri();
        return  ResponseEntity.created(uri).body(new FuelTankDataResponse(fuelTankEntity));
    }

    @GetMapping
    public ResponseEntity<List<FuelTankEntity>> listTanks() {
        var tanksList = fuelTankService.list();
        return ResponseEntity.ok(tanksList);
    }

}
