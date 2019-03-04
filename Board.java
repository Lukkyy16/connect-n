public class Board
{
  private String [][] board;
  private String player;
  private int totalRows;
  private int totalColumns;
  private int count;
  private int inRow;



  public Board(int rows, int cols, int inARow)
  {
    totalRows = rows;
    totalColumns = cols;
    inRow = inARow;
    board = new String[rows][cols];
    
    for(int r = 0; r < board.length; r++)
    {
      for(int c = 0; c < board[0].length; c++)
      {
        board[r][c] = "-";
      }
    }
    player = "O";
  }
  public String getPlayer()
  {
    return player;
  }
  public String getIndex(int r, int c)
  {
    return board[r][c];
  }
  public void replace(int r, int c)
  {
    board[r][c] = player;
  }
  public int countLeft(int r, int c) 
  {
    int count = 0;
    int i = c;
    while(i > 0) 
    {

      if(board[r][i-1] != board[r][c]) 
      {
        break;
      }
      count++;
      i--;
    }
    return count;
  }

  public int countRight(int r, int c) 
  {
    int count = 0;
    int i = c;
    while(i < totalColumns-1)
    {
      if(board[r][i+1] != board[r][c])
      {
        break;
      }
      count++;
      i++;
    }
    return count;
  }

  public int countUp(int r, int c) 
  {
    int count = 0;
    int i = r;
    while(i > 0)
    {
      if(board[i-1][c] != board[r][c])
      {
        break;
      }
      count++;
      i--;
    }
    return count;
  }

  public int countDown(int r, int c) 
  {
    int count = 0;
    int i = r;
    while(i < totalRows-1)
    {
      if(board[i+1][c] != board[r][c])
      {
        break;
      }
      count++;
      i++;
    }
    return count;
  }

  public int countUpRight(int r, int c)
  {
    int count = 0;
    int i = r;
    int j = c;
    while(i > 0 && j < totalColumns-1)
    {
      if(board[i-1][j+1] != board[r][c])
      {
        break;
      }
      i--;
      j++;
      count++;
    }
    return count;
  }

  public int countUpLeft(int r, int c)
  {
    int count = 0;
    int i = r;
    int j = c;
    while(i > 0 && j > 0)
    {
      if(board[i-1][j-1] != board[r][c])
      {
        break;
      }
      i--;
      j--;
      count++;
    }
    return count;
  }

  public int countDownRight(int r, int c)
  {
    int count = 0;
    int i = r;
    int j = c;
    while(i < totalRows-1 && j < totalColumns-1)
    {
      if(board[i+1][j+1] != board[r][c])
      {
        break;
      }
      i++;
      j++;
      count++;
    }
    return count;
  }

  public int countDownLeft(int r, int c)
  {
    int count = 0;
    int i = r;
    int j = c;
    while(i < totalRows-1 && j > 0)
    {
      if(board[i+1][j-1] != board[r][c])
      {
        break;
      }
      i++;
      j--;
      count++;
    }
    return count;
  }
  
  public boolean checkD(int r, int c)
  {
    return (this.countUpRight(r, c) + this.countDownLeft(r, c) >= inRow - 1) ||
    (this.countDownRight(r, c) + this.countUpLeft(r, c) >= inRow - 1);
  }

  public boolean checkH(int r, int c)
  {
    return this.countLeft(r, c) + this.countRight(r, c) >= inRow - 1;
  }

  public boolean checkV(int r, int c)
  {
    return this.countUp(r, c) + this.countDown(r, c) >= inRow - 1;
  } 

  public boolean check(int r, int c)
  {
    return board[r][c] == "-" ? false : checkH(r, c) || checkV(r, c) || checkD(r, c);
  }

  public void switchPlayer()
  {

    if(player.equals("O"))
    {
      player = "X";
    }
    else 
    {
      player = "O";
    }
  }

  public String toString()
  {
    String picture = this.header();

    for(int i = 0; i < totalRows; i++)
    {
      picture += getRow(i);
    }
    return picture;
  }

  public String footer()
  {
    String space = "\n  |";

    for(int i = 0; i < totalColumns; i++)
    {
      space += "-----";
    }
    for(int i = 0; i < totalColumns - 1; i++)
    {
      space += '-';
    }
    return space + "|";
  }

  public String header()
  {
    String space = "";

    for(int i = 0; i < totalColumns; i++)
    {
      space += "     " + (i+1);
    }
    return space + "\n";
  }

  public String getRow(int i)
  {
    String row = "";
    
    for(int j = 0; j < totalColumns; j++)
    {
      row += "  |  " + board[i][j];
    }
    row += "  |";
    return i + 1 == totalRows ? row + this.footer() : row + this.rowBreak();
  }
  
  public String rowBreak()
  {
    String space = "  |";

    for(int i = 0; i < totalColumns-1; i++)
    {
      space += "-----+";
    }
    return "\n" + space + "-----|\n";
  }
}