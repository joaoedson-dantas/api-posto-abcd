package posto.abcd.api.controllers.supply;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.supply.SupplyDataResponse;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.service.supply.SupplyService;

@RestController
@RequestMapping("supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;


    @PostMapping("/to-fuel")
    public ResponseEntity<SupplyDataResponse> toFuel(@RequestBody @Valid SupplyDataResquet supplyDataResquet, UriComponentsBuilder uriComponentsBuilder) {
        var supplyEntity = supplyService.supplyFuel(supplyDataResquet);

        var uri = uriComponentsBuilder.path("/to-fuel/{id}").buildAndExpand(supplyEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new SupplyDataResponse(supplyEntity));
    }


}
