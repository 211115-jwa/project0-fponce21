package com.revature.data.postgres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO{

	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	@Override
	public int create(Person dataToAdd) {
		int generatedId = 0;
		
		try(Connection conn = connUtil.getConnection()) {
		
			conn.setAutoCommit(false);
			
			String sql = "insert into person (id,full_name,username,passwd,user_role) " + 
			"values (default,?,?,?,?,?";
			
			String[] keys = {"id"};
			PreparedStatement pStmt = conn.prepareStatement(sql,keys);
			
			pStmt.setString(1, dataToAdd.getFullName()); // question mark index starts at 1
			pStmt.setString(2, dataToAdd.getUsername());
			pStmt.setString(3, dataToAdd.getPassword());
			pStmt.setString(4, dataToAdd.getRole());
			
			// after setting the values, we can run the statement
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();
			
			if (resultSet.next()) { // "next" goes to the next row in the result set (or the first row)
				// getting the ID value from the result set
				generatedId = resultSet.getInt("id");
				conn.commit(); // running the TCL commit statement
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return generatedId;
	}
	
	@Override
	public Person getById(int id) {
		
		Person person = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				person = new Person();
				person.setId(id);
				person.setFullName(resultSet.getString("full_name"));
				person.setUsername(resultSet.getString("username"));
				person.setPassword(resultSet.getString("passwd"));
				person.setRole(resultSet.getString("user_role"));
				// TODO: get user's pets
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public Set<Person> getAll() {
	Set<Person> allPeople = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			// while the result set has another row
			while (resultSet.next()) {
				// create the Person object
				Person person = new Person();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				person.setId(resultSet.getInt("id"));
				person.setFullName(resultSet.getString("full_name"));
				person.setUsername(resultSet.getString("username"));
				person.setPassword(resultSet.getString("passwd"));
				person.setRole(resultSet.getString("user_role"));
				// TODO: get user's car's
				
				allPeople.add(person);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allPeople;
	}


	@Override
	public void update(Person dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "update person set "
					+ "full_name=?,username=?,passwd=?,user_role=? "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dataToUpdate.getFullName());
			pStmt.setString(2, dataToUpdate.getUsername());
			pStmt.setString(3, dataToUpdate.getPassword());
			pStmt.setString(4, dataToUpdate.getRole());
			pStmt.setInt(5, dataToUpdate.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			
			if (rowsAffected==1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void delete(Person dataToDelete) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "delete from person "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToDelete.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			
			if (rowsAffected==1) {
				// TODO: remove user's pet-owner relationships
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person getByUsername(String username) {
	Person person = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person where username=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, username);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setFullName(resultSet.getString("full_name"));
				person.setUsername(resultSet.getString("username"));
				person.setPassword(resultSet.getString("passwd"));
				person.setRole(resultSet.getString("user_role"));
				// TODO: get user's pets
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return person;
	}


	@Override
	public Person getByFullname(String fullname) {
	Person person = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person where fullname=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, fullname);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setFullName(resultSet.getString("full_name"));
				person.setUsername(resultSet.getString("username"));
				person.setPassword(resultSet.getString("passwd"));
				person.setRole(resultSet.getString("user_role"));
				// TODO: get user's pets
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return person;
	}

}
