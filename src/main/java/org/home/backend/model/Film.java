package org.home.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="FILM", schema = "DATA")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "AUDIO")
    private String audio;
    @Column(name = "SUBTITLE")
    private String subtitle;
    @Column(name = "FORMAT")
    private String format;
    @Column(name = "DOWNLOADED")
    private Boolean downloaded;
    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    @Temporal (TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    public Film (String title, String director, Integer year, String audio, String subtitle,
                 String format, Boolean downloaded) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.audio = audio;
        this.subtitle = subtitle;
        this.format = format;
        this.downloaded = downloaded;
    }
}