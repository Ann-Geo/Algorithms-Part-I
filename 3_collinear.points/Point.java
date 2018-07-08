import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;



public class Point implements Comparable<Point>
{

  // private Comparator<Point> SLOPE_ORDER = new slopeOrder();
  private final int x;
  private final int y;

  public Point(int x, int y)
  {

     this.x = x;
     this.y = y;
  }

  public   void draw()   
  {
 
     StdDraw.point(x, y);
  }

  public   void drawTo(Point that)
  {

     StdDraw.line(this.x, this.y, that.x, that.y);
   
  }

  public String toString()
  {

     return "(" + x + ", " + y + ")";
  }

  public int compareTo(Point that)
  {

      if (this.y < that.y) return -1;
      if (this.y == that.y && this.x < that.x) return -1;
      if (this.y > that.y) return +1;
      if (this.y == that.y && this.x > that.x) return +1;
      return 0;
  }

  public double slopeTo(Point that)
  {

      if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
      if (this.y == that.y && this.x != that.x) return +0.0;
      if (this.x == that.x && this.y != that.y) return Double.POSITIVE_INFINITY;
      return (double) (that.y - this.y)/(that.x - this.x);

  }

  public Comparator<Point> slopeOrder()
  {
      return new SlopeComparator();
  }

  private class SlopeComparator implements Comparator<Point>
  {

      public int compare(Point first, Point second)
      {

         if (slopeTo(first) < slopeTo(second)) return -1;
         if (slopeTo(first) > slopeTo(second)) return +1;
         return 0;
      }
  }

  public static void main(String[] args)
  {
  }

}

      


















 
  
  