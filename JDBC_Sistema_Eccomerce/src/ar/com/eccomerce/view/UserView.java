package ar.com.eccomerce.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IPostDAO;
import ar.com.eccomerce.dao.IUserDAO;
import ar.com.eccomerce.dao.IUserStateDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Post;
import ar.com.eccomerce.model.User;
import ar.com.eccomerce.model.UserState;
import ar.com.eccomerce.utils.UserOptions;
import ar.com.eccomerce.utils.UserPerfilOption;

public class UserView {
	private Scanner input = new Scanner(System.in);
	private IUserDAO daoUser;
	private IUserStateDAO daoState;
	private IPostDAO daoPost;
	
	private IDAOManager manager;
	private User currentUser;
	
	public UserView(IDAOManager manager, Scanner input, User currentUser, IUserDAO daoUser) {
		this.input = input;
		this.manager = manager;
		this.daoUser = daoUser;
		this.daoState = manager.getUserStateDAO();
		this.daoPost = manager.getPostDAO();
		this.currentUser = currentUser;
	}
	
	
	public void showMainView() throws SQLException, ObjectSQLNotExists {
		System.out.println("PLEASE TYPE AN OPTION: ");
		UserOptions opt = typeAnUserOption();
		doActionDependingTheOption(opt);
	}
	
	private UserOptions typeAnUserOption() {
		UserOptions userOptions[] = UserOptions.values();	
		Integer opt;
		Integer attempts = 0;
		
		do {
			if(attempts != 0)
				doSpaces();
			for(int i  = 0; i<userOptions.length;i++)
				System.out.println("["+(i+1)+"]"+userOptions[i].name());
			opt = input.nextInt();
			attempts++;
		}while(opt<1 || opt>userOptions.length);
		
		UserOptions option = userOptions[opt-1];
		return option;
	}
	

	
	private void doActionDependingTheOption(UserOptions opt) throws SQLException, ObjectSQLNotExists {
		doSpaces();
		switch(opt) {
			case SHOW_POSTS:
				showAllPosts();
				break;
			case CONFIGURE_PERFIL:
				cofigurePerfil();
				break;
			case SEE_YOUR_PERFIL:
				showYourPerfil();
				break;
			case SEE_CART:
				break;
		}
	}
	
	private void showYourPerfil() throws SQLException, ObjectSQLNotExists{
		UserState stateAccount =  daoState.getById(new UserState(this.currentUser.getCodSta()));
		
		System.err.println("___________PERFIL___________\n"
				+ "NAME: "+this.currentUser.getName()+"\n"
				+ "PASSWORD: "+this.currentUser.getPassword()+"\n"
				+ "E-MAIL: "+this.currentUser.getMail()+"\n"
				+ "ACCOUNT STATE: "+stateAccount.getName());
	}
	
	private void cofigurePerfil() throws SQLException, ObjectSQLNotExists{
		UserPerfilOption option =typeAnPerfilOptionToUpdate();
		switch(option) {
		case NAME:
			String name = typeString("Type a new name");
			this.currentUser.setName(name);
			break;
		case PASSWORD:
			String password = typeString("Type a new password");
			this.currentUser.setPassword(password);
			break;
		case EMAIL:
			String email = typeString("Type a new email");
			this.currentUser.setMail(email);
			break;
		}
		
		Boolean result = daoUser.update(currentUser);
		if(result)
			JOptionPane.showMessageDialog(null, "SE ACTUALIZO CON EXITO");
	}
	

	private UserPerfilOption typeAnPerfilOptionToUpdate() {
		UserPerfilOption options[] = UserPerfilOption.values();
		Integer opt = 0;
		Integer attempt = 0;
		do {
			if(attempt != 0)
				doSpaces();
			
			for(int i = 0 ; i<options.length; i++) 
				System.out.println("["+(i+1)+"] "+options[i].name());
			opt = input.nextInt();
			attempt++;
		}while(opt<1 || opt>options.length);
		
		UserPerfilOption option = options[opt-1];
		return option;

	}
	
	private void showAllPosts() throws SQLException, ObjectSQLNotExists{
		List<Post> listPost = this.daoPost.getAll();
		System.out.println("POSTS AVALAIBLES\n\n");
		for(Post p : listPost)
			System.out.println(this.daoPost.getDescriptionOfPostById(p)+"\n\n");
	}
	
	
	private String typeString(String msj) {
		String result;
		System.out.println(msj);
		result = this.input.next();
		return result;
	}
	
	
	private void doSpaces() {
		for(int i=0; i<20; i++) 
			System.out.println();
	}
	
}


