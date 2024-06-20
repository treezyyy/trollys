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

    @DeleteMapping("/{vin}")
    public HttpStatus deleteByVin(@PathVariable String vin){
        equipmentService.deleteByTransportVin(vin);
        return HttpStatus.OK;
    }

}
