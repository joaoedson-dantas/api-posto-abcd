package posto.abcd.api.repository.fillTank;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.fillTanks.FillTanksEntity;

public interface FillTankRepository extends JpaRepository<FillTanksEntity, Long> {}
