package services;

import java.sql.SQLException;
import java.util.List;

import dao.TblUsuariosDAO;
import dao.TblUsuariosDAOImpl;
import models.Usuario;

public class UserService {

	public static Integer validaLogin(Usuario user) {
		try {
			TblUsuariosDAO userDAO = new TblUsuariosDAOImpl();
			List<Usuario> tempList = userDAO.getAll();
			if (tempList.isEmpty()) {
				throw new IllegalStateException("A lista esta VAZIA!");
			}
			String loginValida = user.getLogin();
			String senhaValida = user.getSenha();

			for (Usuario y : tempList) {
				if (y.getLogin().compareTo(loginValida) == 0 && y.getSenha().compareTo(senhaValida) == 0
						&& y.getSituacao() == 1) {
					return 1;
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return 0;
	}

	public static Integer validaPermissao(Usuario user) {

		TblUsuariosDAOImpl userDAO = new TblUsuariosDAOImpl();
		Integer nivel = userDAO.getNivelByLogin(user.getLogin());
		if (nivel == 1) {
			return 1;
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
