package cn.todo.repositories;

import cn.todo.domains.TodoType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoTypeRepository extends PagingAndSortingRepository<TodoType, String> {
    List<TodoType> findAllByDateCreatedAfter(Date dateCreated, Pageable pageable);
}
