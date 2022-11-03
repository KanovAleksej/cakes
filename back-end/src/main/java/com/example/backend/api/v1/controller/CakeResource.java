package com.example.backend.api.v1.controller;

import com.example.backend.model.dto.CakeDto;
import com.example.backend.model.model.Cake;
import com.example.backend.service.CakeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CakeResource.BASE_URL)
public class CakeResource {

    public static final String BASE_URL = "api/v1/cakes";

    private final CakeServiceImpl cakeService;

    public CakeResource(CakeServiceImpl cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping()
    public ResponseEntity<List<CakeDto>> getCakes() {
        List<CakeDto> cakeDtos = cakeService.getAll().stream().map(cake -> new CakeDto(
                cake.getId(),
                cake.getTitle(),
                cake.getDesc(),
                cake.getImage())).collect(Collectors.toList());

        return new ResponseEntity<>(cakeDtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CakeDto> saveCake(@RequestBody @Valid CakeDto cakeDto) {
        Cake cake = cakeService.save(cakeDto);
        return new ResponseEntity<>(new CakeDto(cake), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        cakeService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateCake(@RequestBody CakeDto cakeDto, @PathVariable Long id) {
        cakeService.updateCake(cakeDto, id);
    }
}
