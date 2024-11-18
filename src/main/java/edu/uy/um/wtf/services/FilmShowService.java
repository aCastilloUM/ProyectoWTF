package edu.uy.um.wtf.services;


import edu.uy.um.wtf.entities.*;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.repository.FilmShowRepository;
import edu.uy.um.wtf.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmShowService {

    @Autowired
    private FilmShowRepository filmShowRepository;

    @Autowired
    private SeatsRepository seatsRepository;

    public FilmShow addFunction(Date Date, LocalTime time, Movie movie, Branch branch, Room room, String Special_Effects, String Language) {
        if (Date == null || time == null || movie == null || room == null || Special_Effects == null || Language == null || branch == null) {
            return null;
        }
        if (Special_Effects.trim().equals("") || Language.trim().equals("")) {
            return null;
        }

        FilmShow filmShow = FilmShow.builder()
                .date(Date)
                .time(time)
                .movie(movie)
                .branch(branch)
                .room(room)
                .specialEffects(Special_Effects)
                .language(Language)
                .duration(movie.getDuration())
                .build();

        filmShowRepository.save(filmShow);
        createSeatsForFilmShow(filmShow);


        return filmShow;
    }

    private void createSeatsForFilmShow(FilmShow filmShow) {
        // Crear los asientos (15 filas por 10 columnas)
        List<Seats> seats = new ArrayList<>();
        for (int row = 1; row <= 15; row++) {
            for (int col = 1; col <= 10; col++) {
                Seats seat = new Seats();
                seat.setRow(row);
                seat.setColumn(col);
                seat.setOccupied(false);  // Inicializa como disponible
                seat.setFilmShow(filmShow);  // Relaciona con la FilmShow
                seats.add(seat);
            }
        }

        // Guardar los asientos en la base de datos
        seatsRepository.saveAll(seats);
    }



    public Optional<FilmShow> findById(Long id) {
        if (id == null) {
            return null;
        }
        return filmShowRepository.findById(id);
    }

    public List<FilmShow> getAll() {
        return filmShowRepository.findAll();
    }

    public List<FilmShow> findByDate(Date date) {
        if (date == null) {
            throw new EntityNotFoundException();
        }

        return filmShowRepository.findByDate(date);
    }

    public List<FilmShow> findByMovie(Movie movie) {
        if (movie == null) {
            return null;
        }
        return filmShowRepository.findByMovie(movie);
    }

    public List<FilmShow> findByRoom(Room room) {
        if (room == null) {
            return null;
        }
        return filmShowRepository.findByRoom(room);
    }

    public List<FilmShow> findBySpecialEffects(String specialEffects) {
        if (specialEffects == null || specialEffects.isEmpty()) {
            return null;
        }
        return filmShowRepository.findBySpecialEffects(specialEffects);
    }

    public List<FilmShow> findByLanguage(String language) {
        if (language == null || language.isEmpty()) {
            return null;
        }
        return filmShowRepository.findByLanguage(language);
    }

    public FilmShow updateFunction(FilmShow filmShow) {
        if (filmShowRepository.existsById(filmShow.getId())) {
            return filmShowRepository.save(filmShow);
        }
        return null;
    }

    public void deleteFunction(Long id) {
            filmShowRepository.deleteById(id);
    }

    public List<FilmShow> findByMovieId(Long id) {
        if (id == null) {
            return null;
        }
        return filmShowRepository.findByMovieId(id);
    }

    public List<FilmShow> getFilmShowsByMovieId(Long movieId) {
        return filmShowRepository.findByMovieId(movieId);
    }

    public Object getFilmShowById(Long filmShowId) {
        return filmShowRepository.findById(filmShowId);
    }
}
