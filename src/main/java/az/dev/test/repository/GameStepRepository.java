package az.dev.test.repository;

import az.dev.test.entity.GameStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStepRepository extends JpaRepository<GameStepEntity, Long> {
}
