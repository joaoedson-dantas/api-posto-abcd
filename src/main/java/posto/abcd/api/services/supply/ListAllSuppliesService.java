package posto.abcd.api.services.supply;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.supply.SupplyDataResponse;
import posto.abcd.api.repository.supply.SupplyRepository;

@Service
public class ListAllSuppliesService {

    @Autowired
    private SupplyRepository repository;

    public Page<SupplyDataResponse> list(Pageable pagination) {
        return repository.findAll(pagination).map(SupplyDataResponse::new);
    }
}
