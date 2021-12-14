package com.revature.data;

import java.util.Set;

import com.revature.beans.Car;




public interface CarDAO extends GenericDAO<Car> {
	public Set<Car> getByColor(String color);
	public Set<Car> getByMake(String make);
	public Set<Car> getByModel(String model);
	public Car getById(int id);

}
