package org.home.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="EPISODE", schema = "DATA")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "FK_SERIE", nullable = false)
    private Serie serie;
    @Column(name = "SEASON")
    private Integer season;
    @Column(name = "EPISODE")
    private Integer episode;
    @Column(name = "AUDIO")
    private String audio;
    @Column(name = "SUBTITLE")
    private String subtitle;

    public Episode () {
        //Nothing
    }

    public Episode (Serie serie, Integer season, Integer episode, String audio, String subtitle) {
        this.serie = serie;
        this.season = season;
        this.episode = episode;
        this.audio = audio;
        this.subtitle = subtitle;
    }

    public Episode(long id, Serie serie, Integer season, Integer episode, String audio, String subtitle) {
        this.id = id;
        this.serie = serie;
        this.season = season;
        this.episode = episode;
        this.audio = audio;
        this.subtitle = subtitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
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

    @Override
    public String toString() {
        return "Episode {" +
                "id=" + id +
                ", serie=" + serie +
                ", season=" + season +
                ", episode=" + episode +
                ", audio='" + audio + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
