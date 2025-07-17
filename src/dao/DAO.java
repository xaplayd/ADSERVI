package dao;

import java.sql.SQLException;
import java.util.List;
import java.sql.Types;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import models.TabelaColuna;

public interface DAO<T> {
	
	T getById(Integer id) throws SQLException;
	
	T getByName(String name) throws SQLException;

	List<T> getAll() throws SQLException;

	Integer insert(T t) throws SQLException;

	Integer updateById(T t) throws SQLException;
	
	Integer deleteById(T t) throws SQLException;
	
	TableView estruturaTbl() throws SQLException;
	
	ObservableList<String> obterNomesDasColunas() throws SQLException;
	
	String getTblName() throws SQLException;
	
	List<TabelaColuna> getColunasDaTabela() throws SQLException;
	
	String getChavePrimaria() throws SQLException;

	List<TabelaColuna> mapperEntityToView(Integer id, List<TabelaColuna> estrutura);
}
