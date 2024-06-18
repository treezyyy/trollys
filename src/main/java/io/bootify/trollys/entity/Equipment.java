package io.bootify.trollys.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="equipment")
public class Equipment {


    @ManyToOne
    @JoinColumn(name = "transport_vin", nullable = false)
    @JsonBackReference // Обратная сторона отношения
    private Transport transport;

    @Column(nullable = false)
    private String name_equipment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serial_number;

    @Column(nullable = false)
    private String status;

}
