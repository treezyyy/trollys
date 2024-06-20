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

    @PostMapping("create_transport")
    public ResponseEntity<Transport> create(@RequestBody TransportDTO dto){
        return new ResponseEntity<>(transportService.create(dto), HttpStatus.OK);
    }

    @GetMapping("get_transports")
    public ResponseEntity<List<TransportDTO>> readAll(){
        return new ResponseEntity<>(transportService.readAll(), HttpStatus.OK);
    }
//пример
    @GetMapping("get_infoteh/{vin}")
    public ResponseEntity<String> findByVin(@PathVariable String vin){
        return new ResponseEntity<>(transportService.findByVin(vin), HttpStatus.OK);
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Transport> update(@PathVariable String vin, @RequestBody TransportDTO transp){
        return new ResponseEntity<>(transportService.update(vin, transp), HttpStatus.OK);
    }

    @DeleteMapping("/{vin}")
    public HttpStatus delete(@PathVariable String vin){
        transportService.delete(vin);
        return HttpStatus.OK;
    }
}
