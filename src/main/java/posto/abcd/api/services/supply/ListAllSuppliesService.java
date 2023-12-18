package posto.abcd.api.services.supply;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.supply.SupplyDataResponse;
import posto.abcd.api.repository.supply.SupplyRepository;

import java.util.List;

@Service
public class ListAllSuppliesService {

    @Autowired
    private SupplyRepository repository;

    public List<SupplyDataResponse> list() {
        return repository.findAll().stream().map(SupplyDataResponse::new).toList();
    }
}
