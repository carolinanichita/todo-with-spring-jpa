package cn.todo.repositories;

import cn.todo.domains.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    Todo findByTitle(String title);

    List<Todo> findAllByType(TodoType todoType);

    Todo findByDateCreatedGreaterThanEqual(Date dateCreated);

    Todo findByDoneAndDateDone(Boolean done, Date dateDone);

    List<Todo> findAllByDone(Boolean done);

    List<Todo> findAllByDateCreatedAfter(Date dateCreated);

    long countAllByDone(Boolean done);

    long countAllByDateCreatedGreaterThanAndDueDate(Date dateCreated, Date dueDate);

    long deleteAllByDone(Boolean done);

    List<Todo> findAllByDoneAndDateDoneAfter(Boolean done, Date dateDone);

    Todo save(Todo todo);

    boolean existsById(Long id);

    void deleteById(Long id);

    Optional<Todo> findById(Long id);
}
