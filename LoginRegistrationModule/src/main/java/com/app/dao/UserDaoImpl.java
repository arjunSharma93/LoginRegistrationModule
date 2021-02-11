package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Override
	public long registrationProcess(User user) {
		
		long id = 0;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ loginregistrationmodule?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("select * from users where email='"+user.getEmail()+"'");
			ResultSet rs1 = ps.executeQuery();
			ps = con.prepareStatement("select * from users where mobile='"+user.getMobile()+"'");
			ResultSet rs2 = ps.executeQuery();
			if(!rs1.next() && !rs2.next()) {
				ps = con.prepareStatement("INSERT INTO users (name, email, mobile, password, role) VALUES (?, ?, ?, ?, ?)");
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getMobile());
				ps.setString(4, user.getPassword());
				ps.setString(5, user.getRole());
				
				int i = ps.executeUpdate();
				if(i!=0) {
					
					ps = con.prepareStatement("SELECT * FROM users WHERE email='"+user.getEmail()+"'");
					ResultSet rs = ps.executeQuery();
					rs.next();
					id = rs.getLong(1);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return id;
	}

	@Override
	public User loginProcess(User user) {
		
		User newUser = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ loginregistrationmodule?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("select * from users where email='"+user.getEmail()+"' and password='"+user.getPassword()+"'");
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				return newUser;
			}
			else {
				newUser = new User();
				newUser.setId(rs.getLong(1));
				newUser.setName(rs.getString(2));
				newUser.setEmail(rs.getString(3));
				newUser.setMobile(rs.getString(4));
				newUser.setRole(rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return newUser;
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ loginregistrationmodule?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("select * from users");
			ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getLong(1));
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					user.setMobile(rs.getString(4));
					user.setPassword(rs.getString(5));
					user.setRole(rs.getString(6));
					userList.add(user);
				}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return userList;
	}

	@Override
	public User getUser(int id) {
		User user = new User();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ loginregistrationmodule?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("select * from users where id='"+id+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user.setId(rs.getLong(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setMobile(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setRole(rs.getString(6));
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User postUser(User user) {
		
		User newUser = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ loginregistrationmodule?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("INSERT INTO users (name, email, mobile, password, role) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getMobile());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			
			int i = ps.executeUpdate();
			if(i != 0) {
				newUser = user;
				return newUser;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return newUser;
	}

	@Override
	public User deleteUser(int id) {
		User user = new User();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ loginregistrationmodule?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("select * from users where id='"+id+"'");
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setId(rs.getLong(1));
			user.setName(rs.getString(2));
			user.setEmail(rs.getString(3));
			user.setMobile(rs.getString(4));
			user.setRole(rs.getString(6));
			ps = con.prepareStatement("delete from users where id='"+id+"'");
			int i = ps.executeUpdate();
			if(i!=0) {
				return user;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateUser(User user) {
		User updatedUser = user;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ loginregistrationmodule?useTimezone=true&serverTimezone=UTC","root","");
			PreparedStatement ps = con.prepareStatement("update users set name='"+user.getName()+"', email='"+user.getEmail()+"', mobile='"+user.getMobile()+"' where id='"+user.getId()+"'");
			int i = ps.executeUpdate();
			if(i!=0) {
				ps=con.prepareStatement("select * from users where id='"+user.getId()+"'");
				ResultSet rs = ps.executeQuery();
				rs.next();
				updatedUser.setId(rs.getLong(1));
				updatedUser.setName(rs.getString(2));
				updatedUser.setEmail(rs.getString(3));
				updatedUser.setMobile(rs.getString(4));
				updatedUser.setRole(rs.getString(6));
				return updatedUser;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return updatedUser;
	}

}
