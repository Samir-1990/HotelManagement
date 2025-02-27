package az.dev.repository;

import az.dev.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao extends CrudRepository<Room, Long> {

    List<Room> findAllByActive(Integer active);

    Room findByIdAndActive(Long id, Integer active);

}
