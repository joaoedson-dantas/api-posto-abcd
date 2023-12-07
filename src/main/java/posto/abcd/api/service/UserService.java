package posto.abcd.api.service;

import org.springframework.stereotype.Service;
import posto.abcd.api.entity.User;
import posto.abcd.api.repository.UserRepository;

@Service // para ser um serviço no spring e assim ser passível de injecao tem que anotar com @serivce
public class UserService {
    // Aqui colocamos todas as opercões que iremos realizar. Use Case

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
       return userRepository.save(user);
    }

}
