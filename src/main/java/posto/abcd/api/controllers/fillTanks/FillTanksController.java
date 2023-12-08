package posto.abcd.api.controllers.fillTanks;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity fillTank(@RequestBody @Valid FillTankDataRequest fillTankData, UriComponentsBuilder uriBuilder) {
        var fillTanksEntity = fillTankService.fillTank(fillTankData);

        var uri = uriBuilder.path("/fill-tanks/{id}").buildAndExpand(fillTanksEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new FillTankDataResponse(fillTanksEntity));

    }

}
