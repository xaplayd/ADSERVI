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
			if (y.getLogin().compareTo(loginValida) == 0 && y.getSenha().compareTo(senhaValida) == 0 && y.getSituacao() == 1) {
			return 1;
			}
		}
		return 0;
	}
	
	public static Integer validaPermissao(List<Usuario> list, Usuario user) {
		if (list.isEmpty()) {
			throw new IllegalStateException("User list is EMPTY!");
		}
		
		String loginValida = user.getLogin();

		for (Usuario y : list) {
			if (y.getLogin().compareTo(loginValida) == 0 && y.getPermissoes() == 1) {
			return 1;
			}
		}
		return 0;
	}
	
	
	
}
