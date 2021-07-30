package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class AbstractRepository <T> {

    private final EntityManagerFactory emFactory;


    public AbstractRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Student> findAll(){
        return executeForEntityManager(
                entityManager -> entityManager.createNamedQuery("allStudents", Student.class).getResultList());
    }

    public Student findById(long id){
        return executeForEntityManager(entityManager -> entityManager.find(Student.class, id));
    }

    public void insert(Student student){
        executeInTransaction(entityManager -> entityManager.persist(student));
    }

    public void update(Student student){
        executeInTransaction(entityManager -> entityManager.merge(student));
    }

    public void delete(long id){
        executeInTransaction(entityManager -> {
            Student student = entityManager.find(Student.class, id);
            if(student != null){
                entityManager.remove(student);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function){
        EntityManager em = emFactory.createEntityManager();
        try{
            return function.apply(em);
        } finally {
            if(em != null){
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer){
        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex){
            em.getTransaction().rollback();
        } finally {
            if(em != null){
                em.close();
            }
        }
    }
}
