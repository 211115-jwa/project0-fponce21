package com.revature.data.postgres;

import java.util.Set;

import com.revature.beans.Car;
import com.revature.data.CarDAO;

public class CarPostgres implements CarDAO {

	@Override
	public int create(Car dataToAdd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Car getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Car> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Car dataToUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Car dataToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Car> getByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Car> getByMake(String make) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Car> getByModel(String model) {
		// TODO Auto-generated method stub
		return null;
	}

}
