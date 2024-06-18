package io.bootify.trollys.dto;

import lombok.Data;

@Data
public class EquipmentDTO {

    private Long id;
    private String transport_vin;
    private String nameEquipment;
    private String serialNumber;
    private String status;

}
