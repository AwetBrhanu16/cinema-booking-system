package com.example.cinema_ticket_system.service;

import com.example.cinema_ticket_system.converter.ShowConverter;
import com.example.cinema_ticket_system.dto.ShowDto;
import com.example.cinema_ticket_system.dto.ShowSeatRequest;
import com.example.cinema_ticket_system.entities.*;
import com.example.cinema_ticket_system.enums.SeatType;
import com.example.cinema_ticket_system.repository.MovieRepo;
import com.example.cinema_ticket_system.repository.ShowRepo;
import com.example.cinema_ticket_system.repository.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    @Autowired
    private ShowRepo showRepo;

    public String addShow(ShowDto showDto) {
        Show show = ShowConverter.showDtoToShow(showDto);

        Optional<Movie> movieOpt = movieRepo.findById(showDto.getId());

        if (movieOpt.isEmpty()) {
            throw new RuntimeException("Movie not found");
        }

        Optional<Theater> theaterOpt = theaterRepo.findById(showDto.getTheaterId());

        if (theaterOpt.isEmpty()) {
            throw new RuntimeException("Theater not found");
        }

        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();

        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepo.save(show);

        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepo.save(movie);
        theaterRepo.save(theater);


        return "Show has been added Successfully";
    }

    public String associateShowSeats(ShowSeatRequest showSeatRequest) {

        Optional<Show> showOpt = showRepo.findById(showSeatRequest.getShowId());

        if (showOpt.isEmpty()) {
            throw new RuntimeException("Show not found");
        }

        Show show = showOpt.get();
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = show.getShowSeatList();


        for (TheaterSeat theaterSeat : theaterSeatList) {

            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNumber());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if (showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatRequest.getPriceOfClassicSeat());
            }else {
                showSeat.setPrice(showSeatRequest.getPriceOfPremiumSeat());
            }

            showSeat.setShow(show);
            showSeat.setSeatAvailable(true);
            showSeat.setFoodContains(true);

            showSeatList.add(showSeat);
        }

        showRepo.save(show);
        return "Show has been associated to the seat";
    }

    public List<ShowDto> getAllShows() {

        return showRepo.findAll().stream()
                .map(show -> new ShowDto(
                        show.getMovie().getId(),
                        show.getTheater().getTheaterId(),
                        show.getShowId(),
                        show.getTime(),
                        show.getDate(),
                        new
                                Movie(
                                show.getMovie().getMoviename(),
                                show.getMovie().getDuration(),
                                show.getMovie().getRating(),
                                show.getMovie().getReleaseDate(),
                                show.getMovie().getGenre(),
                                show.getMovie().getLanguage()
                        )

                )).collect(Collectors.toList());
        }
}


