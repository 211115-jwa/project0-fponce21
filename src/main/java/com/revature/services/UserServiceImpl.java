 package com.revature.services;



	import java.util.Set;
import java.util.stream.Collectors;

import com.revature.beans.Person;
import com.revature.beans.Car;
	import com.revature.data.PersonDAO;
	import com.revature.data.CarDAO;

	public class UserServiceImpl implements UserService {
		private PersonDAO personDao;
		private CarDAO carDao;

		@Override
		public Person register(Person newUser) {
				int newId = personDao.create(newUser);
				if (newId != 0) {
					newUser.setId(newId);
					return newUser;
				}
				return null;
			}

		@Override
		public Person logIn(String username, String password) {
			Person personFromDatabase = personDao.getByUsername(username);
			if (personFromDatabase != null && personFromDatabase.getPassword().equals(password)) {
				return personFromDatabase;
			}
			return null;
		}

		@Override
		public Person updateUser(Person userToUpdate) {
			if (personDao.getById(userToUpdate.getId()) != null) {
				personDao.update(userToUpdate);
				userToUpdate = personDao.getById(userToUpdate.getId());
				return userToUpdate;
			}
			return null;
		}

		@Override
		public Person purchasedCar(int carId, Person newOwner) {
			Car carToPurchase = carDao.getById(carId);
			if (carToPurchase.getStatus().equals("Available")) {
				carToPurchase.setStatus("Purchased");
				newOwner.getCars().add(carToPurchase);
				
				carDao.update(carToPurchase);
				personDao.update(newOwner);
				return newOwner;
			}
			return null;
		}
		
		@Override
		public Set<Car> viewAvailableCars() {
			return carDao.getByStatus("Available");
		}

		
		@Override
		public Set<Car> searchAvailableCarsByMake(String make) {
			Set<Car> availableCars = carDao.getByStatus("Available");
			availableCars = availableCars.stream()
					.filter(car -> car.getMake().toLowerCase().contains(make.toLowerCase()))
					.collect(Collectors.toSet());
			
			return availableCars;
		}
		

		@Override
		public Set<Car> searchAvailableCarsByModel(String model) {
	Set<Car> availableCars = carDao.getByStatus("Available");
			availableCars = availableCars.stream()
					.filter(car -> car.getModel().toLowerCase().contains(model.toLowerCase()))
					.collect(Collectors.toSet());
			
			return availableCars;
		}
		
		@Override
		public Set<Car> searchAvailableCarsByColor(String color) {
			Set<Car> availableCars = carDao.getByStatus("Available");
			
			/* 
			   using a Stream to filter the pets
			   "filter" takes in a Predicate (functional interface)
			   and iterates through each pet, adding the pet to the stream
			   if the predicate returns "true"
			*/
			availableCars = availableCars.stream()
					.filter(car -> car.getColor().toLowerCase().contains(color.toLowerCase()))
					.collect(Collectors.toSet());
			
			// if you don't want to use a Stream, you can always just
			// do this yourself using a for loop :) it will do the same thing!
			/*
			 * Set<Pet> petsBySpecies = new HashSet<>();
			 * for (Pet pet : availablePets) {
			 * 		if(pet.getSpecies().toLowercase().contains(species.toLowercase())) {
			 * 			petsBySpecies.add(pet);
			 * 		}
			 * }
			 * 
			 * availablePets = petsBySpecies;
			 */
			
			return availableCars;
		}
	}

	

