package posto.abcd.api.controllers.fillTanks;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.fillTank.FillTankDataRequest;
import posto.abcd.api.dtos.fillTank.FillTankDataResponse;
import posto.abcd.api.service.fillTank.FillTankService;



@RestController
@RequestMapping("fill-tanks")
public class FillTanksController {

    @Autowired
    private FillTankService fillTankService;

    @PostMapping
    public ResponseEntity<FillTankDataResponse> fillTank(@RequestBody @Valid FillTankDataRequest fillTankData, UriComponentsBuilder uriBuilder) {
        var fillTanksEntity = fillTankService.fillTank(fillTankData);

        var uri = uriBuilder.path("/fill-tanks/{id}").buildAndExpand(fillTanksEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new FillTankDataResponse(fillTanksEntity));

    }

    @GetMapping
    public ResponseEntity<Page<FillTankDataResponse>> listFillTanks(@PageableDefault(size = 10, page = 0, sort = {"date"}) Pageable pagination) {
        var fillTanksList = fillTankService.list(pagination);
        return ResponseEntity.ok(fillTanksList);
    }

}
