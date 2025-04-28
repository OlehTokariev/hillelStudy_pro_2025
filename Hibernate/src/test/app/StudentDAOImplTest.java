package test.app;

import app.Homework;
import app.Student;
import app.StudentDAOImpl;
import org.junit.jupiter.api.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentDAOImplTest {

    private EntityManager em;
    private StudentDAOImpl dao;

    @BeforeAll
    void setupAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hillel-persistence-unit");
        em = emf.createEntityManager();
        dao = new StudentDAOImpl(em);
    }

    @BeforeEach
    void beginTx() {
        em.getTransaction().begin();
    }

    @AfterEach
    void rollbackTx() {
        em.getTransaction().rollback();
    }

    @AfterAll
    void teardownAll() {
        em.close();
    }

    @Test
    void testSaveAndFindById() {
        Student s = new Student();
        s.setFirstName("Sean");
        s.setLastName("O'Malley");
        s.setEmail("sean@ufc.com");
        dao.save(s);

        assertNotNull(s.getId(), "ID isn't null after save");
        Student found = dao.findById(s.getId());
        assertEquals("Sean", found.getFirstName());
    }

    @Test
    void testFindByEmail() {
        Student s = new Student();
        s.setFirstName("Brian");
        s.setLastName("Ortega");
        s.setEmail("brian@ufc.com");
        dao.save(s);

        Student found = dao.findByEmail("brian@ufc.com");
        assertEquals("Ortega", found.getLastName());
    }

    @Test
    void testFindAll() {
        List<Student> before = dao.findAll();
        Student s = new Student();
        s.setFirstName("Nate");
        s.setLastName("Diaz");
        s.setEmail("nate@ufc.com");
        dao.save(s);

        List<Student> after = dao.findAll();
        assertTrue(after.size() > before.size());
    }

    @Test
    void testUpdate() {
        Student s = new Student();
        s.setFirstName("Nick");
        s.setLastName("Diaz");
        s.setEmail("nick@ufc.com");
        dao.save(s);

        s.setLastName("Diaz");
        Student updated = dao.update(s);
        assertEquals("Diaz", updated.getLastName());
    }

    @Test
    void testDeleteById() {
        Student s = new Student();
        s.setFirstName("Nassourdin");
        s.setLastName("Imavov");
        s.setEmail("nassourdin@ufc.com");
        dao.save(s);

        boolean deleted = dao.deleteById(s.getId());
        assertTrue(deleted, "deleteById should return true for existing entity");
        assertNull(dao.findById(s.getId()), "After deletion, entity should be null");
    }

    @Test
    void testHomeworkRelation() {
        Student s = new Student();
        s.setFirstName("Test");
        s.setLastName("User");
        s.setEmail("test.user@ufc.com");

        Homework hw = new Homework();
        hw.setDescription("Some homework excercises given by coaches");
        hw.setDeadline(LocalDate.now());
        hw.setMark(9);

        s.addHomework(hw);
        dao.save(s);

        Student db = dao.findById(s.getId());
        assertEquals(1, db.getHomeworks().size());
        Homework fromDb = db.getHomeworks().iterator().next();
        assertEquals("Some homework excercises given by coaches", fromDb.getDescription());
    }
}