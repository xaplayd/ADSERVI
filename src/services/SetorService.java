package services;

import java.util.List;

import models.Setor;

public class SetorService {
	

	public static Setor puxaSetor(List<Setor> list, Integer cod) {
		if (list.isEmpty()) {
			throw new IllegalStateException("A lista esta VAZIA!");
		}
		
		for (Setor y : list) {
			if (y.getCodigo().compareTo(cod) == 0) {
			return y;
			}
		}
		return null;

	}
	
	
}
