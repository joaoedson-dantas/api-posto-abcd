package posto.abcd.api.controllers.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import posto.abcd.api.dtos.user.UserDataResponse;
import posto.abcd.api.dtos.user.UserDataRequest;
import posto.abcd.api.entity.user.UserEntity;
import posto.abcd.api.service.user.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody @Valid UserDataRequest userData, UriComponentsBuilder uriBulder) {

        // convertendo de USerDataRequest para userEntity, pois o metodo create recebe uma Entity;
        var userEntity = new UserEntity(userData);
        var user = userService.create(userEntity);

        // URI representa o endereço (tem que ser o endereço da nossa API) -> O spring vai criar o cabeçalho location baseado na uri
        var uri = uriBulder.path("/users/{id}").buildAndExpand(user.getId()).toUri(); // criar o objeto uri

        return ResponseEntity.created(uri).body(new UserDataResponse(user));
    }

    @GetMapping("/{id}") // passando o parametro na rota
    public ResponseEntity findUser(@PathVariable Long id) {
        var user = userService.findUserId(id);
        return ResponseEntity.ok(new UserDataResponse(user));
    }
}
