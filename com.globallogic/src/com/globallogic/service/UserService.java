package com.globallogic.service;

import java.util.*;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;

import com.globallogic.repo.UserRepo;
import com.globallogic.to.UserTO;

public class UserService {

	Scanner sc = new Scanner(System.in);

	UserRepo repository = new UserRepo();
	BookService bookService = new BookService();

	public void register() {

		System.out.println("Enter a username : ");
		String userId = sc.nextLine();
		System.out.println("Enter a password : ");
		String password = sc.nextLine();
		System.out.println("Enter your email : ");
		String email = sc.nextLine();
		System.out.println("Enter your role : ");
		String role = sc.nextLine();

		UserTO user = new UserTO(userId, password, email, role);
		repository.register(user);

	}

	public void login() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your username : ");
		String userId = sc.nextLine();
		System.out.println("Enter your password : ");
		String password = sc.nextLine();

		repository.login(userId, password);
	}
	
	
	
	

}
