package posto.abcd.api.services.globalSettings;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.globalSettings.SettingsUpdateDataRequest;
import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;
import posto.abcd.api.infra.exceptions.GlobalSettingsNotFoundException;
import posto.abcd.api.repository.globalSettings.GlobalSettingsRepository;

import java.util.Optional;

@Service
public class UpdateGlobalSettingsService {

    @Autowired
    private GlobalSettingsRepository globalSettingsRepository;

    @Autowired
    private GetGlobalSettingsByIdService getGlobalSettingsByIdService;

    @Transactional
    public GlobalSettingsEntity update(SettingsUpdateDataRequest settingsUpdateDataRequest) {

        Optional<GlobalSettingsEntity> optionalSettings = globalSettingsRepository.findById(settingsUpdateDataRequest.id());


        if (optionalSettings.isPresent()) {
            GlobalSettingsEntity existingSettings = optionalSettings.get();
            existingSettings.setLabel(settingsUpdateDataRequest.label());
            existingSettings.setValue(settingsUpdateDataRequest.value());

            return globalSettingsRepository.save(existingSettings);
        } else {
            throw new IllegalArgumentException("Configuração global não encontrada com o ID fornecido: " + settingsUpdateDataRequest.id());
        }
    }

}

