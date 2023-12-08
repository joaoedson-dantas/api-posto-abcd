package posto.abcd.api.service.fuelTank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.fuelTank.FuelTankDataRequest;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import posto.abcd.api.repository.fuelTank.FuelTankRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuelTankService {


    private FuelTankRepository fuelTankRepository;

    public FuelTankService(FuelTankRepository fuelTankRepository) {
        this.fuelTankRepository = fuelTankRepository;
    }



    @Transactional
    public FuelTankEntity create(FuelTankDataRequest fuelTankDataRequest) {
        var fuelTankEntity = new FuelTankEntity(fuelTankDataRequest, LocalDateTime.now());
        fuelTankRepository.save(fuelTankEntity);
        return fuelTankEntity;
    }

    public List<FuelTankEntity> list() {
        return fuelTankRepository.findAll();
    }



}

//       try {
//            fuelTankRepository.save(fuelTankEntity);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao salvar o tanque de combustível", e);
//        }
