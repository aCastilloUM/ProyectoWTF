package edu.uy.um.wtf.services;


import edu.uy.um.wtf.entities.Branch;
import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.repository.FilmShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmShowService {

    @Autowired
    private FilmShowRepository filmShowRepository;

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

        return filmShowRepository.save(filmShow);
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
}
