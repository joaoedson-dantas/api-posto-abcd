package posto.abcd.api.repository.supply;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.supply.SupplyEntity;

public interface SupplyRepository extends JpaRepository<SupplyEntity, Long> {}
