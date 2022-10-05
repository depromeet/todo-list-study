package com.depromeet.todolist.infrastructure.mysql;

import com.depromeet.todolist.domain.interfaces.ITodoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoJpaRepository implements ITodoRepository {
    private final EntityManager em;
    public TodoJpaRepository(EntityManager em){
        this.em=em;
    }

    @Override
    public List<TodoEntity> findAll() {
        return em.createQuery("select m from TodoEntity m", TodoEntity.class).getResultList();
    }

    @Override
    public Optional<TodoEntity> findById(long id) {
        List<TodoEntity> results = em.createQuery("select m from TodoEntity m where m.id =:id", TodoEntity.class)
                .setParameter("id", id)
                .getResultList();
        return results.stream().findAny();
    }

    @Override
    public TodoEntity save(TodoEntity todoItem) {
        this.em.persist(todoItem);
        return todoItem;
    }

    @Override
    public int delete(long id) {
        return this.em.createQuery("delete from TodoEntity m where m.id =:id")
                .setParameter("id",id).executeUpdate();
    }
}
