package Splitwise;

import Splitwise.UserRepository.UserRepository;
import Splitwise.constants.Commands;
import Splitwise.enums.ExpenseType;
import Splitwise.service.SplitwiseService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));

        SplitwiseService splitwiseService = new SplitwiseService();
        UserRepository userRepository = new UserRepository();

        while(scanner.hasNext()){
            String inpString = scanner.nextLine();
            String[] inp = inpString.trim().split(" ");
            try{
                switch (inp[0]){
                    case Commands.SHOW:
                        System.out.println(inpString);
                        splitwiseService.show(inp);
                        System.out.println("----------------------");
                        break;
                    case Commands.EXPENSE:
                        System.out.println(inpString);
                        splitwiseService.splitExpense(inp);
                        System.out.println("----------------------");
                        break;
                }
            }catch (Exception e){
                System.out.println("INVALID INPUT");
            }

        }
    }
}
