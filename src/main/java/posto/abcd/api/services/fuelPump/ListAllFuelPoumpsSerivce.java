package posto.abcd.api.services.fuelPump;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataResponse;
import posto.abcd.api.repository.fuelPump.FuelPumpRespository;

@Service
public class ListAllFuelPoumpsSerivce {

    @Autowired
    private FuelPumpRespository repository;

    public Page<FuelPumpDataResponse> list(Pageable pagination) {
        return repository.findAll(pagination).map(FuelPumpDataResponse::new);
    }
}
