package cn.todo.domains;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@Data
@XmlRootElement
@JsonPropertyOrder(alphabetic = true)
public class TodoType {
    private String code;
    private String description;
    private Date dateCreated;
    private Date lastUpdated;

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Date getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(Date dateCreated) {
//        this.dateCreated = dateCreated;
//    }
//
//    public Date getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public void setLastUpdated(Date lastUpdated) {
//        this.lastUpdated = lastUpdated;
//    }
}
