package posto.abcd.api.services.globalSettings;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;
import posto.abcd.api.infra.exceptions.GlobalSettingsNotFoundException;
import posto.abcd.api.repository.globalSettings.GlobalSettingsRepository;

@Service
public class UpdateGlobalSettingsService {

    @Autowired
    private GlobalSettingsRepository repository;

    @Transactional
    public GlobalSettingsEntity update(GlobalSettingsEntity globalSettings) {

        if (!repository.existsById(globalSettings.getId())) {
            throw new GlobalSettingsNotFoundException("Global Settings com ID " + globalSettings.getId() + " não encontrado.");
        }

        return repository.save(globalSettings);
    }

}
