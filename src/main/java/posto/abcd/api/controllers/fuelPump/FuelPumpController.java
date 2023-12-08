package posto.abcd.api.controllers.fuelPump;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataResponse;
import posto.abcd.api.service.fuelPump.FuelPumpService;

@RestController
@RequestMapping("fuel-pumps")
public class FuelPumpController {

    @Autowired
    private FuelPumpService fuelPumpService;

    @PostMapping
    public ResponseEntity registerTank(@RequestBody @Valid FuelPumpDataRequest fuelPumpData, UriComponentsBuilder uriBuilder) {
        var fuelPumpEntity = fuelPumpService.createPump(fuelPumpData);
        var uri = uriBuilder.path("/fuel-pumps/{id}").buildAndExpand(fuelPumpEntity.getId()).toUri();
        return  ResponseEntity.created(uri).body(new FuelPumpDataResponse(fuelPumpEntity.getId(), fuelPumpEntity.getName(), fuelPumpEntity.getFuelTankEntity().getId()));
    }
}
