package service;

import repository.SnakeRepository;

import java.util.Map;
import java.util.Scanner;

public class SnakeService {
     static void initializeSnakeRepository(Scanner sc){
         SnakeRepository snakeRepository = new SnakeRepository();
         Map<Integer,Integer> snakeMap = SnakeRepository.getStartEndMap();
         int snakeNum = sc.nextInt();
         sc.nextLine();
         while((snakeNum--)>0){
            String[] snakePos = sc.nextLine().trim().split(" ");
             int start=Integer.parseInt(snakePos[0]);
             int end=Integer.parseInt(snakePos[1]);
             snakeMap.put(start,end);
         }
    }
}
