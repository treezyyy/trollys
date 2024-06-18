package io.bootify.trollys.service;


import io.bootify.trollys.dto.TransportDTO;
import io.bootify.trollys.entity.Transport;
import io.bootify.trollys.mapper.EquipmentMapper;
import io.bootify.trollys.mapper.TransportMapper;
import io.bootify.trollys.repos.TransportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransportService {

    private final TransportRepository transportRepository;

    @Transactional
    public Transport create(TransportDTO dto){
        Transport transportet = TransportMapper.toEntity(dto);
        return transportRepository.save(transportet);
    }

    @Transactional
    public List<TransportDTO> readAll(){
        List<Transport> transportList = transportRepository.findAll();
        return transportList.stream()
                .map(TransportMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public Transport update(String vin, TransportDTO dto) {
        Transport existingTransport = transportRepository.findById(vin)
                .orElseThrow(() -> new EntityNotFoundException("Transport not found with vin: " + vin));

        existingTransport.setGarage_number(dto.getGarageNumber());
        existingTransport.setInfoteh(dto.getInfoteh());

        if (dto.getEquipmentList() != null) {
            existingTransport.setEquipmentList(dto.getEquipmentList().stream()
                    .map(EquipmentMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return transportRepository.save(existingTransport);
    }
    @Transactional
    public void delete(String vin) {
        Transport transports = transportRepository.findById(vin)
                .orElseThrow(() -> new EntityNotFoundException("Transport not found with vin: " + vin));
        transportRepository.delete(transports);
    }
}
