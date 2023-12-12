package posto.abcd.api.controllers.user;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posto.abcd.api.dtos.user.AuthUserDTO;
import posto.abcd.api.services.user.AuthUserService;


@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/user")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid AuthUserDTO authUserDTO) {
        try {
           var result = this.authUserService.execute(authUserDTO);
           return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
