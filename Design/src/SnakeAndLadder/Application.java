import service.BoardService;
import service.InMemoryService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));

        InMemoryService inmemory = new InMemoryService(sc);
        BoardService boardService = new BoardService();
        try{
            boardService.startGame();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
