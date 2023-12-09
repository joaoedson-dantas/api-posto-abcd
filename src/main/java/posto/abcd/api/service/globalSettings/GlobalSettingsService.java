package posto.abcd.api.service.globalSettings;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataRequest;
import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;
import posto.abcd.api.repository.globalSettings.GlobalSettingsRepository;

@Service
public class GlobalSettingsService {

    private final GlobalSettingsRepository globalSettingsRepository;

    public GlobalSettingsService(GlobalSettingsRepository globalSettingsRepository) {
        this.globalSettingsRepository = globalSettingsRepository;

    }

    @Transactional
    public GlobalSettingsEntity create(GlobalSettingsDataRequest globalSettingsDataRequest) {
        System.out.println("oi");
        var globalSettingsEntity = new GlobalSettingsEntity(globalSettingsDataRequest);

        return globalSettingsRepository.save(globalSettingsEntity);
    }
}
