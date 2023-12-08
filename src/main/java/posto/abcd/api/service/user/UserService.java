package posto.abcd.api.service.user;

import org.springframework.stereotype.Service;
import posto.abcd.api.entity.user.UserEntity;
import posto.abcd.api.repository.user.UserRepository;

@Service // para ser um serviço no spring e assim ser passível de injecao tem que anotar com @serivce
public class UserService {
    // Aqui colocamos todas as opercões que iremos realizar. Use Case

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity create(UserEntity user) {
       return userRepository.save(user);
    }

    public UserEntity findUserId(Long id) {
       return userRepository.getReferenceById(id);
    }

}
