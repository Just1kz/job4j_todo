package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

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
    public Item addCategoriesInItem(Item item, String[] ids) {
        for (String id : ids) {
            item.addCategory(findById(Integer.parseInt(id)));
        }
        return item;
    }

    @Override
    public Item addItemBD(Item item) {
        this.tx(session -> session.save(item));
        return item;
    }

    @Override
    public List<Item> findAll() {
        return this.tx(session -> session.createQuery(
                "select distinct c from Item c join fetch c.categoryList order by c.id").list());
    }

    @Override
    public List<Item> showFilter() {
        return this.tx(session -> session.createQuery(
                "select distinct c from Item c join fetch c.categoryList WHERE c.done = false order by c.id").list());
    }

    @Override
    public void update(String key) {
        this.tx(session -> session.createQuery("UPDATE Item SET done = true where description = :description")
                                            .setParameter("description", key)
                                            .executeUpdate());
    }

    @Override
    public void createUser(User user) {
        this.tx(session -> session.save(user));
    }

    @Override
    public User findByEmailAndPasswordUser(User user) {
        return (User) this.tx(session -> session.createQuery("FROM User where email = :email and password = :password")
                                                                                                        .setParameter("email", user.getEmail())
                                                                                                        .setParameter("password", user.getPassword())
                                                                                                        .uniqueResult());
    }

    @Override
    public List<Category> AllCategories() {
        return this.tx(session -> session.createQuery("from Category ORDER BY id").list());
    }

    @Override
    public Category findById(int id) {
        return (Category) this.tx(session -> session.createQuery("FROM Category WHERE id = :id")
                                                                                                                        .setParameter("id", id)
                                                                                                                        .uniqueResult()
        );
    }
}
