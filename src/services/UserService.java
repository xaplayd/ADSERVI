package services;

import java.util.List;

import models.Usuario;

public class UserService {
	
	public static Integer validaLogin(List<Usuario> list, Usuario user) {
		if (list.isEmpty()) {
			throw new IllegalStateException("User list is EMPTY!");
		}
		
		String loginValida = user.getLogin();
		String senhaValida = user.getSenha();

		for (Usuario y : list) {
			if (y.getLogin().compareTo(loginValida) == 0 && y.getSenha().compareTo(senhaValida) == 0) {
			return 1;
			}
		}
		return 0;
	}

}
