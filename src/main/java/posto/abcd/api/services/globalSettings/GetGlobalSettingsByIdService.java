package posto.abcd.api.services.globalSettings;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataResponse;
import posto.abcd.api.repository.globalSettings.GlobalSettingsRepository;

@Service
public class GetGlobalSettingsByIdService {

    @Autowired
    private GlobalSettingsRepository repository;

    @Transactional
    public GlobalSettingsDataResponse getById(Long id) {

        try {
            var settings = repository.getReferenceById(id);
            return new GlobalSettingsDataResponse(settings);

        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException();

        }
    }
}
