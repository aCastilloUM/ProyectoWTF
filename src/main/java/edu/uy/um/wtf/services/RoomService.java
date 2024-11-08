package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room addRoom(String number, int capacity, int rows, int columns) {
        if (number != null || capacity <= 0 || rows <= 0 || columns <= 0) {
            return null;
        }

        Room room = Room.builder()
                .number(number)
                .capacity(capacity)
                .rows(rows)
                .columns(columns)
                .build();

        return roomRepository.save(room);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }


    public Optional<Room> findById(Long id) {
        if (id == null) {
            return null;
        }
        return roomRepository.findById(id);
    }

    public Room updateRoom(Room room) {
        if (room == null || room.getId() == null || !roomRepository.existsById(room.getId())) {
            return null;
        }
        return roomRepository.save(room);
    }

}