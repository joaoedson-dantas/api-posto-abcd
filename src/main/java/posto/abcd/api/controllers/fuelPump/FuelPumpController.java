package posto.abcd.api.controllers.fuelPump;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataResponse;
import posto.abcd.api.services.fuelPump.CreateFuelPumpService;
import posto.abcd.api.services.fuelPump.ListAllFuelPoumps;

@RestController
@RequestMapping("fuel-pumps")
public class FuelPumpController {

    @Autowired
    private CreateFuelPumpService createFuelPumpService;

    @Autowired
    private ListAllFuelPoumps listAllFuelPoumps;

    @PostMapping
    public ResponseEntity<FuelPumpDataResponse> createPump(@RequestBody @Valid FuelPumpDataRequest fuelPumpData, UriComponentsBuilder uriBuilder) {
        var fuelPumpEntity = createFuelPumpService.createPump(fuelPumpData);

        var uri = uriBuilder.path("/fuel-pumps/{id}").buildAndExpand(fuelPumpEntity.getId()).toUri();
        return  ResponseEntity.created(uri).body(new FuelPumpDataResponse(fuelPumpEntity));
    }

    @GetMapping
    public ResponseEntity<Page<FuelPumpDataResponse>> listAllFuelPoumps(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        var pumpsList = listAllFuelPoumps.list(pagination);
        return ResponseEntity.ok(pumpsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelPumpDataResponse> getPumpById(@PathVariable @Valid Long id) {
        System.out.println(id);
        var pump = createFuelPumpService.getPumpById(id);
        return ResponseEntity.ok(new FuelPumpDataResponse(pump));
    }


}
