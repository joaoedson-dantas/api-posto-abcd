package posto.abcd.api.repository.globalSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;

public interface GlobalSettingsRepository extends JpaRepository<GlobalSettingsEntity, Long> {}
