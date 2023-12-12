package posto.abcd.api.controllers.supply;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.supply.SupplyDataResponse;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.services.supply.ListAllSuppliesService;
import posto.abcd.api.services.supply.FuelSupplyService;

@RestController
@RequestMapping("supply")
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

    @PostMapping("/all-supplies")
    public ResponseEntity<Page<SupplyDataResponse>> findAll(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        var allSuppliesList = listallSuppliesService.list(pagination);
        return ResponseEntity.ok(allSuppliesList);
    }

}
