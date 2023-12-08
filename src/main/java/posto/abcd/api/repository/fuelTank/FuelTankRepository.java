package posto.abcd.api.repository.fuelTank;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;

import java.util.Optional;

public interface FuelTankRepository extends JpaRepository<FuelTankEntity, Long> {
    Optional<FuelTankEntity> findByName(String name);
}
