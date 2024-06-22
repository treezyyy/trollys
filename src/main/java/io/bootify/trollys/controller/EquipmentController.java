package io.bootify.trollys.controller;


import io.bootify.trollys.dto.EquipmentDTO;
import io.bootify.trollys.entity.Equipment;
import io.bootify.trollys.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping("get_equipment")
    public ResponseEntity<List<EquipmentDTO>> readAll(){
        return new ResponseEntity<>(equipmentService.readAll(), HttpStatus.OK);
    }
    @PostMapping("create_equipment")
    public ResponseEntity<Equipment> create(@RequestBody EquipmentDTO dto){
        return new ResponseEntity<>(equipmentService.create(dto), HttpStatus.OK);
    }


/*
    @DeleteMapping("/{vin}")
    public HttpStatus deleteByTransportVin(@PathVariable String vin){
        equipmentService.deleteByTransportVin(vin);
        return HttpStatus.OK;
    }
*/


    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> update(@PathVariable Long id, @RequestBody EquipmentDTO equipmentDTO) {
       return new ResponseEntity<>(equipmentService.update(id, equipmentDTO),HttpStatus.OK);
        // EquipmentDTO updatedEquipment = equipmentService.update(id, equipmentDTO);
        //return ResponseEntity.ok(updatedEquipment);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        equipmentService.delete(id);
        return HttpStatus.OK;
    }
}