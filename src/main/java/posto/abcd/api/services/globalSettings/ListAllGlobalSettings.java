package posto.abcd.api.services.globalSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataResponse;
import posto.abcd.api.repository.globalSettings.GlobalSettingsRepository;

@Service
public class ListAllGlobalSettings {

    @Autowired
    private GlobalSettingsRepository repository;

    public Page<GlobalSettingsDataResponse> list(Pageable pagination) {
        return repository.findAll(pagination).map(GlobalSettingsDataResponse::new);
    }
}
