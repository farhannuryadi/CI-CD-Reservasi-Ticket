package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "films")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FilmEntity implements Serializable {

    @Id
    @Column(name = "film_id", length = 10)
    private String id;

    @NotEmpty(message = "film name is required")
    @Column(length = 150, nullable = false)
    private String filmName;

    @Column(name = "film_status", nullable = false)
    private Boolean filmStatus;

}
