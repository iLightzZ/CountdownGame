//Countdown game non-driver class
//CountdownGame.java
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
//import java.util.Timer;
//import java.util.TimerTask;

public class CountdownGame 
{
  private Random rand = new Random();
  private Scanner input = new Scanner(System.in);
  
  //instance variables
  private final String[] consonants = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
  private final String[] vowels = {"A", "E", "I", "O", "U"};
  private int[] playerScores; //array of the players scores. (ignore element zero)
  private String[] playerNames; //array of each player's name (ignore element zero)
  private final int players; //no. of players
  private final int rounds; //no. of rounds
  
  public CountdownGame(int players, int rounds)
  {
    this.players = players;
    this.rounds = rounds;
    
    playerScores = new int[players+1];
    
    for(int i = 0; i < players; ++i) { playerScores[i] = 0; }
  }//end constructor
  
  //get methods for instance variables players and rounds
  public int getPlayers() { return players; }
  public int getRounds() { return rounds; }
  
  
  public static void introduction()
  {
    System.out.printf(":===========:%n  COUNTDOWN%n:===========:%nHello and welcome to Countdown!%nCountdown is a popular British game show invloving word and number tasks.%nCountdown is one of the longest-running game shows in the world.%nIts first episode aired on the 2nd of Novemeber, 1982!");
    
  }//end introduction
  
  public static void rules()
  {
    System.out.printf("%n%n-------------------%nHow to play/rules:%n-------------------%n*NOTE: These rules have been altered to my liking.*%n%nThere are two types of rounds.%nThe first type of round is the Letters round.%n%nLetters round:%n%n-The round's current player will be choosing between two letter choices, vowels or consonants. After the player chose his set of letters (maximum of nine letters), all contestants have to come up with a word from the conjointed letters in thirty seconds.%n- The player with the longer, but valid, word scores one point per letter. If the player used every letter to make a valid word, that player recieves 18 points.%n%n*Example*:%nContestant One chooses five consonants, then three vowels, then another consonant.%n%nSelection (word) is:%nG Y H D N O E U R%n%nContestant One declares 7, while Contestant Two declares 8.%nContestant One reveals the word 'younger', but Contestant Two reveals the word 'hydrogen' and scores 8 points. Contestant One does not score.%nThe word 'greyhound' would have scored 18 points for using all nine letters.%n%n");
    System.out.printf("%n%nThe second type of round is the Numbers round.%n%nNumbers round:%n%n-The round's current player will choose six numbers of either a small number or a large number. A small number is a number that is from 1 to 10, whereas a large number can be 25, 50, 75, or 100.%n-Once the player fninshed picking his set of six numbers, a random generated target, from 100 to 999, will appear on the screen. Contestants have to use the six numbers on the screen to reach the target in the allocated time.%n-Contestants may use only the four basic operations of addition, subtraction, multiplication and division, and do not have to use all six numbers. A number may not be used more times than it appears on the board.%n-Contestants must show/explain their work at the end of the round. If their work is flawed they score no points.%n-Only the contestant whose result is closer to the target number scores points: 10 for reaching it exactly, 7 for being 1�5 away, 5 for being 6�10 away. Both score if they reach the same result, or if their results are the same distance away.%n%n*Example*:%nContestant One requests two large numbers and four small numbers.%n%nSelection is:%n75 50 2 3 8 7%n%nTarget is: 812%n%nContestant One declares 813, while Contestant Two declares 815.%nContestant One is closer and so reveals: 75 + 50 � 8 = 117, and 117 � 7 � (3 � 2) = 813, which scores 7 points for being 1 away. Contestant Two does not score.%nThe equation 50 + 8 = 58, and 7 � 2 � 58 = 812 would have scored 10 points.%n%n");
  }//end rules method
  
  //public void beginTimer() { myTimer.scheduleAtFixedRate(task, 1000, 1000); }
  
  public void setPlayerNames()
  {
    playerNames = new String[playerScores.length];
    for(int i = 1; i < playerScores.length; ++i)
    {
      System.out.printf("%nPlayer #%d name: ", i);
      String name = input.nextLine();
      playerNames[i] = name;
      System.out.println();
    }//end for
  }//end method
  
  private void lettersRound()
  {
    int userInput = 0;
    String word = ""; //our random generated 9-letter word
    
    System.out.printf("%nLetters round:%n%n");
    for(int i = 1; i < 10; ++i)
    {
      int randConsonant = rand.nextInt(20);
      int randVowel = rand.nextInt(5);
      System.out.printf("%nEnter the value '1' to get a consonant, '2' to get a vowel.%n");
      userInput = input.nextInt();
      
      while(userInput < 0 || userInput > 2) //error-trapping the user's input
      { 
        System.out.printf("Invalid input. Please read the instructions and try again. ");
        System.out.printf("Enter the value '1' to get a consonant, '2' to get a vowel.%n");
        userInput = input.nextInt();
      }
      
      //adding the letters together
      if(userInput == 1) { word += consonants[randConsonant];  }
      else { word += vowels[randVowel]; }
      word += " "; //adding a space between the letters to make it much more comprehensible
      
      if(i == 9) { break; }
      System.out.printf("Your word so far:%n%s%n%n", word);
      
    }//end for loop
    
    System.out.printf("%nSelection is:%n%s%n%nYou have thirty seconds to make a word out of this selection.%n", word);
    System.out.printf("One of the players start the timer on their device..%n");
    
    System.out.printf("%nSelection has 'unlucky' letters or letters that you can not make a word of? Well no fear, enter '1' to try again or any other value to skip.%n");
    int retry = input.nextInt(); 
    
    if(retry == 1) { lettersRound(); }
   
    //beginTimer();
    else
    {
      System.out.printf("%n%n----------------------------------%n%n*After you finished thirty seconds*%n%n----------------------------------%n");
      enterScores();
    }
  }//end lettersRound method
  
