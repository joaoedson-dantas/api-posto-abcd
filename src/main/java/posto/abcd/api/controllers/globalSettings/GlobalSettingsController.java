package posto.abcd.api.controllers.globalSettings;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataRequest;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataResponse;
import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;
import posto.abcd.api.service.globalSettings.GlobalSettingsService;

@RestController
@RequestMapping("/global-settings")
public class GlobalSettingsController {

    @Autowired
    private GlobalSettingsService globalSettingsService;

    @PostMapping
    public ResponseEntity<GlobalSettingsDataResponse> create(@RequestBody @Valid GlobalSettingsDataRequest globalSettingsDataRequest, UriComponentsBuilder uriBuilder) {
        var globalSettingsEntity = globalSettingsService.create(globalSettingsDataRequest);

        var uri = uriBuilder.path("/global-settings/{id}").buildAndExpand(globalSettingsEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new GlobalSettingsDataResponse(globalSettingsEntity));
    }

}
