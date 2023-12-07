package posto.abcd.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