  private void numbersRound()
  {
    final int[] smallNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    final int[] largeNumbers = {25, 50, 75, 100};
    
    String userNumbers = ""; //the small and large numbers selected by the user. (demonstrational purposes only)
    
    System.out.printf("%nNumbers round:%n%n");
    for(int i = 1; i <= 6; ++i)
    {
      int randSmallNum = rand.nextInt(20);
      int randLargeNum = rand.nextInt(4);
      
      System.out.printf("Enter the value '1' to get a small number, '2' to get a large number: ");
      int userInput = input.nextInt();
      
      while(userInput < 0 || userInput > 2) //error-trapping the user's input
      { 
        System.out.printf("Invalid input. Please read the instructions and try again. ");
        System.out.printf("Enter the value '1' to get a small number, '2' to get a large number.%n");
        userInput = input.nextInt();
      }
      
      if(userInput == 1) { userNumbers += smallNumbers[randSmallNum]; }
      else { userNumbers += largeNumbers[randLargeNum]; }
      userNumbers += " ";
      
      if(i == 6) { break; }
      System.out.println("Your numbers so far:");
      System.out.printf("%s%n%n", userNumbers);
      
    }//end for loop
    int number = 100 + rand.nextInt(899); //generate a random number from 100 to 999
    
    System.out.println("Your numbers are:");
    System.out.printf("%s%n", userNumbers);
    
    System.out.printf("%nRandomly generated target is: %d%n%n", number);
    
    System.out.printf("You have forty five seconds to reach this target.%n%nSomeone start the timer please....");
    System.out.printf("%n%n----------------------------------%n%n*After you finished forty five seconds*%n%n----------------------------------%n");
    enterScores();
  }//end method numbersRound
  
  private void enterScores()
  {
    int score = 0;
    for(int i = 1; i < playerScores.length; i++)
    {
      System.out.printf("Enter %s's (Player #%d) score this round: ", playerNames[i], i);
      score = input.nextInt();
      
      while(score < 0 || score > 18) //error-trapping the user's input
      {
        System.out.printf("%nInvalid input. Read the instructions carefully and try again. ");
        System.out.printf("Enter %s's (Player #%d) score this round: ", playerNames[i], i);
        score = input.nextInt();
      }
      
      playerScores[i] += score;
      score = 0;
    }//end for loop
  }//end enterScores method
  
  private void printScores()
  {
    
    System.out.println("Scores:");
    for(int i = 1; i < playerScores.length; i++)
    {
      System.out.printf("%s (Player %d): (%d points)%n", playerNames[i], i, playerScores[i]);
    }//end for loop
    System.out.printf("%n%n");
  }//end method
  
  private void sortRankings(int[] scores)
  {
    //using the sort method to sort the scoreboard then printing the array later on in the game in the reverse order
    for(int i = 1; i < scores.length; i++)
    {
      int smallest = i; //fint index of remaining array
      
      //loop to find index of smallest element
      for(int index = i + 1; index < scores.length; index++)
      {
        if(scores[index] < scores[smallest]) { smallest = index; }
      }//end inner for loop
      
      swap(playerScores, i, smallest); //swap smallest element into position
      
    }//end outer for loop
  }//end rankings method
  
  private void swap(int[] scores, int first, int second)
  {
    //swapping the player's scores
    int temporary = scores[first];
    scores[first] = scores[second];    
    scores[second] = temporary;
    
    //swapping the player's names
    String temp = playerNames[first];
    playerNames[first] = playerNames[second];
    playerNames[second] = temp;
    
  }//end method swap
  
  public void start()
  { 
    System.out.printf("%nNow that that's out of the way, let us begin!%n----------------------------------------------%n");
    System.out.printf("%nGame overview:%nNumber of players: %d%nNumber of rounds: %d%n%n", getPlayers(), getRounds());
    
    
    for(int i = 1; i <= getRounds(); ++i)
    {
      if(i == (getRounds())) { System.out.printf("%nFINAL ROUND:%n--------------------------------------"); }
      else { System.out.printf("%nROUND #%d:%n--------------------------------------", i); }
      
      System.out.printf("%n%n%s, It's your turn this round to choose the letters and the numbers.%n", playerNames[i]);
      lettersRound();
      
      
      numbersRound();
      if(i == getRounds()) { break; }
      System.out.printf("%n%n--------------------------------%nThat concludes round #%d folks!%n--------------------------------%nLet's check out the scores, shall we?%n%n", i);
      printScores();
      
    }//end for loop
    
    System.out.printf("%n%n--------------------------------%nThat concludes the FINAL round!%n--------------------------------%nLet's check out who came out on top!%n%n");
    sortRankings(playerScores);
    
    int place = 1;
    int iteration = playerScores.length - 1;
    for(int i = iteration; i > 0; --i)
    {
      System.out.printf("#%d) %s: (%d points)%n", place, playerNames[i], playerScores[i]);
      ++place;
    }//end for loop
    
    System.out.printf("%nAs it is clear from the scoreboard ladies and gents, %s is your Countdown WINNER!%n%n", playerNames[iteration]);
  }//end start method
  
}//end class
