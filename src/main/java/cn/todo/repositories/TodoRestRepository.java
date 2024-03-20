package cn.todo.repositories;

import cn.todo.domains.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface TodoRestRepository extends PagingAndSortingRepository<Todo, Long> {
    void save(Todo todo1);

//    @RestResource(exported = false)
//    void deleteById(Long id);

    @RestResource
    List<Todo> findAllByTypeCode(@Param("type") String code);
}
