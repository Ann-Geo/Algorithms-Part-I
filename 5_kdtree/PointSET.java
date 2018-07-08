// import java.util.TreeSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;

public class PointSET
{
   
    private final SET<Point2D> setofPoints;


    public PointSET()
    {
       setofPoints = new SET<Point2D>();
    }

    public boolean isEmpty()
    {
       return size() == 0;
    }
 
    public int size()
    {
       return setofPoints.size();
    }

    public void insert(Point2D p)
    {
       if (p == null) throw new IllegalArgumentException("null point");
       setofPoints.add(p);
    }

    public boolean contains(Point2D p)
    {
       if (p == null) throw new IllegalArgumentException("null point");
       return setofPoints.contains(p);
    }

    public void draw()
    {

       for (Point2D a : setofPoints)
       a.draw();
    }

    public Iterable<Point2D> range(RectHV rect)
    {
       
       ArrayList<Point2D> range = new ArrayList<>();

       for (Point2D a : setofPoints)
       {
          if (rect.contains(a))
          {
             range.add(a);
          }
       }
      
       return range;
    }
    
    public Point2D nearest(Point2D p)
    {

       if (p == null) throw new IllegalArgumentException("null point");
       if (isEmpty()) return null;

       Point2D nearestPoint = null; 

       for (Point2D a : setofPoints)
       {
          if (nearestPoint == null)
          {
              nearestPoint = a;
          }

          else
          {

             if (a.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p))
             {
                 nearestPoint = a;
             }
          }
       }
       return nearestPoint;
    }

    public static void main(String[] args)
    {
    
    }

}


























      
     































    
