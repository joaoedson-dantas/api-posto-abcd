package posto.abcd.api.controllers.supply;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.supply.SupplyDataResponse;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.services.supply.ListAllSuppliesService;
import posto.abcd.api.services.supply.FuelSupplyService;

import java.util.List;

@RestController
@RequestMapping("supply")
@CrossOrigin(origins = "*")
public class SupplyController {

    @Autowired
    private FuelSupplyService fuelSupplyService;

    @Autowired
    private ListAllSuppliesService listallSuppliesService;


    @PostMapping("/to-fuel")
    public ResponseEntity<SupplyDataResponse> toFuel(@RequestBody @Valid SupplyDataResquet supplyDataResquet, UriComponentsBuilder uriComponentsBuilder) {
        var supplyEntity = fuelSupplyService.supplyFuel(supplyDataResquet);

        var uri = uriComponentsBuilder.path("/to-fuel/{id}").buildAndExpand(supplyEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new SupplyDataResponse(supplyEntity));
    }

    @GetMapping("/all-supplies")
    public ResponseEntity<List<SupplyDataResponse>> findAll( ) {
        var allSuppliesList = listallSuppliesService.list().stream().toList();
        return ResponseEntity.ok(allSuppliesList);
    }

}
