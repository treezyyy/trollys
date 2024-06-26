package io.bootify.trollys.controller;

import io.bootify.trollys.dto.EquipmentDTO;
import io.bootify.trollys.entity.Equipment;
import io.bootify.trollys.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    // Вывод всех единиц
    @GetMapping("get_equipment")
    public ResponseEntity<List<EquipmentDTO>> readAll(){
        return new ResponseEntity<>(equipmentService.readAll(), HttpStatus.OK);
    }

    // Создание единицы оборудования
    @PostMapping("create_equipment")
    public ResponseEntity<Equipment> create(@RequestBody EquipmentDTO dto){
        return new ResponseEntity<>(equipmentService.create(dto), HttpStatus.OK);
    }

    // Обновление единицы оборудования
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable Long id, @RequestBody EquipmentDTO equipmentDTO) {
        return new ResponseEntity<>(equipmentService.update(id, equipmentDTO),HttpStatus.OK);
    }

    // Удаление единицы оборудования
    @DeleteMapping("del/{id}")
    public HttpStatus delete(@PathVariable Long id){
        equipmentService.delete(id);
        return HttpStatus.OK;
    }

    // Удаление всех единиц по Vin
    @DeleteMapping("/{vin}")
    public HttpStatus deleteByTransportVin(@PathVariable String vin){
        equipmentService.deleteByTransportVin(vin);
        return HttpStatus.OK;
    }

    // Вывод всех единиц оборудования по Vin
    @GetMapping("get_equipmentByVin/{vin}")
    public ResponseEntity<List<EquipmentDTO>> findByTransportVin(@PathVariable String vin){
        return new ResponseEntity<>(equipmentService.findByTransportVin(vin), HttpStatus.OK);
    }

    // Обновление статуса единицы оборудования
    @PutMapping("/update_status/{id}")
    public ResponseEntity<Equipment> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        return new ResponseEntity<>(equipmentService.updateStatus(id, status), HttpStatus.OK);
    }
}
