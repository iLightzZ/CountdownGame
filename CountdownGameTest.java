//CountdownGame driver class
//CountdownGameTest.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class CountdownGameTest
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    
    CountdownGame.introduction(); //game introduction
    CountdownGame.rules(); //game rules/HTP 
    
    int players = 0; //no. of players
    int rounds = 0; //no. of rounds
    boolean continueLoop = true; //determines if more input is needed
    
    do{
      try{
        System.out.printf("%n%nPlease enter the number of players: ");
        players = input.nextInt();
        System.out.printf("%nPlease enter the number of rounds you want to play: ");
        rounds = input.nextInt();
        
        while(players < 1 || rounds < 1){
          System.out.printf("%nPlayers and rounds range must be greater than zero. Please try again.%n%n");
          System.out.printf("Please enter the number of players: ");
          players = input.nextInt();
          System.out.printf("%nPlease enter the number of rounds you want to play: ");
          rounds = input.nextInt();
        }//end while
        
        continueLoop = false; //input successful; stop looping
      }//end try
      
      catch(InputMismatchException inputMismatchException){
        System.err.printf("%nException: %s%n", inputMismatchException);
        input.nextLine(); //discard input so user can try again
        System.out.printf("Invalid input. Please try again.%n%n");
      }//end catch 

    }while(continueLoop); //end do...while

    CountdownGame game = new CountdownGame(players, rounds); //object instantiation
    
    game.setPlayerNames();
    
    
    game.start();
  }//end main
}//end class
  