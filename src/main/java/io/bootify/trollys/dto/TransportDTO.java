package io.bootify.trollys.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransportDTO {
    private String vin;
    private String garageNumber;
    private String infoteh;
    private List<EquipmentDTO> equipmentList;
}
