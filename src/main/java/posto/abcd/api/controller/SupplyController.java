package posto.abcd.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import posto.abcd.api.domain.supply.SupplyRegistrationData;

@RestController
@RequestMapping("supply")
public class SupplyController {

    @PostMapping
    public void register(@RequestBody SupplyRegistrationData data) {
        System.out.println(data);
    }

}
