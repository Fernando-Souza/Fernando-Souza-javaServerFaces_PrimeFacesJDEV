package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfaces.InterfaceCrud;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Autowired
	private JdbcTemplateImpl jdbcTemplate;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;

	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;

	@Autowired
	private SimpleJdbcClassImpl simpleJdbcClass;

	@Override
	public void save(T obj) throws Exception {

		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();

	}

	@Override
	public void persist(T obj) throws Exception {

		validaSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();

	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {

		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();

	}

	@Override
	public void update(T obj) throws Exception {

		validaSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();

	}

	@Override
	public void delete(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();

	}

	@Override
	public T merge(T obj) throws Exception {
		validaSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> obj) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select distinct(entity) ").append(obj.getSimpleName()).append(" entiy");
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();

		return lista;
	}

	@Override
	public T findObjectById(Class<T> entidade, Long id) throws Exception {

		validaSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().load(entidade.getClass(), id);

		return obj;

	}

	@Override
	public List<T> findListByQuery(String query) throws Exception {

		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query).list();

		return lista;
	}

	@Override
	public List<?> findListBySQL(String sql) throws Exception {
		validaSessionFactory();
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@Override
	public List<Object[]> findListArrayBySQL(String sql) throws Exception {
		validaSessionFactory();
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@Override
	public void executeUpdateHQL(String query) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
		executeFlushSession();

	}

	@Override
	public void executeUpdateSQL(String query) throws Exception {

		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
		executeFlushSession();

	}

	@Override
	public void clearSession() throws Exception {

		sessionFactory.getCurrentSession().clear();

	}

	@Override
	public void evict(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().evict(obj);
		;
	}

	@Override
	public Session getSession() throws Exception {
		validaSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {

		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {

		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
	
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegistro(String table) throws Exception {
		
		StringBuilder sql =  new StringBuilder();
		sql.append(" select count(1) from ").append(table);
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		return queryReturn;
	}

	@Override
	public List<T> findListByQuery(String query, int inicioIndex, int finalIndex) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<>();
		lista= sessionFactory.getCurrentSession().createQuery(query).setFirstResult(inicioIndex)
				.setMaxResults(finalIndex).list();
		return lista;
	}

	private void validaTransaction() {

		if (sessionFactory.getCurrentSession().getTransaction().isActive()) {

			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	private void validaSessionFactory() {

		if (sessionFactory == null) {

			sessionFactory = HibernateUtil.getSessionFactory();
		}

		validaTransaction();
	}

	private void commitProcessoAjax() {

		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public T findClassById(Class<T> entidade, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
