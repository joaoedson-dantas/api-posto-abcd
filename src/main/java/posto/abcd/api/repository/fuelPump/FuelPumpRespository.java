package posto.abcd.api.repository.fuelPump;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;
@Repository
public interface FuelPumpRespository extends JpaRepository<FuelPumpEntity, Long> {}
