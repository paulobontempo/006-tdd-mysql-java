package com.bontempo.tddmysqljava;

import com.bontempo.tddmysqljava.models.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	RestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}

	/**
	 * Here we test that we can get all the cars in the database
	 * using the GET method
	 */
	@Test
	public void testGetAllCars() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/cars", HttpMethod.GET, entity, String.class);

		Assertions.assertNotNull(response.getBody());
	}

	/**
	 * Here we test that we can fetch a single car using its id
	 */
	@Test
	public void testGetCarById() {
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/1", Car.class);
		Assertions.assertNotNull(car);
	}

	/**
	 * Here we test that we can create a car using the POST method
	 */
	@Test
	public void testCreateCar() {
		Car car = new Car();
		car.setCarName("Car01");
		car.setDoors(4);

		ResponseEntity<Car> postResponse = restTemplate.postForEntity(getRootUrl() + "/cars", car, Car.class);

		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateCar() {
		int id = 1;
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		car.setCarName("Car02");
		car.setDoors(8);

		restTemplate.put(getRootUrl() + "/cars/" + id, car);

		Car updatedCar = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		Assertions.assertNotNull(updatedCar);
	}


}
