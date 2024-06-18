package io.bootify.trollys.mapper;

import io.bootify.trollys.dto.EquipmentDTO;
import io.bootify.trollys.entity.Equipment;
import io.bootify.trollys.entity.Transport;

public class EquipmentMapper {

    public static EquipmentDTO toDTO(Equipment equipment) {
        EquipmentDTO dto = new EquipmentDTO();
        dto.setId(equipment.getId());
        dto.setTransport_vin(equipment.getTransport().getVin());
        dto.setNameEquipment(equipment.getName_equipment());
        dto.setSerialNumber(equipment.getSerial_number());
        dto.setStatus(equipment.getStatus());
        return dto;
    }

    public static Equipment toEntity(EquipmentDTO dto) {
        Equipment equipment = new Equipment();
        equipment.setId(dto.getId());

        // Примерный код для установки существующего транспорта по vin
        Transport transport = new Transport();
        transport.setVin(dto.getTransport_vin());
        equipment.setTransport(transport);

        equipment.setName_equipment(dto.getNameEquipment());
        equipment.setSerial_number(dto.getSerialNumber());
        equipment.setStatus(dto.getStatus());
        return equipment;
    }

}
