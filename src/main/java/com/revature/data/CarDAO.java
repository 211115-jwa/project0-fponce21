package com.revature.data;

import java.util.Set;

import com.revature.beans.Car;




public interface CarDAO extends GenericDAO<Car> {
	
	public Set<Car> getByStatus(String status);
	Set<Car> getByMake(String make);
	Set<Car> getByModel(String model);
}
