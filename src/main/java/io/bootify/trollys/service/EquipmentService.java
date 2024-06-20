package io.bootify.trollys.service;


import io.bootify.trollys.dto.EquipmentDTO;
import io.bootify.trollys.entity.Equipment;
import io.bootify.trollys.entity.Transport;
import io.bootify.trollys.mapper.EquipmentMapper;
import io.bootify.trollys.repos.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Transactional
    public List<EquipmentDTO> readAll() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        return equipmentList.stream()
                .map(EquipmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Создание единицы оборудования
    @Transactional
    public Equipment create(EquipmentDTO equipmentDTO) {
        Equipment equipment = EquipmentMapper.toEntity(equipmentDTO);
        //Equipment savedEquipment = equipmentRepository.save(equipment);
        //return EquipmentMapper.toDTO(savedEquipment);
        return equipmentRepository.save(equipment);

    }
    /*
    public EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO) {
        Equipment equipment = EquipmentMapper.toEntity(equipmentDTO);
        Equipment savedEquipment = equipmentRepository.save(equipment);
        return EquipmentMapper.toDTO(savedEquipment);
    }

     */

    // Обновление единицы оборудования
/*
    @Transactional
    public Transport update(Long id, EquipmentDTO equipmentDTO) {
        Equipment existingEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with id: " + id));

        existingEquipment.setName_equipment(equipmentDTO.getNameEquipment());
        existingEquipment.setSerial_number(equipmentDTO.getSerialNumber());
        existingEquipment.setStatus(equipmentDTO.getStatus());

        return equipmentRepository.save(existingEquipment).getTransport();
    }

 */
    @Transactional
    public EquipmentDTO update(Long id, EquipmentDTO equipmentDTO) {
        Equipment existingEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with id: " + id));

        Transport transport = new Transport();
        transport.setVin(equipmentDTO.getTransport_vin());
        existingEquipment.setTransport(transport);

        existingEquipment.setName_equipment(equipmentDTO.getNameEquipment());
        existingEquipment.setSerial_number(equipmentDTO.getSerialNumber());
        existingEquipment.setStatus(equipmentDTO.getStatus());

        Equipment updatedEquipment = equipmentRepository.save(existingEquipment);
        return EquipmentMapper.toDTO(updatedEquipment);
    }

    // Удаление единицы оборудования
    @Transactional
    public void delete(Long id) {
        Equipment equipmente = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with id: " + id));
        equipmentRepository.delete(equipmente);
    }


    @Transactional
    public void deleteByTransportVin(String transportVin) {
        List<Equipment> equipmentList = equipmentRepository.findByTransportVin(transportVin);
        System.out.println("Found " + equipmentList.size() + " equipment items for transport_vin: " + transportVin);
        equipmentRepository.deleteAll(equipmentList);
    }

}
