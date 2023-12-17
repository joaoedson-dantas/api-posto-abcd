package posto.abcd.api.services.globalSettings;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataResponse;
import posto.abcd.api.repository.globalSettings.GlobalSettingsRepository;

@Service
public class GetGlobalSettingsByKeyService {

    @Autowired
    private GlobalSettingsRepository repository;

    public GlobalSettingsDataResponse getByKey(String key) {
        System.out.println(key);
        var settings = repository.findByKey(key)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar a chave " + key));

        return new GlobalSettingsDataResponse(settings);
    }
}
