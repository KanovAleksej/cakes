package com.example.backend;

import com.example.backend.model.dto.CakeDto;
import com.example.backend.model.model.Cake;
import com.example.backend.repository.CakeRepository;
import com.example.backend.service.CakeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CakeServiceImplTest {
    @Autowired
    protected CakeServiceImpl cakeService;
    @Autowired
    protected CakeRepository repository;

    @Test
    void cakeGetsSaved() {
        CakeDto cake = new CakeDto();
        cake.setTitle("lemon");
        cake.setDesc("tasty");

        Cake savedCake = cakeService.save(cake);

        assertThat(savedCake.getDesc(), equalTo(cake.getDesc()));
        assertThat(savedCake.getTitle(), equalTo(cake.getTitle()));
    }

    @Test
    void getAllFunctionWorks() {
        CakeDto cake = new CakeDto();

        int numberOfCakes = cakeService.getAll().size();
        cakeService.save(cake);
        List<Cake> foundCakes = cakeService.getAll();

        assertThat(foundCakes.size(), equalTo(numberOfCakes + 1));
    }

    @Test
    void whenFindByIdEmpty_throwsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            cakeService.findById(null);
        });

        String expectedMessage = "given id must not be null!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteByIdRemovesRecord() {
        CakeDto cake = new CakeDto();
        cake.setTitle("lemon");
        cake.setDesc("tasty");
        Cake savedCake = cakeService.save(cake);

        cakeService.deleteById(savedCake.getId());
        Optional<Cake> deletedCake = repository.findById(savedCake.getId());

        assertFalse(deletedCake.isPresent());
    }

    @Test
    void updateCakeIsMade_whenCalled() {
        CakeDto cake = new CakeDto();
        cake.setTitle("lemon");
        cake.setDesc("tasty");
        Cake savedCake = cakeService.save(cake);

        CakeDto newCakeDetails = new CakeDto();
        newCakeDetails.setTitle("newCakeTitle");
        newCakeDetails.setDesc("FantasticDescription");
        newCakeDetails.setImage("mostAmazingImage");

        cakeService.updateCake(newCakeDetails, savedCake.getId());
        Optional<Cake> updatedCake = repository.findById(savedCake.getId());

        assertTrue(updatedCake.isPresent());
        assertThat(updatedCake.get().getTitle(), equalTo(newCakeDetails.getTitle()));
        assertThat(updatedCake.get().getImage(), equalTo(newCakeDetails.getImage()));
        assertThat(updatedCake.get().getDesc(), equalTo(newCakeDetails.getDesc()));
    }
}