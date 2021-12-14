package com.revature.services;



	import java.util.Set;

	import com.revature.beans.Person;
import com.revature.beans.Car;


	public interface UserService {
	
		public Person register(Person newUser);
		public Person logIn(String username, String password);
		public Person updateUser(Person userToUpdate);
		public Person purchasedCar(int carId, Person newOwner);
		public Set<Car> viewAvailableCars();
		public Set<Car> searchAvailableCarsByColor(String colors);
		public Set<Car> searchAvailableCarsByModel(String model);
		public Set<Car> searchAvailableCarsByMake(String make);
		
	}


