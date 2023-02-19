package br.com.framework.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import br.com.framework.implementacao.crud.ConexaoUtil;

/**
 * @author fernando
 *
 */
/**
 * @author fernando
 *
 */
/**
 * @author fernando
 *
 */
@ApplicationScoped
public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static ConexaoUtil conexaoDataSource = ConexaoUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE;
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * responsável por ler o arquivo de configuração hibernate.cfg.xml
	 * 
	 * @return SessionFactory
	 **/
	private static SessionFactory buildSessionFactory() {

		try {

			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}

			return sessionFactory;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory");
		}

	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}

	public static Session getCurrentSession() {

		return getSessionFactory().getCurrentSession();
	}
	
	public  static Session openSession() {
		
		if(sessionFactory==null) {
			buildSessionFactory();
		}
		
		return sessionFactory.openSession();
		
	}
	
	public static Connection getConnectionProvider() throws SQLException {
		
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
		
	}
		
	/**
	 * @return Conecção do contexto inicial do datasource java:/comp/env/jdbc/datasource
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws NamingException, SQLException {
		
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(conexaoDataSource.getOpcao());
		return ds.getConnection();
	}
	
	/**
	 * @return DataSource JNDI TomCat
	 * @throws NamingException
	 */
	public DataSource getDataSourcJndi() throws NamingException {
		InitialContext context = new InitialContext();
		return (DataSource) context.lookup(conexaoDataSource.getOpcao());
	}

}
