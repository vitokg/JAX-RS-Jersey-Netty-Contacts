package org.example.app.repository.impl;

import org.example.app.domain.contact.Contact;
import org.example.app.repository.AppRepository;
import org.example.app.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactRepository implements AppRepository<Contact> {

    private static final Logger LOGGER =
            Logger.getLogger(ContactRepository.class.getName());

    @Override
    public void create(Contact contact) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            String hql = "INSERT INTO Contact (name, phone)" +
                    "VALUES (:name, :phone)";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("name", contact.getName());
            query.setParameter("phone", contact.getPhone());
            query.executeUpdate();
            // Транзакція виконується
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public Optional<List<Contact>> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            // Транзакція стартує
            transaction = session.beginTransaction();
            List<Contact> list =
                    session.createQuery("FROM Contact", Contact.class).list();
            // Транзакція виконується
            transaction.commit();
            // Повертаємо Optional-контейнер з колецією даних
            return Optional.of(list);
        } catch (Exception e) {
            // Якщо помилка повертаємо порожній Optional-контейнер
            return Optional.empty();
        }
    }

    // ---- Path Param ----------------------

    @Override
    public Optional<Contact> fetchById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            Query<Contact> query = session.createQuery("FROM Contact WHERE id = :id", Contact.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            Contact contact = query.uniqueResult();
            // Транзакція виконується
            transaction.commit();
            // Повертаємо Optional-контейнер з об'єктом
            return Optional.of(contact);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            // Якщо помилка або такого об'єкту немає в БД,
            // повертаємо порожній Optional-контейнер
            return Optional.empty();
        }
    }

    @Override
    public void update(Long id, Contact contact) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакция стартует
            transaction = session.beginTransaction();
            String hql = "UPDATE Contact SET name = :name," +
                    " phone = :phone" +
                    " WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("name", contact.getName());
            query.setParameter("phone", contact.getPhone());
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Contact WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            // Транзакція виконується
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }


    // ---- Query Params ----------------------

    public Optional<List<Contact>> fetchByName(String name) {
        String hql = "FROM Contact WHERE name = :name";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            // Транзакція стартує
            transaction = session.beginTransaction();
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("name", name);
            List<Contact> list = query.list();
            // Транзакція виконується
            transaction.commit();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<Contact>> fetchAllOrderBy(String orderBy) {
        String hql = "FROM Contact ORDER BY " + orderBy;
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            // Транзакція стартує
            transaction = session.beginTransaction();
            List<Contact> list =
                    session.createQuery(hql, Contact.class)
                            .list();
            // Транзакція виконується
            transaction.commit();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<Contact>> fetchBetweenIds(Integer from, Integer to) {
        String hql = "FROM Contact u WHERE u.id BETWEEN :from AND :to";
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            // Транзакція стартує
            transaction = session.beginTransaction();
            List<Contact> list =
                    session.createQuery(hql, Contact.class)
                            .setParameter("from", from)
                            .setParameter("to", to)
                            .list();

            // Транзакція виконується
            transaction.commit();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<Contact>> fetchNameIn(String name1, String name2) {
        String hql = "FROM Contact u WHERE u.name IN (:name1, :name2)";
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            // Транзакція стартує
            transaction = session.beginTransaction();
            List<Contact> list =
                    session.createQuery(hql, Contact.class)
                            .setParameter("name1", name1)
                            .setParameter("name2", name2)
                            .list();
            // Транзакція виконується
            transaction.commit();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    // ---- Utils ----------------------

    public boolean isIdExists(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Перевірка наявності об'єкту за певним id
            Contact contact = new Contact();
            contact.setId(id);
            contact = session.get(Contact.class, contact.getId());

            if (contact != null) {
                Query<Contact> query = session.createQuery("FROM Contact", Contact.class);
                query.setMaxResults(1);
                query.getResultList();
            }
            return contact != null;
        }
    }

    public Optional<Contact> getLastUser() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            Query<Contact> query = session.createQuery("FROM Contact ORDER BY id DESC", Contact.class);
            query.setMaxResults(1);
            Contact contact = query.uniqueResult();
            // Транзакція виконується
            transaction.commit();
            return Optional.of(contact);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Optional.empty();
        }
    }
}
