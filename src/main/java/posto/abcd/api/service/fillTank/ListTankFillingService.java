package posto.abcd.api.service.fillTank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.fillTank.FillTankDataResponse;
import posto.abcd.api.repository.fillTank.FillTankRepository;


@Service
public class ListTankFillingService {

    @Autowired
    private FillTankRepository repository;

    public Page<FillTankDataResponse> list(Pageable pagination) {
        return repository.findAll(pagination).map(FillTankDataResponse::new);
    }

}
