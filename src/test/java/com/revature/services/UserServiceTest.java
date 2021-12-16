package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Car;
import com.revature.beans.Person;
import com.revature.data.CarDAO;
import com.revature.data.PersonDAO;

// telling junit were using mockito
@ExtendWith(MockitoExtension.class)

public class UserServiceTest {
	// tell mockito what classes/interfaces were mocking
	
	@Mock
	private CarDAO carDao;
	
	@Mock
	private PersonDAO personDao;
	
	// tell mockito to override the regular DAOs w/our mock DAOs
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	
	private static Set<Car> mockAvailableCars;
	
	@BeforeAll
	public static void mockAvailableCarsSetup() {
		mockAvailableCars = new HashSet<>();
		
		for (int i=1; i<=5; i++) {
			Car car = new Car();
			car.setId(i);
			if (i<3)
				car.setMake("Toyota");
			mockAvailableCars.add(car);
		}
	}
	
	@Test
	public void logInSuccessfully() {
		//set up
		String username="ponce";
		String password="pass";
		
		//set up mocking
		Person mockPerson = new Person();
		mockPerson.setUsername(username);
		mockPerson.setPassword(password);
		when(personDao.getByUsername(username)).thenReturn(mockPerson);
		// call method
		Person actualPerson = userServ.logIn(username,password);
		//assert expected behavior/output
		assertEquals(mockPerson,actualPerson);
	}

	@Test
	public void logInIncorrectPassword() {
		String username="qwertyuiop";
		String password="12345";
		
		Person mockPerson = new Person();
		mockPerson.setUsername(username);
		mockPerson.setPassword("pass");
		when(personDao.getByUsername(username)).thenReturn(mockPerson);
		
		Person actualPerson = userServ.logIn(username, password);
		assertNull(actualPerson);
	}
	
	@Test
	public void logInUsernameDoesNotExist() {
		String username="shanno11";
		String password="pass";
		
		when(personDao.getByUsername(username)).thenReturn(null);
		
		Person actualPerson = userServ.logIn(username, password);
		assertNull(actualPerson);
	}
	
	@Test
	public void registerPersonSuccessfully() {
		Person person = new Person();
		
		when(personDao.create(person)).thenReturn(10);
		
		Person actualPerson = userServ.register(person);
		assertEquals(10, actualPerson.getId());
	}
	@Test
	public void registerPersonSomethingWrong() {
		Person person = new Person();
		when(personDao.create(person)).thenReturn(0);
		Person actualPerson = userServ.register(person);
		assertNull(actualPerson);
	}
	
	@Test
	public void searchByMakeExists() {
		String make = "Toyota";
		
		when(carDao.getByStatus("Available")).thenReturn(mockAvailableCars);
		
		Set<Car> actualToyotas = userServ.searchAvailableCarsByMake(make);
		boolean onlyToyotas = true;
		for (Car car : actualToyotas) {
			if (!car.getMake().equals(make))
				onlyToyotas = false;
		}
		
		assertTrue(onlyToyotas);
	}
	
	@Test
	public void searchByMakeDoesNotExist() {
		String make = "Merecedes";
		
		when(carDao.getByStatus("Available")).thenReturn(mockAvailableCars);
		
		Set<Car> actualCars = userServ.searchAvailableCarsByMake(make);
		assertTrue(actualCars.isEmpty());
	}
	
	@Test
	public void purchasedCarSuccessfully() {
		int carId = 1;
		Person person = new Person();
		
		Car mockCar = new Car();
		mockCar.setId(1);
		when(carDao.getById(carId)).thenReturn(mockCar);
		
		// mock will do nothing when "update" gets called with any pet or person
		doNothing().when(carDao).update(Mockito.any(Car.class));
		doNothing().when(personDao).update(Mockito.any(Person.class));
		
		Person newPerson = userServ.purchasedCar(carId, person);
		
		// make sure that the method returned a person that has their
		// newly adopted pet there, and that pet has the correct status
		mockCar.setStatus("purchased");
		assertTrue(newPerson.getCars().contains(mockCar));
	}
	
	@Test
	public void purchasedCarAlreadyPurchased() {
		int carId = 1;
		Person person = new Person();
		
		Car mockCar = new Car();
		mockCar.setId(1);
		mockCar.setStatus("Purchased");
		when(carDao.getById(carId)).thenReturn(mockCar);
		
		Person newPerson = userServ.purchasedCar(carId, person);
		
		assertNull(newPerson);
		
		// these Mockito methods will verify that neither of these
		// update methods got called
		verify(carDao, times(0)).update(Mockito.any(Car.class));
		verify(personDao, times(0)).update(Mockito.any(Person.class));
	}
	
	@Test
	public void updateSuccessfully() {
		Person mockPerson = new Person();
		mockPerson.setId(1);
		
		doNothing().when(personDao).update(Mockito.any(Person.class));
		when(personDao.getById(1)).thenReturn(mockPerson);
		
		Person person = new Person();
		person.setId(1);
		person.setUsername("Badbunny");
		Person updatedPerson = userServ.updateUser(person);
		assertNotEquals(person, updatedPerson);
	}
	
	@Test
	public void updateSomethingWrong() {
		Person mockPerson = new Person();
		mockPerson.setId(1);
		
		doNothing().when(personDao).update(Mockito.any(Person.class));
		when(personDao.getById(1)).thenReturn(mockPerson);
		
		Person person = new Person();
		person.setId(1);
		person.setUsername("qwertyuiop");
		Person updatedPerson = userServ.updateUser(person);
		assertNotEquals(person, updatedPerson);
	}
	
	@Test
	public void viewAvailableCars() {
		when(carDao.getByStatus("Available")).thenReturn(mockAvailableCars);
		
		Set<Car> actualCars = userServ.viewAvailableCars();
		
		assertEquals(mockAvailableCars, actualCars);
	}
}

