package com.revature.app;
import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.beans.Person;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.beans.Car;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

public class CarApp {
	private static UserService userServ = new UserServiceImpl();
	private static EmployeeService empServ = new EmployeeServiceImpl();
	public static void main(String[] args) {
		Javalin app = Javalin.create();
		
		app.start();
		
		
		app.routes(() -> {
			// localhost:8080/cars
			path("/cars", () -> {
				get(ctx -> {
					// checking if they did /car?make=
					String makeSearch = ctx.queryParam("make");
					String modelSearch = ctx.queryParam("model");
					if (makeSearch != null && !"".equals(makeSearch)) {
						Set<Car> carsFound = userServ.searchAvailableCarsByMake(makeSearch);
						ctx.json(carsFound);
						
					} 
					
					else if (modelSearch != null && !"".equals(modelSearch)) {
						Set<Car> carsFound = userServ.searchAvailableCarsByModel(modelSearch);
						ctx.json(carsFound);
					} else {
					
						
						Set<Car> availableCars = userServ.viewAvailableCars();
						ctx.json(availableCars);
					}
				});
				
				post(ctx -> {
					// bodyAsClass turns JSON into a Java object based on the class you specify
					Car newCar = ctx.bodyAsClass(Car.class);
					if (newCar !=null) {
						empServ.addNewCar(newCar);
						ctx.status(HttpStatus.CREATED_201);
					} else {
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}
				});
				
				// localhost:8080/pets/adopt/8
				path("/purchased/{id}", () -> {
					put(ctx -> {
						try {
							int carId = Integer.parseInt(ctx.pathParam("id")); // num format exception
							Person newOwner = ctx.bodyAsClass(Person.class);
							// returns the person with their new car added
							newOwner = userServ.purchasedCar(carId, newOwner);
							ctx.json(newOwner);
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Car ID must be a numeric value");
						}
					});
				});
				
				// localhost:8080/pets/8
				path("/{id}", () -> {
					
					get(ctx -> {
						try {
							int carId = Integer.parseInt(ctx.pathParam("id")); // num format exception
							Car car = empServ.getCarById(carId);
							if (car != null)
								ctx.json(car);
							else
								ctx.status(404);
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Car ID must be a numeric value");
						}
					});
					
					put(ctx -> {
						try {
							int carId = Integer.parseInt(ctx.pathParam("id")); // num format exception
							Car carToEdit = ctx.bodyAsClass(Car.class);
							if (carToEdit != null && carToEdit.getId() == carId) {
								carToEdit = empServ.editCar(carToEdit);
								if (carToEdit != null)
									ctx.json(carToEdit);
								else
									ctx.status(404);
							} else {
								// conflict: the id doesn't match the id of the pet sent
								ctx.status(HttpCode.CONFLICT);
							}
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Car ID must be a numeric value");
						}
					});
					
				});
			});
		});
	}
	
}
