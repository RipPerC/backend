package org.home.backend.model;

import jakarta.persistence.*;
import lombok.*;

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

    public Book(String title) {
        this.title = title;
    }

}
