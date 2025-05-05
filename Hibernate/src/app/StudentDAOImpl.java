package app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAOImpl implements GenericDAO<Student, Long> {

    private final EntityManager em;

    public StudentDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Student entity) {
        em.persist(entity);
    }

    @Override
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Student findByEmail(String email) {
        TypedQuery<Student> q =
                em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class);
        q.setParameter("email", email);
        return q.getSingleResult();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> q =
                em.createQuery("SELECT s FROM Student s", Student.class);
        return q.getResultList();
    }

    @Override
    public Student update(Student entity) {
        return em.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        Student s = em.find(Student.class, id);
        if (s != null) {
            em.remove(s);
            return true;
        }
        return false;
    }
}