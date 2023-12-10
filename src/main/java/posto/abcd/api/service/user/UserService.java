package posto.abcd.api.service.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.entity.user.UserEntity;
import posto.abcd.api.repository.user.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity create(UserEntity user) {

       return userRepository.save(user);
    }

    public UserEntity findUserId(Long id) {
       return userRepository.getReferenceById(id);
    }

}
