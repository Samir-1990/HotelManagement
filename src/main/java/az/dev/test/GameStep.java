package az.dev.test;

import az.dev.test.entity.GameStepEntity;
import az.dev.test.repository.GameStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameStep {

    private final GameStepRepository gameStepRepository;

    public String play(int x, int y) {
        saveToDb('A', x, y);

        return "SUCCESS";
    }

    private void saveToDb(Character player, int x, int y) {
        GameStepEntity gameStep = new GameStepEntity();
        gameStep.setPlayer(player);
        gameStep.setX(x);
        gameStep.setY(y);

        gameStepRepository.save(gameStep);
    }

    public List<GameStepEntity> getGameSteps() {
        return gameStepRepository.findAll();
    }

    public GameStepEntity getGameSteps(long id) {
        return gameStepRepository.findById(id).get();
    }
}
