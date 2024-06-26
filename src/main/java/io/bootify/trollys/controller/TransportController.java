package io.bootify.trollys.controller;

import io.bootify.trollys.dto.TransportDTO;
import io.bootify.trollys.entity.Transport;
import io.bootify.trollys.service.TransportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transport")
public class TransportController {

    private final TransportService transportService;

        // Вывод всех единиц
    @GetMapping("get_transports")
    public ResponseEntity<List<TransportDTO>> readAll(){
        return new ResponseEntity<>(transportService.readAll(), HttpStatus.OK);
    }

    // Создание тренспорта
    // Создание транспорта с обработкой исключения на дублирование VIN
    @PostMapping("create_transport")
    public ResponseEntity<?> create(@RequestBody TransportDTO dto){
        try {
            Transport transport = transportService.create(dto);
            return new ResponseEntity<>(transport, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // Обновление тренспорта
    @PutMapping("/{vin}")
    public ResponseEntity<Transport> update(@PathVariable String vin, @RequestBody TransportDTO transp){
        return new ResponseEntity<>(transportService.update(vin, transp), HttpStatus.OK);
    }

    // Удаление тренспорта
    @DeleteMapping("/{vin}")
    public HttpStatus delete(@PathVariable String vin){
        transportService.delete(vin);
        return HttpStatus.OK;
    }

    //Поиск  infoteh по Vin
    @GetMapping("get_infoteh/{vin}")
    public ResponseEntity<String> findByVin(@PathVariable String vin){
        return new ResponseEntity<>(transportService.findByVin(vin), HttpStatus.OK);
    }

}
