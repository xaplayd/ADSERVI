package services;

import java.util.List;

import models.Usuario;

public class UserService {
	
	public static Integer validaLogin(List<Usuario> list, Usuario user) {
		if (list.isEmpty()) {
			throw new IllegalStateException("A lista esta VAZIA!");
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
			throw new IllegalStateException("A lista esta VAZIA!");
		}
		
		String loginValida = user.getLogin();

		for (Usuario y : list) {
			if (y.getLogin().compareTo(loginValida) == 0 && y.getPermissoes() == 1) {
			return 1;
			}
		}
		return 0;
	}
	
	public static Usuario puxaUser(List<Usuario> list, Integer cod) {
		if (list.isEmpty()) {
			throw new IllegalStateException("A lista esta VAZIA!");
		}
		
		for (Usuario y : list) {
			if (y.getCodigo().compareTo(cod) == 0) {
			return y;
			}
		}
		return null;

	}
	
	
}
