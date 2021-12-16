package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import com.revature.beans.Person;
import com.revature.data.postgres.PersonPostgres;

public class PersonDAOTest {
	private PersonDAO personDao = new PersonPostgres();
	
	@Test
	public void createTest() {
		Person create = new Person();
		assertNotEquals(1, personDao.create(create));
		
	}
	
	@Test
	public void getValidPersonById()
	{
		String expectedUsername = "asaunders2";
		Person actual = personDao.getById(3);
		assertEquals(expectedUsername, actual.getUsername());
	}
	
	@Test
	public void getAll() {
		Set<Person> actual = personDao.getAll();
		assertEquals(personDao.getAll(), actual);
	}
	
	
	@Test
	public void getAllNotNull() {
		Set<Person> actual = personDao.getAll();
		assertNotEquals(null, actual);
	}
	
	@Test
	public void getByUsernameWhenUsernameExists() {
		// setup
		String usernameInput = "deslie3";
		Person personOutput = personDao.getByUsername(usernameInput);
		assertEquals("deslie3", personOutput.getUsername());
	}

	@Test
	public void testUpdate() {
		Person personUp = personDao.getById(11);
		personUp.setFullName("ponce");
		personDao.update(personUp);
		assertEquals("ponce",personDao.getById(11).getFullName());	
	}
	
	
}
