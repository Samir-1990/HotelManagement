package az.dev.repository;

import az.dev.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDao extends CrudRepository<Booking, Long> {

    List<Booking> findAllByActive(Integer active);

    Booking findByIdAndActive(Long id, Integer active);
}
