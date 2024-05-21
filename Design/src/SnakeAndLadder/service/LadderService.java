package service;

import repository.LadderRepository;
import repository.SnakeRepository;

import java.util.Map;
import java.util.Scanner;

public class LadderService {
    public static void initializeLadderRepository(Scanner sc) {
        LadderRepository ladderRepository = new LadderRepository();
        Map<Integer,Integer> ladderMap = LadderRepository.getStartEndMap();
        int snakeNum = sc.nextInt();
        sc.nextLine();
        while((snakeNum--)>0){
            String[] snakePos = sc.nextLine().trim().split(" ");
            int start=Integer.parseInt(snakePos[0]);
            int end=Integer.parseInt(snakePos[1]);
            ladderMap.put(start,end);
        }
    }
}
