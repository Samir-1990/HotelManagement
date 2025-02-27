package az.dev.repository;


import az.dev.entity.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeDao extends CrudRepository<RoomType, Long> {

    RoomType findByIdAndActive(Long id, Integer active);

}
