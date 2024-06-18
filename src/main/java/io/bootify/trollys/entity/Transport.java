package io.bootify.trollys.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transport")
public class Transport {


    @Id
    @Column(nullable = false)
    private String vin;

    @Column(nullable = false)
    private String garage_number;

    @Column(nullable = false)
    private String infoteh;

    // Используем новое поле для маппинга с Equipment
    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipmentList = new ArrayList<>();

}
