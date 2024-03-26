package cn.todo.repositories;

import cn.todo.domains.TodoType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoTypeRepository extends PagingAndSortingRepository<TodoType, String> {

    List<TodoType> findAllByDateCreatedAfter(Date dateCreated, Pageable pageable);

    TodoType save(TodoType todoType);

    Optional<TodoType> findById(String code);

    boolean existsById(String code);

    void deleteById(String code);
}