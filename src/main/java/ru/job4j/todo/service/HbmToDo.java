package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Item;
import java.util.List;
import java.util.function.Function;

public class HbmToDo implements TaskService, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private final Logger logger = LoggerFactory.getLogger(HbmToDo.class.getName());

    private static final HbmToDo INST = new HbmToDo();

    public static HbmToDo instOf() {
        return INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Item add(Item item) {
        this.tx(session -> session.save(item));
        return item;
    }

    @Override
    public List<Item> findAll() {
        return this.tx(session -> session.createQuery("FROM Item order by id").list());
    }

    @Override
    public List<Item> showFilter() {
        return this.tx(session -> session.createQuery("FROM Item WHERE done = false order by id").list());
    }

    @Override
    public void update(String key) {
        this.tx(session -> session.createQuery("UPDATE Item SET done = true where description = :description")
                                            .setParameter("description", key)
                                            .executeUpdate());
    }
}
