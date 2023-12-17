package posto.abcd.api.controllers.globalSettings;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataRequest;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataResponse;

import posto.abcd.api.dtos.globalSettings.SettingsUpdateDataRequest;
import posto.abcd.api.services.globalSettings.*;

import java.util.List;

@RestController
@RequestMapping("/global-settings")
@CrossOrigin(origins = "*")
public class GlobalSettingsController {

    private final CreateGlobalSettingsService createGlobalSettingsService;
    private final GetGlobalSettingsByKeyService getGlobalSettingsByKeyService;
    private final GetGlobalSettingsByIdService getGlobalSettingsByIdService;
    private final ListAllGlobalSettings listAllGlobalSettings;
    private final UpdateGlobalSettingsService updateGlobalSettingsService;

    public  GlobalSettingsController(CreateGlobalSettingsService createGlobalSettingsService, GetGlobalSettingsByKeyService getGlobalSettingsByKeyService, GetGlobalSettingsByIdService getGlobalSettingsByIdService, ListAllGlobalSettings listAllGlobalSettings, UpdateGlobalSettingsService updateGlobalSettingsService) {
        this.createGlobalSettingsService = createGlobalSettingsService;
        this.getGlobalSettingsByKeyService = getGlobalSettingsByKeyService;
        this.getGlobalSettingsByIdService = getGlobalSettingsByIdService;
        this.listAllGlobalSettings = listAllGlobalSettings;
        this.updateGlobalSettingsService = updateGlobalSettingsService;
    }


    @PostMapping
    public ResponseEntity<GlobalSettingsDataResponse> create(@RequestBody @Valid GlobalSettingsDataRequest globalSettingsDataRequest, UriComponentsBuilder uriBuilder) {
        var globalSettingsEntity = createGlobalSettingsService.create(globalSettingsDataRequest);

        var uri = uriBuilder.path("/global-settings/{id}").buildAndExpand(globalSettingsEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new GlobalSettingsDataResponse(globalSettingsEntity));
    }

    @GetMapping
    public ResponseEntity<List<GlobalSettingsDataResponse>> getAllGlobalSettings(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        var globalSettingsList = listAllGlobalSettings.list(pagination);
        return ResponseEntity.ok(globalSettingsList.stream().toList());
    }

    @GetMapping("/key/{key}")
    public ResponseEntity<GlobalSettingsDataResponse> getGlobalSettingsByKey(@PathVariable String key) {
        var settings = getGlobalSettingsByKeyService.getByKey(key);
        return ResponseEntity.ok(settings);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GlobalSettingsDataResponse> getGlobalSettingsById(@PathVariable Long id) {
        var settings = getGlobalSettingsByIdService.getById(id);
        return ResponseEntity.ok(settings);
    }

    @PutMapping()
    public ResponseEntity<GlobalSettingsDataResponse> editGlobalSettingById(@RequestBody @Valid SettingsUpdateDataRequest settingsUpdateDataRequest) {
        var settingEntity = getGlobalSettingsByIdService.getById(settingsUpdateDataRequest.id());
        var settingUpdated =  updateGlobalSettingsService.update(settingsUpdateDataRequest);
        return ResponseEntity.ok(new GlobalSettingsDataResponse(settingUpdated));
    }
}
