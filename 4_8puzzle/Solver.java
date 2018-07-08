
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
// import edu.princeton.cs.algs4.StdIn;
import java.util.LinkedList;


public class Solver
{
   
   private int movesToReach;
   private boolean solnStatus;
   private LinkedList<Board> answerList;  




   public Solver(Board initial)
   {
     MinPQ<Node> originalQ = new MinPQ<Node>();
     MinPQ<Node> twinBoardQ = new MinPQ<Node>();

     solnStatus = false;
     movesToReach = 0;


     Node answerNode = new Node(initial, 0, null);
     originalQ.insert(answerNode);

     Node twinNode = new Node(initial.twin(), 0, null);
     twinBoardQ.insert(twinNode);

    
     while (true)
     {
       
              

       answerNode = originalQ.delMin();


       twinNode = twinBoardQ.delMin();



       if (answerNode.boardToSolve.isGoal())
       {
         solnStatus = true;
         movesToReach = answerNode.movesToReach;
         answerList = new LinkedList<>();
         Node currentNode = answerNode;
         while (currentNode != null)
         {
            answerList.addFirst(currentNode.boardToSolve);
            currentNode = currentNode.prevSearchNode;
         }


         break;
       }
       else if (twinNode.boardToSolve.isGoal())
       {
         solnStatus = false;
         movesToReach = -1;
         answerList = null;
         break;
       }
       else
       {
         for (Board b : answerNode.boardToSolve.neighbors())
         {
           
           if (answerNode.prevSearchNode == null || !b.equals(answerNode.prevSearchNode.boardToSolve))                     {             
              originalQ.insert(new Node(b, answerNode.movesToReach+1, answerNode));

           }
         }

         for (Board b : twinNode.boardToSolve.neighbors())
         {
           
           if (twinNode.prevSearchNode == null || !b.equals(twinNode.prevSearchNode.boardToSolve))
           {              
              twinBoardQ.insert(new Node(b, twinNode.movesToReach+1, twinNode));
           }
         }
       }
     }
   }
   
   public boolean isSolvable()
   {
      return solnStatus;
   }
   
   public int moves()
   {
     return movesToReach;
   }

   public Iterable<Board> solution()
   {
     return answerList;
   }

  
   private class Node implements Comparable<Node>
   {
      private final Board boardToSolve;
      private final int movesToReach;
      private final Node prevSearchNode;
      private final int boardPriority;
      
 

      private Node(Board initialBoard, int moves, Node prevNode)
      {
         this.boardToSolve = initialBoard;
         this.movesToReach = moves;
         this.prevSearchNode = prevNode;
         this.boardPriority = this.boardToSolve.manhattan() + this.movesToReach;
      }

      public int compareTo(Node that)
      {

if (this.boardPriority == that.boardPriority)
{

            return this.boardToSolve.manhattan() - that.boardToSolve.manhattan();
}

else
{
            return this.boardPriority - that.boardPriority;
}
      }
     

   }


public static void main(String[] args) 
{

    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
  }

/* 
public static void main(String[] args) 
{
 

System.out.println("Enter n");
int n = StdIn.readInt();

   int[][] blocks = new int[n][n];

// System.out.println("Enter i");
// int i = StdIn.readInt();

// System.out.println("Enter j");
// int j = StdIn.readInt();


System.out.println("Enter nos");

for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            blocks[i][j] = StdIn.readInt();
    
    Board initial = new Board(blocks);

    Solver solver = new Solver(initial);

if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
         for (Board board : solver.solution())
             StdOut.println(board);
  }


  }
 */

}

