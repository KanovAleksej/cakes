package com.example.backend.service;

import com.example.backend.model.dto.CakeDto;
import com.example.backend.model.model.Cake;
import com.example.backend.repository.CakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeServiceImpl {

    private final CakeRepository cakeRepository;

    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    public List<Cake> getAll() {
        return cakeRepository.findAll();
    }

    public Cake save(CakeDto cakeDto) {
         return cakeRepository.save(new Cake(
                cakeDto.getTitle(),
                cakeDto.getDesc(),
                cakeDto.getImage()));
    }

    public void deleteById(Long id) {
        cakeRepository.deleteById(id);
    }
   public Cake findById(Long id){
       return cakeRepository.findById(id).orElseThrow(()-> new RuntimeException("id field must not be empty"));
   }
   public void updateCake(CakeDto cakeDto, Long id){
       Cake cakeToUpdate = findById(id);
       cakeToUpdate.setTitle(cakeDto.getTitle());
       cakeToUpdate.setDesc(cakeDto.getDesc());
       cakeToUpdate.setImage(cakeDto.getImage());
       cakeRepository.save(cakeToUpdate);
   }
}
