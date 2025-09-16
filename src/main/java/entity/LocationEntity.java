package entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "latitude", nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;
}
