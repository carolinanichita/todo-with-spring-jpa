package cn.todo;

import cn.todo.controllers.TodoRestController;
import cn.todo.domains.Todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	public RepresentationModelProcessor<EntityModel<Todo>> todoProcessor() {
		return new RepresentationModelProcessor<EntityModel<Todo>>() {
			@Override
			public EntityModel<Todo> process(EntityModel<Todo> model) {
				model.add(linkTo(methodOn(TodoRestController.class).todoTypes()).withRel("todoTypes"));
				return model;
			}
		};
	}
}
