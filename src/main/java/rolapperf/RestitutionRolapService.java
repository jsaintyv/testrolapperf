package rolapperf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import rolapperf.model.Company;
import rolapperf.model.Contract;
import rolapperf.model.User;

public class RestitutionRolapService implements AutoCloseable {
	private Map<String, String> externProperties = new HashMap<String, String>();
	private final SessionFactory sessionFactory;

	public RestitutionRolapService() {
		sessionFactory = createSessionFactory();			
	}
	
	public RestitutionRolapService(Map<String, String> externProperties) {
		this.externProperties.putAll(externProperties);
				
		sessionFactory = createSessionFactory();			
	}

	private SessionFactory createSessionFactory() {
		return Persistence.createEntityManagerFactory("primaryPU", externProperties).unwrap(SessionFactory.class);
	}
	

	public void clear() {
		try (Session session = sessionFactory.openSession()) {			
			session.getTransaction().begin();
			try {
				for (String tableName : new String[] { "User", "Company", "Contract", "RestitutionMonth" }) {
					Query query = session.createQuery(String.format("delete from " + tableName));
					query.executeUpdate();
				}
				session.getTransaction().commit();
			} catch(Throwable th) {
				session.getTransaction().rollback();	
			}
		} 
	}
	
	
	
	
	public List<User> listUsers() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("SELECT a FROM User a", User.class).getResultList();  
		}
	}
	

	public Connection getConnection() throws SQLException
	{
		Map<String, Object> properties = sessionFactory.getProperties();
		String url = (String) properties.get("hibernate.connection.url");
		String username = (String) externProperties.get("hibernate.connection.username");
		String password = (String) externProperties.get("hibernate.connection.password");
		
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public void close() {
		sessionFactory.close();
	}

	
	public User addUser(Session session, String name) {
		
			User entity = new User();
			entity.setName(name);
			session.save(entity);			
			return entity;
		
	}
	
	public Company addCompany(Session session, Integer parentId, int level, String name) {
		
			Company entity = new Company();
			
			entity.setParentId(parentId);
			entity.setLevel(level);
			entity.setName(name);
			session.save(entity);			
			return entity;
		
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}

	public Contract buildContract(Session session, int companyId, String name) {
		Contract contract = new Contract();
		contract.setCompanyId(companyId);
		contract.setName(name);
		session.save(contract);
		return contract;
	}

	public Company getCompany(Session session, int companyId) {
		return session.get(Company.class, companyId);
	}
	
	public static RestitutionRolapService buildService(String[] args) {
		String myHost = "localhost";
		String myPort = "3306";
		String myDb = "olapperf";
		String username = "ordreo";
		String password = "allpriv";
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
		properties.put("hibernate.connection.url",
				"jdbc:mariadb://" + myHost + ":" + myPort + "/" + myDb + "?allowPublicKeyRetrieval=true&useSSL=false");
		properties.put("hibernate.connection.autoReconnect", "true");
		properties.put("hibernate.connection.autocommit", "false");
		properties.put("hibernate.connection.username", username);
		properties.put("hibernate.connection.password", password);
		properties.put("hibernate.connection.pool_size", "30");

		System.out.println(properties);

		RestitutionRolapService service = new RestitutionRolapService(properties);
		return service;
	}
}