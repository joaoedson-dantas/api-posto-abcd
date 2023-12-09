package posto.abcd.api.entity.globalSettings;


import jakarta.persistence.*;
import lombok.*;
import posto.abcd.api.dtos.globalSettings.GlobalSettingsDataRequest;

@Entity
@Table(name = "globalSettings")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class GlobalSettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String label;

    @Column(unique = true)
    @Setter
    private String key;

    @Setter
    private String value;

    public GlobalSettingsEntity(GlobalSettingsDataRequest globalSettingsDataRequest) {
        this.setLabel(globalSettingsDataRequest.label());
        this.setKey(globalSettingsDataRequest.key());
        this.setValue(globalSettingsDataRequest.value());
    }
}
