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
import posto.abcd.api.dtos.fuelPump.FuelPumpIdRequest;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;
import posto.abcd.api.service.fuelPump.FuelPumpService;

@RestController
@RequestMapping("fuel-pumps")
public class FuelPumpController {

    @Autowired
    private FuelPumpService fuelPumpService;

    @PostMapping
    public ResponseEntity<FuelPumpDataResponse> createPump(@RequestBody @Valid FuelPumpDataRequest fuelPumpData, UriComponentsBuilder uriBuilder) {
        var fuelPumpEntity = fuelPumpService.createPump(fuelPumpData);

        var uri = uriBuilder.path("/fuel-pumps/{id}").buildAndExpand(fuelPumpEntity.getId()).toUri();
        return  ResponseEntity.created(uri).body(new FuelPumpDataResponse(fuelPumpEntity));
    }

    @GetMapping
    public ResponseEntity<Page<FuelPumpDataResponse>> getAllPumps(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        var pumpsList = fuelPumpService.GetAllPumpsFuel(pagination);
        return ResponseEntity.ok(pumpsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelPumpDataResponse> getPumpById(@PathVariable @Valid Long id) {
        System.out.println(id);
        var pump = fuelPumpService.getPumpById(id);
        return ResponseEntity.ok(new FuelPumpDataResponse(pump));
    }


}
