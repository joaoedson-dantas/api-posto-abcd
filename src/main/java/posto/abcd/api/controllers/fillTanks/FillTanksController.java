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
import posto.abcd.api.services.fillTank.FillTankService;
import posto.abcd.api.services.fillTank.ListTankFillingService;

import java.util.List;


@RestController
@RequestMapping("fill-tanks")
@CrossOrigin(origins = "*")
public class FillTanksController {

    @Autowired
    private FillTankService fillTankService;

    @Autowired
    private ListTankFillingService listTankFillingService;

    @PostMapping
    public ResponseEntity<FillTankDataResponse> fillTank(@RequestBody @Valid FillTankDataRequest fillTankData, UriComponentsBuilder uriBuilder) {
        var fillTanksEntity = fillTankService.fillTank(fillTankData);

        var uri = uriBuilder.path("/fill-tanks/{id}").buildAndExpand(fillTanksEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new FillTankDataResponse(fillTanksEntity));

    }

    @GetMapping
    public ResponseEntity<List<FillTankDataResponse>> listFillTanks(Pageable pagination) {
        var fillTanksList = listTankFillingService.list(pagination).stream().toList();
        return ResponseEntity.ok(fillTanksList);
    }

}
