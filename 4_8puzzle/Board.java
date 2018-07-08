// import java.util.Arrays;
// import java.util.Iterator;
import edu.princeton.cs.algs4.Queue;

public class Board
{

    private int n;
    private int[][] cells;


    public Board(int[][] blocks)
    {
       
       n = blocks.length;
       cells = new int[n][n];

       for (int i = 0; i < n; i++)
       {
          for (int j = 0; j < n; j++)
          {  
             cells[i][j] = blocks[i][j];
          }
       }

    }

    public int dimension()
    {

       return n;
    }

    public int hamming()
    {
       int hamDistance = 0;

       for (int i = 0; i < n; i++)
       {
          for (int j = 0; j < n; j++)
          {  
  
             if (cells[i][j] != 0 && cells[i][j] != indexConvertToCellNo(i, j))
             {
                hamDistance++; 
             }
          }
       }
       return hamDistance;
     }

     
     public int manhattan()
     {
       int manDistance = 0, x1, x0, y1, y0;

       for (int i = 0; i < n; i++)
       {
          for (int j = 0; j < n; j++)
          {  
             if (cells[i][j] != 0 && cells[i][j] != indexConvertToCellNo(i, j))
             {
                x1 = i;
                y1 = j;

                x0 = (cells[i][j]-1) / n;
                y0 = (cells[i][j]-1) % n;

                manDistance = manDistance + Math.abs(x1-x0) + Math.abs(y1-y0);
             }
          }
       }
       return manDistance;
     }


     public boolean isGoal()
     {  
  
       for (int i = 0; i < n; i++)
       {
          for (int j = 0; j < n; j++)
          {  
             if (i == n-1 && j == n-1 && cells[i][j] == 0)
             {
                return true;
             }

             if (cells[i][j] != indexConvertToCellNo(i, j))
             {
                return false;
             }
          }
       }
       return true;
     }


     public Board twin()
     {
       Board twin = new Board(cells);

       for (int i = 0; i < n; i++)
       {
          for (int j = 0; j < n-1; j++)
          { 
             if (cells[i][j] != 0 && cells[i][j+1] != 0)
             {
               
               twin.cells[i][j] = cells[i][j+1];
               twin.cells[i][j+1] = cells[i][j];
               return twin;
             }
          }
       }
       return twin;
     }


     public boolean equals(Object y)
     {

        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (this.n != that.n) return false;
        for (int i = 0; i < n; i++)
        {
          for (int j = 0; j < n; j++)
          { 
             if (this.cells[i][j] != that.cells[i][j]) return false;
          }
        }
        return true;
     }

     public Iterable<Board> neighbors()
     {
        int upi, downi, leftj, rightj;

        Queue<Board> nbq = new Queue<Board>();

        for (int i = 0; i < n; i++)
        {
          for (int j = 0; j < n; j++)
          { 
             if (cells[i][j] == 0)
             {
                upi = i-1;
                downi = i+1;
                leftj = j-1;
                rightj = j+1;

                if (upi >= 0)
                {
                  Board neighbor = new Board(cells);
                  neighbor.cells[upi][j] = cells[i][j];
                  neighbor.cells[i][j] = cells[upi][j];
                  nbq.enqueue(neighbor);
                }

                if (downi < n)
                {
                  Board neighbor = new Board(cells);
                  neighbor.cells[downi][j] = cells[i][j];
                  neighbor.cells[i][j] = cells[downi][j];
                  nbq.enqueue(neighbor);
                }

                if (leftj >= 0)
                {
                  Board neighbor = new Board(cells);
                  neighbor.cells[i][leftj] = cells[i][j];
                  neighbor.cells[i][j] = cells[i][leftj];
                  nbq.enqueue(neighbor);
                }

                if (rightj < n)
                {
                  Board neighbor = new Board(cells);
                  neighbor.cells[i][rightj] = cells[i][j];
                  neighbor.cells[i][j] = cells[i][rightj];
                  nbq.enqueue(neighbor);
                }
                
                return nbq;
              }               
            
            }
         }
       return nbq;
     }

     public String toString()
     {
       StringBuilder s = new StringBuilder();
       s.append(n + "\n");
       for (int i = 0; i < n; i++) 
       {
         for (int j = 0; j < n; j++) 
         {
            s.append(String.format("%2d ", cells[i][j]));
         }
         s.append("\n");
       }
       return s.toString();
     }


     private int indexConvertToCellNo(int row, int col)
     {
       int blockNo;
       blockNo = (n*row + col) + 1;
       return blockNo;
     }

     public static void main(String[] args)
     {
     }
}   