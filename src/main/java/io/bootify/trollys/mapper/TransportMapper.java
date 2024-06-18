package io.bootify.trollys.mapper;

import io.bootify.trollys.dto.TransportDTO;
import io.bootify.trollys.entity.Transport;

import java.util.stream.Collectors;

public class TransportMapper {


    public static TransportDTO toDTO(Transport trensp){
        TransportDTO dto = new TransportDTO();
        dto.setVin(trensp.getVin());
        dto.setInfoteh(trensp.getInfoteh());
        dto.setGarageNumber(trensp.getGarage_number());
        dto.setEquipmentList(trensp.getEquipmentList().stream()
                .map(EquipmentMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;

    }

    public static Transport toEntity(TransportDTO dto) {
        Transport transportets = new Transport();
        transportets.setVin(dto.getVin());
        transportets.setGarage_number(dto.getGarageNumber());
        transportets.setInfoteh(dto.getInfoteh());
        if (dto.getEquipmentList() != null) {
            transportets.setEquipmentList(dto.getEquipmentList().stream()
                    .map(EquipmentMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return transportets;
    }


}
