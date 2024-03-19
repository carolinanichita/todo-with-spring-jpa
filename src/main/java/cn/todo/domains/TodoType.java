package cn.todo.domains;

import cn.todo.utils.adapters.xml.DataFormatXmlAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"code", "dateCreated", "lastUpdated"})
@JsonPropertyOrder({"description", "code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoType {

    @Id
    @NotBlank
    @Size(min = 4, max = 10)
    private String code;

    @XmlTransient
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @XmlJavaTypeAdapter(DataFormatXmlAdapter.class)
    private Date dateCreated;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @XmlJavaTypeAdapter(DataFormatXmlAdapter.class)
    private Date lastUpdated;
}
