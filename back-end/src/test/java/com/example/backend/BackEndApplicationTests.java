package com.example.backend;

import com.example.backend.model.dto.CakeDto;
import com.example.backend.model.model.Cake;
import com.example.backend.repository.CakeRepository;
import com.example.backend.service.CakeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CakeServiceImplTest {
	@Autowired
	protected CakeServiceImpl cakeService;

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
	void getAllFunctionWorks(){
		CakeDto cake = new CakeDto();
		int numberOfCakes =  cakeService.getAll().size();
		List<Cake> foundCakes = cakeService.getAll();
		Cake savedCake = cakeService.save(cake);

		assertThat(foundCakes.size(), equalTo(numberOfCakes + 1));
	}

	@Test
	void findByIdFunctionOperates(){
		Exception exception = assertThrows(RuntimeException.class, () -> {
			Long.parseLong("");
		});
		String expectedMessage = "id field must not be empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));


	}
}