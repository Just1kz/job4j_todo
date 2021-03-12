package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Item;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("FROM ru.job4j.todo.model.Item").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Item> showFilter() {
        List<Item> result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("FROM ru.job4j.todo.model.Item WHERE done = false order by id").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean update(String key) {
        Item result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = (Item) session.createQuery(
                    "FROM ru.job4j.todo.model.Item WHERE description = :description"
            ).setParameter("description", key).uniqueResult();
            result.setDone(true);
            session.update(result);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
