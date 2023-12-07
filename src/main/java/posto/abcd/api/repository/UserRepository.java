package posto.abcd.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import posto.abcd.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
