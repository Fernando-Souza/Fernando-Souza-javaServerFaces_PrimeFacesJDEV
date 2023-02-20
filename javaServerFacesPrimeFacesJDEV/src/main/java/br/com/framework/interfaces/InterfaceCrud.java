package br.com.framework.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {
	
	void save (T obj) throws Exception;
	void persist(T obj)throws Exception;
	void saveOrUpdate(T obj) throws Exception;
	void update(T obj)throws Exception;
	void delete(T obj)throws Exception;
	T merge(T obj) throws Exception;
	List<T> findList(Class<T> obj)throws Exception;
	Object findObjectById(Class<T> entidade,Long id)throws Exception;
	T findClassById(Class<T> entidade,Long id)throws Exception;
	List<T> findListByQuery(String s)throws Exception;
	void executeUpdateHQL(String s)throws Exception;
	void executeUpdateSQL(String s)throws Exception;
	void clearSession()throws Exception;
//	Retira objeto da sessão do hibernate
	void evict(T obj)throws Exception;
	Session getSession()throws Exception;
	List<?> getListSQL(String sql)throws Exception;
//	JDBC do Spring
	JdbcTemplate getJdbcTemplate();
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	SimpleJdbcInsert getSimpleJdbcInsert();
//	--
	Long totalRegistro(String table)throws Exception;
	
	Query obterQuery(String query)throws Exception;
//	Filtra a lista pelo index inicial e final
	List<T> findListByQuery(String query, int inicioIndex,int finalIndex)throws Exception;

}
