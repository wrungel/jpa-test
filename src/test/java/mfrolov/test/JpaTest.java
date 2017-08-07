package mfrolov.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mfrolov.entity.ChildEntity;
import mfrolov.entity.ParentEntity;

public class JpaTest {

    private EntityManager em;
    private static final long PARENT_ID = 1L;

    @Before
    public void before() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void after() {
        EntityTransaction transaction = em.getTransaction();
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    @Test
    public void test() {

        createParent();

        em.flush();
        em.clear();
        ParentEntity parent = em.find(ParentEntity.class, 1L);
        System.out.println("version without children: " + parent.getVersion());

        createChild(1L, parent);

        em.flush();
        em.clear();
        parent = em.find(ParentEntity.class, 1L);
        System.out.println("version with 1 child: " + parent.getVersion());

        createChild(2L, parent);

        em.flush();
        em.clear();
        parent = em.find(ParentEntity.class, 1L);
        System.out.println("version with 2 children: " + parent.getVersion());

    }

    private void createParent() {
        ParentEntity parent = new ParentEntity();
        parent.setId(PARENT_ID);
        parent.setDescription(String.format("P%d", (Long) PARENT_ID));
        em.persist(parent);
    }

    private void createChild(Long id, ParentEntity parentEntity) {
        ChildEntity child = new ChildEntity();
        child.setId(id);
        child.setDescription(String.format("C%d", id));
        child.setParent(parentEntity);
        parentEntity.getChildren().add(child);
    }

}
