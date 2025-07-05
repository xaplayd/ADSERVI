package services;

import java.util.List;
import models.Tabela;

public class TblsService {

	public static Tabela puxaTabela(List<Tabela> list, Integer cod) {
		if (list.isEmpty()) {
			throw new IllegalStateException("A lista esta VAZIA!");
		}

		for (Tabela y : list) {
			if (y.getCodigo().compareTo(cod) == 0) {
				return y;
			}
		}
		return null;

	}

}
