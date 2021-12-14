package com.revature.services;



	import java.util.Set;

	import com.revature.beans.Person;
	import com.revature.beans.Car;
	import com.revature.data.PersonDAO;
	import com.revature.data.CarDAO;

	public class UserServiceImpl implements UserService {
		private PersonDAO personDao;
		private CarDAO carDao;

		@Override
		public Person register(Person newUser) {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			return null;
		}

		
		@Override
		public Set<Car> searchAvailableCarsByColor(String colors) {
			// TODO Auto-generated method stub
			return null;
		}

		

		@Override
		public Set<Car> searchAvailableCarsByModel(String model) {
			// TODO Auto-generated method stub
			return null;
		}

		public CarDAO getCarDao() {
			return carDao;
		}

		public void setCarDao(CarDAO carDao) {
			this.carDao = carDao;
		}

		@Override
		public Person purchasedCar(int carId, Person newOwner) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<Car> viewAvailableCars() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<Car> searchAvailableCarsByMake(String make) {
			// TODO Auto-generated method stub
			return null;
		}

		
	}

	

