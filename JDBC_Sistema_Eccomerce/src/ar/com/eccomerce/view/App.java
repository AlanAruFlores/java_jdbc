package ar.com.eccomerce.view;

import java.sql.SQLException;
import java.util.Scanner;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IUserDAO;
import ar.com.eccomerce.dao.implementation.DAOManager;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;
import ar.com.eccomerce.utils.MenuOptions;

public class App {
	private IDAOManager manager;
	private Scanner input = new Scanner(System.in);
	
	private IUserDAO daoUser;

	public App() throws SQLException {
		manager = new DAOManager();
		this.daoUser = manager.getUserDAO();
	}
	
	public void startApp() throws SQLException, ObjectSQLNotExists {		
		System.out.println("HELLO !! , WELCOME TO THE ECCOMMERCE\n"
				+ "PLEASE SELECT AN OPTION\n");
		MenuOptions option = selectAMainOption();
		showViewOption(option);
		
	}
	private MenuOptions selectAMainOption() {
		MenuOptions options[] = MenuOptions.values();
		Integer opt;
		Integer attempts = 0;
		do {
			if(attempts != 0)
				doSpaces();
			
			for(int i = 0; i<options.length;i++)
				System.out.println("["+(i+1)+"]"+options[i].name());
			opt = input.nextInt();	
			attempts++;
		}while(opt < 1 || opt > options.length);
		
		MenuOptions option = options[opt-1];
		return option;
	}
	
	private void showViewOption(MenuOptions opt) throws SQLException, ObjectSQLNotExists {
		User currentUser = null;
		
		switch(opt) {
			case LOGIN:
				currentUser = showLoginView();
				UserView ui = new UserView(this.manager,this.input,currentUser,this.daoUser);
				ui.showMainView();
				break;
			case REGISTER:
				showRegisterView();
				break;
		}
	}
	
	private User showLoginView() throws SQLException, ObjectSQLNotExists{
		User myUser;
		
		String user, password;
		System.out.println("PLEASE TO LOGIN TYPE YOUR USER AND PASSWORD: \n");
		System.out.println("USER: ");
		user = input.next();
		System.out.println("PASSWORD: ");
		password = input.next();	
		
		User aux = new User();
		aux.setName(user);
		aux.setPassword(password);
		
		myUser = daoUser.getUserByNameAndPassword(aux);
		return myUser;
	}
	
	private void showRegisterView() throws SQLException {
		String user, password, mail;
		System.out.println("\n\nPLEASE REGISTER YOUR NEW USER: \n");
		System.out.println("USER: ");
		user = input.next();
		System.out.println("PASSWORD: ");
		password = input.next();
		System.out.println("EMAIL: ");
		mail = input.next();
		
		User newUser = new User(user,password,0.0f,false,mail,1);
		
		Boolean result = daoUser.insert(newUser);
		
		if(result)
			System.out.println("We can register success your new user");
		else
			System.out.println("We cannot register your new user");
	}
	
	
	
	private void doSpaces() {
		for(int i=0; i<20; i++) 
			System.out.println();
	}
	
	
	public void closeApplication() throws SQLException{
		Boolean verify = manager.verifyIfWeHaveConnection();
		if(verify)
			manager.closeConnection();
	}
}
