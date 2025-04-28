package app;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        StudentDAOImpl dao = new StudentDAOImpl(em);

        em.getTransaction().begin();

        Student st = new Student();
        st.setFirstName("Pam");
        st.setLastName("Oliver");
        st.setEmail("pam@wakeforest.nc.edu");
        dao.save(st);

        Homework hw = new Homework();
        hw.setDescription("Hibernate Basics");
        hw.setDeadline(java.time.LocalDate.now().plusDays(7));
        hw.setMark(0);
        st.addHomework(hw);
        dao.update(st);

        em.getTransaction().commit();

        Student fromDb = dao.findById(st.getId());
        System.out.println("Student from DB: " + fromDb);
        fromDb.getHomeworks().forEach(h -> System.out.println("  HW: " + h));

        em.close();
        JPAUtil.shutdown();
    }
}