package edu.uy.um.wtf.services;


import edu.uy.um.wtf.entities.Function;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class FunctionService {

    @Autowired
    private FunctionRepository functionRepository;

    public Function addFunction(Date Date, LocalTime time, Movie movie, Room room, String Special_Effects, String Language) {
        if (Date == null || time == null || movie == null || room == null || Special_Effects == null || Language == null) {
            return null;
        }
        if (Special_Effects.trim().equals("") || Language.trim().equals("")) {
            return null;
        }

        Function function = Function.builder()
                .date(Date)
                .time(time)
                .movie(movie)
                .room(room)
                .specialEffects(Special_Effects)
                .language(Language)
                .duration(movie.getDuration())
                .build();

        return functionRepository.save(function);
    }
    public List<Function> getAll() {
        return functionRepository.findAll();
    }

    public List<Function> findByDate(Date date) {
        if (date == null) {
            return null;
        }
        return functionRepository.findByDate(date);
    }

    public List<Function> findByMovie(Movie movie) {
        if (movie == null) {
            return null;
        }
        return functionRepository.findByMovie(movie);
    }

    public List<Function> findByRoom(Room room) {
        if (room == null) {
            return null;
        }
        return functionRepository.findByRoom(room);
    }

    public List<Function> findBySpecialEffects(String specialEffects) {
        if (specialEffects == null || specialEffects.isEmpty()) {
            return null;
        }
        return functionRepository.findBySpecialEffects(specialEffects);
    }

    public List<Function> findByLanguage(String language) {
        if (language == null || language.isEmpty()) {
            return null;
        }
        return functionRepository.findByLanguage(language);
    }

    public Function updateFunction(Function function) {
        if (functionRepository.existsById(function.getId())) {
            return functionRepository.save(function);
        }
        return null;
    }

    public void deleteFunction(Long id) {
        if (id != null) {
            functionRepository.deleteById(id);
        }
    }

}
