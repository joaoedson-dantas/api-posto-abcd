package posto.abcd.api.service.globalSettings;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataRequest;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataResponse;
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

        var globalSettingsEntity = new GlobalSettingsEntity(globalSettingsDataRequest);

        return globalSettingsRepository.save(globalSettingsEntity);
    }

    public Page<GlobalSettingsDataResponse> GetAllGlobalSettings(Pageable pagination) {
        return globalSettingsRepository.findAll(pagination).map(GlobalSettingsDataResponse::new);
    }

    public GlobalSettingsDataResponse getGlobalSettingsByKey(String key) {
        var settings = globalSettingsRepository.findByKey(key).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar a chave" + key));

        if (settings == null) {
            throw new EntityNotFoundException();
        }

        return new GlobalSettingsDataResponse(settings);
    }

    public GlobalSettingsDataResponse getGlobalSettingsById(Long id) {

        try {
            var settings = globalSettingsRepository.getReferenceById(id);
            return new GlobalSettingsDataResponse(settings);

        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException();

        }
    }
}
