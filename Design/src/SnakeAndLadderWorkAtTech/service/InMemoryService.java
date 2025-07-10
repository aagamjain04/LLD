package service;

import java.util.Scanner;

public class InMemoryService {
    public InMemoryService(Scanner sc) {
        SnakeService.initializeSnakeRepository(sc);
        LadderService.initializeLadderRepository(sc);
        PlayerService.initializePlayerRepository(sc);
        BoardService.initializeBoardRepository(sc);

    }
}
