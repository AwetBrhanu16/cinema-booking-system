package com.example.cinema_ticket_system.entities;

import com.example.cinema_ticket_system.enums.Genre;
import com.example.cinema_ticket_system.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MOVIE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String moviename;

    private Integer duration;

    @Column(scale = 2)
    private Double rating;

    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> shows = new ArrayList<>();


    public Movie(String moviename,
                 Integer duration,
                 Double rating,
                 Date releaseDate,
                 Genre genre,
                 Language language) {
        this.moviename=moviename;
        this.duration=duration;
        this.rating=rating;
        this.releaseDate=releaseDate;
        this.genre=genre;
        this.language=language;

    }
}
