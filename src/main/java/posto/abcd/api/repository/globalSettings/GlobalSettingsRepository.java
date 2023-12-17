package posto.abcd.api.repository.globalSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;

import java.util.Optional;

public interface GlobalSettingsRepository extends JpaRepository<GlobalSettingsEntity, Long> {
    Optional<GlobalSettingsEntity> findByKey(String key);
}