package posto.abcd.api.services.globalSettings;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataRequest;
import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;
import posto.abcd.api.repository.globalSettings.GlobalSettingsRepository;

@Service
public class CreateGlobalSettingsService {

    private final GlobalSettingsRepository globalSettingsRepository;

    public CreateGlobalSettingsService(GlobalSettingsRepository globalSettingsRepository) {
        this.globalSettingsRepository = globalSettingsRepository;

    }

    @Transactional
    public GlobalSettingsEntity create(GlobalSettingsDataRequest globalSettingsDataRequest) {

        String value = globalSettingsDataRequest.value();

        if (value.contains(",") || value.contains(".")) {
            value = value.replace(',', '.');
        }

        var globalSettingsEntity = new GlobalSettingsEntity(globalSettingsDataRequest, value);

        return globalSettingsRepository.save(globalSettingsEntity);
    }
}
