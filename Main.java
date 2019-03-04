import java.util.*;

class Main 
{
  public static void main(String[] args) 
  {
    String occupied;
    String name1;
    String name2;
    String insult = "";
    String choice;

    int rand = (int)(Math.random() * 10);
    int r = 0;
    int c = 0;
    int count = 0;
    int tie = 0;
    int totalRows = 6;
    int totalColumns = 7;
    int guess = -1;
    int inRow = 4;
    int chris = 1;

    boolean customize = false;

    Scanner keyboard = new Scanner(System.in);

    System.out.println("\n\n\n\n");
    System.out.println("                   Welcome to our Connect 4 game!!\n\nYou will be able to enter your name, so choose anything you would like and you\nwill be reffered to that as the game goes on.\n\nYou will also have a choice to customize the game.\nWhen prompted if you want to customize the game simply type (Y) or (N).\nMake sure that you choose enough rows and columns for the\ngame to still be able to be won.\n\nThis game was made by Chris 1(M), Travis(Hot), and Chris 2(B)\nENJOYYYY!!!!!\n");
    System.out.print("Player one insert your name: ");
    name1 = keyboard.nextLine();
    String name3 = name1 + " (X)";
    System.out.println();
    System.out.print("Player two insert your name: ");
    name2 = keyboard.nextLine();
    String name4 = name2 + " (O)";
    System.out.println();

    while(customize != true)
    {
      System.out.print("Would you like to customize the game(Y or N): ");
      choice = keyboard.nextLine();
      if(!(choice.toLowerCase().equals("y")) && !(choice.toLowerCase().equals("n")))
      {
        space();
        System.out.println("Must enter Yes (Y), or No (N).");
        System.out.println();
      }
      else if(choice.toLowerCase().equals("n"))
      {
        space();
        customize = true;
      }
      else if(!(choice.toLowerCase().equals("n")))
      {
        System.out.println();
        System.out.println("How many rows? (Standard is 6)");
        totalRows = keyboard.nextInt();
        
        while(totalRows > 10 || totalRows < 3)
        {
          space();
          System.out.println("Must be between 3 or 10!");
          System.out.println();
          System.out.println("How many rows? (Standard is 6)");
          totalRows = keyboard.nextInt();
        }
        
        System.out.println();
        System.out.println("How many columns? (Standard is 7)");
        totalColumns = keyboard.nextInt();
        
        while(totalColumns > 10 || totalColumns < 3)
        {
          space();
          System.out.println("Must be between 3 or 10!");
          System.out.println();
          System.out.println("How many columns? (Standard is 7)");
          totalColumns = keyboard.nextInt();
        }

        System.out.println();
        System.out.println("How many spaces in a row until you win would you like?");
        inRow = keyboard.nextInt();
        while(inRow > totalRows || inRow > totalColumns || inRow < 3)
        {
          space();
          System.out.println("Can't be longer than rows or columns or less than 3!");
          System.out.println();
          System.out.println("How many spaces in a row until you win would you like?");
          inRow = keyboard.nextInt(); 
        }
        customize = true;
        space();
      }
    }

    Board board = new Board(totalRows, totalColumns, inRow);
    board.switchPlayer();
    System.out.println(board);

    
    while(board.check(r, c) != true)
    {
      String winner = board.getPlayer() == "X"? name1 : name2;
      String playerName = board.getPlayer() == "X"? name3 : name4;
      guess = getColumn(playerName);
      insult = win(rand, playerName, totalRows, totalColumns, inRow);
      
      if(guess <= totalColumns)
      {
        c = guess - 1;
      }
      else 
      {
        space();
        System.out.println(board);
        System.out.println("Invalid move. Try again.");
        System.out.println();
        continue;
      }

      for(int i = totalRows - 1 ; i > 0; i--)
      {
        occupied = board.getIndex(i, c);
        if(occupied.equals("-"))
        {
          count++;
        }
      }

      if(!(board.getIndex(0, c).equals("-")))
      {
        space();
        System.out.println(board);
        System.out.println("That column is full. Try again.");
        System.out.println();
        continue;
      }
      
      r = count;
      board.replace(r, c);
      board.switchPlayer();
      count = 0;
      tie++;
      space();
      System.out.println(board);
      System.out.println();
      System.out.println("Previous move was column " + guess + "\n");

      if(tie == (totalRows * totalColumns))
      {
        System.out.println();
        System.out.println("It's a tie.");
        break;
      }

      if(board.check(r, c) == true)
      {
        System.out.println();
        board.switchPlayer();
        System.out.println("WINNER!!!! " + playerName + "\n");
        System.out.println(insult);
      
      }
    }  
  }
  public static void space()
  {
    for(int i = 0; i < 55; i++)
    {
      System.out.println();
    }
  } 

  public static int getColumn(String playerName)
  {
    Scanner keyboard = new Scanner(System.in);
    int col = -1;
    while(col == -1) 
    {
      col = tryGetColumn(playerName);
      if(col == -1) 
      {
        System.out.println("Whoops, you did something wrong");
        System.out.println();
        System.out.println("Please input a valid number and not a letter\n");
      }
    }
    return col;
  }
  
  public static int tryGetColumn(String playerName)
  {
    Scanner keyboard = new Scanner(System.in);

    try
    {
      System.out.print(playerName + " pick a column: ");
      return keyboard.nextInt();
    }
    catch(Exception e)
    {
      return -1;
    }
  }
  
  public static String win(int rand, String winner, int rows, int cols, int inRow)
  {
    Board board = new Board(rows, cols, inRow);

    List<String> insults = Arrays.asList(
      winner + ", that's what I call a Victory Royale!"
      , "Wow good moves " + winner + " good win!"
      , winner + " has a massive brain!"
      , "Woah " + winner + ", you should go pro!"
      , winner + " is clearly the superior connect 4 player!"
      , "That was brutal " + winner + ", you might want to go easy next time!"
      , "Good looks " + winner + ", you WON!"
      , winner + ", you're the best!!"
      , "This is almost too easy for " + winner + "!"
      , winner + " is a sexy beast! You're on fire!!!! "
    );
    return insults.get(rand);
  }
}