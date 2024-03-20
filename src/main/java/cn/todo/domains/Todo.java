package cn.todo.domains;

import cn.todo.events.TodoEvent;
import cn.todo.utils.validators.TitleConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NamedQuery(name = "Todo.findAllDone", query = "SELECT t FROM Todo t WHERE t.done = true")
@NamedQuery(name = "Todo.findAllTitle", query = "SELECT t FROM Todo t WHERE t.title = ?1")
@XmlRootElement(name = "todo")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Todo extends AbstractAggregateRoot<Todo> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @TitleConstraint
    private String title;

    @JsonIgnore
    private String description;

    private boolean done;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dateCreated;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dateDone;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date lastUpdated;

    @ManyToOne
    @JsonProperty("type")
    private TodoType type;

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
        System.out.println("I was here...");
        registerEvent(this);
    }

    @EventListener
    public void beforeSave(Todo todo) {
        System.out.println("I am here");
        System.out.println(todo.toString());
        if (null != todo.getDateDone()) {
            todo.setDone(true);
        }
    }

    public void afterSave() {
        registerEvent(new TodoEvent());
    }
}