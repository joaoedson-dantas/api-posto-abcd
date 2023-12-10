package posto.abcd.api.dtos.globalSettings;

import posto.abcd.api.entity.globalSettings.GlobalSettingsEntity;
import posto.abcd.api.entity.supply.SupplyEntity;

public record GlobalSettingsDataResponse(
        Long id,
        String label,
        String key,
        String value
){
    public GlobalSettingsDataResponse(GlobalSettingsEntity globalSettingsEntity) {
        this(globalSettingsEntity.getId(), globalSettingsEntity.getLabel(), globalSettingsEntity.getKey(), globalSettingsEntity.getValue());
    }

}
