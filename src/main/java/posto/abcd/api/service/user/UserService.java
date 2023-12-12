package posto.abcd.api.service.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.entity.user.UserEntity;
import posto.abcd.api.infra.exceptions.UserAlreadyExistsException;
import posto.abcd.api.repository.user.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserEntity create(UserEntity user) {

        if(userRepository.existsByLogin(user.getLogin())) {
            throw new UserAlreadyExistsException(user.getLogin());
        }

        var password = passwordEncoder.encode(user.getPassword_hash());
        user.setPassword_hash(password);

       return userRepository.save(user);
    }

    public UserEntity findUserId(Long id) {
       return userRepository.getReferenceById(id);
    }

}
