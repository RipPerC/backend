package org.home.backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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

    public Film () {
        //Nothing
    }

    public Film (long id, String title, String director, Integer year, String audio, String subtitle, String format,
                Boolean downloaded, Date creationDate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.audio = audio;
        this.subtitle = subtitle;
        this.format = format;
        this.downloaded = downloaded;
        this.creationDate = creationDate;
    }

    public Film (String title, String director, Integer year, String audio, String subtitle, String format, Boolean downloaded) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.audio = audio;
        this.subtitle = subtitle;
        this.format = format;
        this.downloaded = downloaded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Boolean getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Film {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", audio='" + audio + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", format='" + format + '\'' +
                ", downloaded=" + downloaded +
                ", creationDate=" + creationDate +
                '}';
    }
}