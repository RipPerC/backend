package org.home.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString()
@Entity
@Table(name="BOOK", schema = "DATA")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "AUTHOR")
    private String author;
    @ManyToOne
    private BookSage sage;
    @Column(name = "ORDER_SAGE")
    private Integer orderSage;
    @Column(name = "FILE_YEAR")
    private Integer fileYear;
    @Column(name = "DIGITAL")
    private Boolean digital;
    @Column(name = "NOTES")
    private String notes;
    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    @Temporal (TemporalType.TIMESTAMP)
    private Date creationDate = new Date();


    public Book(String title) {
        this.title = title;
    }

}
