package com.hibernate;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.genel.UserEntity;

import jakarta.persistence.Entity;


public class HibernateMySQLUtil {
	
	
	public static void main(String[] args) {
		Session	ses	=	null;
		try {
			ses	=	HibernateMySQLUtil.openSession();
			
			UserEntity usr = ses.get(UserEntity.class, 1L);
			if (usr!=null && usr.getId() == 0) {
				System.out.println(usr.getFirstName() + " " + usr.getLastName());				
			}

			UserEntity usrdb = new UserEntity();
			//usrdb.setId(1L);
			usrdb.setFirstName("assasas");
			usrdb.setLastName("sacdcdsvdv");
			usrdb.setPassword("password");
			usrdb.setEmail("a@h.com");
			
			ses.beginTransaction();
			
			//ses.persist(usrdb);
			ses.merge(usrdb);
			ses.evict(usrdb);
			
			ses.getTransaction().commit();
			
		} catch (Throwable e) {
			ses.getTransaction().rollback();
			HibernateMySQLUtil.rollBack(ses);
			e.printStackTrace();
		}finally {
			HibernateMySQLUtil.close(ses);
			System.out.println("islem tamamlandi.");
			System.exit(1);
		}
		
		
		
	}
	
	protected static Logger logger = LoggerFactory.getLogger(HibernateMySQLUtil.class);
	private final static Set<Class<?>> classes;
	private final static ArrayList<String> resources;

	static {
		classes = new HashSet<Class<?>>();
		resources = new ArrayList<String>();
		Reflections reflections = new Reflections(new ConfigurationBuilder()
		.addUrls(ClasspathHelper.forClassLoader())
		.addUrls(ClasspathHelper.forJavaClassPath())
		.setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner(), new ResourcesScanner()));

		// Reflections reflections = new Reflections("");
		Set<Class<?>> _class = reflections.getTypesAnnotatedWith(Entity.class);
		for (final Class<?> clazz : _class) {
			classes.add(clazz);
		}

	}

	private static StandardServiceRegistry registry;
	private static BufferedReader br;
	
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/CayDB?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "none"); /* ENTİTY deki colonlara göre tablo drop edip tekrardan create eder*/

                configuration.setProperties(settings);

                //configuration.addAnnotatedClass(Student.class);
				for (final Class<?> clazz : classes) {
					configuration.addAnnotatedClass(clazz);
				}
				 for (final String str : resources) {
		            configuration.addResource(str);
		        }

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    
	public static Session openSession() {
		Session session	=	HibernateMySQLUtil.getSessionFactory().openSession();
		/*
		session.doWork(new org.hibernate.jdbc.Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
    	        PreparedStatement statement = null;
    	        try {              
    	        	connection.setAutoCommit(false);
    	            statement = connection.prepareStatement("alter session set nls_sort = XTURKISH");
    	            statement.executeUpdate();
    	        }finally {
    	            if(statement != null) {
    	                statement.close();
    	            }           
    	        } 
				
			}
		});
		*/
		return session;
	}
	
	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	public static void rollBack(Session session) {
		try {
			if (session != null) {
				if (session.isOpen()) {
					session.getTransaction().rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Session session) throws HibernateException {
		try {
			if (session != null) {
				if (session.isOpen()) {
					try {
						// rollback exception olursa session close olması için
						if (session.getTransaction().isActive()) {
							session.getTransaction().rollback();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
