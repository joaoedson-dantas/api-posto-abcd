package posto.abcd.api.repository.fuelPump;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;

public interface FuelPumpRespository extends JpaRepository<FuelPumpEntity, Long> {}
