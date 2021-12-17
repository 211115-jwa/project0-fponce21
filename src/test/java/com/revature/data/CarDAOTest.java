package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;
import org.junit.jupiter.api.Test;
import com.revature.beans.Car;
import com.revature.beans.Person;
import com.revature.data.postgres.CarPostgres;

public class CarDAOTest {

	private CarDAO carDao = new CarPostgres();
	
	@Test
	public void createTest() {
		Car create = new Car();
		assertNotEquals(1, carDao.create(create));
		
	}
	
	
	@Test
	public void getValidCarById()
	{
		int idInput = 3;
		Car actual = carDao.getById(3);
		assertEquals(idInput, actual.getId());
	}
	
	
	@Test
	public void getByIdWhenIdDoesNotExists() {
		int idInput = -1;
		Car petOutput = carDao.getById(idInput);
		assertNull(petOutput);
	}
	
	@Test
	public void getAll() {
		Set<Car> actual = carDao.getAll();
		assertEquals(carDao.getAll(), actual);
	}
	
	@Test
	public void addNewCar() {
		Car newCar = new Car();
		System.out.println(newCar);
		
		int generatedId = carDao.create(newCar);
		
		assertNotEquals(0, generatedId);
		System.out.println(generatedId);
	}
	
	@Test
	public void testUpdate() {
		Car carUp = carDao.getById(2);
		carUp.setMake("Toyota");
		carDao.update(carUp);
		assertEquals("Toyota",carDao.getById(2).getMake());	
	}
	
	@Test
	// cahnge ID bc deleted ID: 1, how do i create an id:1again?
	public void testDelete() {
		Car carDelete = carDao.getById(5);
		carDelete.setMake("Toyota");
		carDao.delete(carDelete);
		assertNotEquals("Toyota",carDao.getById(5).getMake());	
	}
	
}
