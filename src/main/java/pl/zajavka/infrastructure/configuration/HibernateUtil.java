package pl.zajavka.infrastructure.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import pl.zajavka.infrastructure.entities.*;

import java.util.Map;
public class HibernateUtil {
    private static final Map<String, Object> SETTINGS = Map.ofEntries(
            Map.entry(Environment.DRIVER, "org.postgresql.Driver"),
            Map.entry(Environment.URL, "jdbc:postgresql://localhost:5432/CarDealership"),
            Map.entry(Environment.USER, "postgres"),
            Map.entry(Environment.PASS, "E=mv^2/2"),
            Map.entry(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect"),
            Map.entry(Environment.HBM2DDL_AUTO, "none"),
            Map.entry(Environment.SHOW_SQL, true),
            Map.entry(Environment.FORMAT_SQL, false)
    );
    private static final SessionFactory sessionFactory = loadSessionFactory();
    private static SessionFactory loadSessionFactory() {
        try {
            ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(SETTINGS)
                    .build();
            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(CustomerEntity.class)
                    .addAnnotatedClass(SalesmanEntity.class)
                    .addAnnotatedClass(AddressEntity.class)
                    .addAnnotatedClass(CarToBuyEntity.class)
                    .addAnnotatedClass(CarToServiceEntity.class)
                    .addAnnotatedClass(ServiceEntity.class)
                    .addAnnotatedClass(PartEntity.class)
                    .addAnnotatedClass(MechanicEntity.class)
                    .addAnnotatedClass(InvoiceEntity.class)
                    .addAnnotatedClass(CarServiceRequestEntity.class)
                    .addAnnotatedClass(ServicePartEntity.class)
                    .addAnnotatedClass(ServiceMechanicEntity.class)
                    .getMetadataBuilder()
                    .build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static void closeSessionFactory() {
        try {
            sessionFactory.close();
        } catch (Throwable ex) {
            System.err.println("Exception while closing SessionFactory: " + ex);
        }
    }
    public static Session getSession() {
        try {
            return sessionFactory.openSession();
        } catch (Throwable ex) {
            System.err.println("Exception while getting Session: " + ex);
        }
        return null;
    }
}