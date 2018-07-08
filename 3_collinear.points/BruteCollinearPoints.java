import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints
{


    private ArrayList<LineSegment> segmentList = new ArrayList<>();
    private int countofSegs = 0;

    public BruteCollinearPoints(Point[] points)
    { 

      findRepeatedpoints(points);
      
      Point[] sPs = Arrays.copyOf(points, points.length);
      Arrays.sort(sPs);
      

      for (int p = 0; p < sPs.length-3; p++)
      {
        for (int q = p+1; q < sPs.length-2; q++)
        {
          for (int r = q+1; r < sPs.length-1; r++)
          {
            for (int s = r+1; s < sPs.length; s++)
            {


               if (Double.compare(sPs[p].slopeTo(sPs[q]), sPs[p].slopeTo(sPs[r])) == 0 && Double.compare(sPs[p].slopeTo(sPs[q]), sPs[p].slopeTo(sPs[s])) == 0)
                 {
                    segmentList.add(new LineSegment(sPs[p], sPs[s]));                    
                    countofSegs++;
                 }
            }
          }
        }
      }
      


    }
    public int numberOfSegments()
    {
      return countofSegs;
    }

    public LineSegment[] segments()
    {
      return segmentList.toArray(new LineSegment[countofSegs]);
    }      



    private void findRepeatedpoints(Point[] points)
    {
      for (int i = 0; i < points.length; i++)
      {

        if (i == 0 && points[i] == null)
        {
          throw new NullPointerException("Contains null points");
        }
          
        for (int j = i+1; j < points.length; j++)
        {
           if (points[j] == null)
           {
             throw new NullPointerException("Contains null points");
           }
           if (points[i].compareTo(points[j]) == 0)
           {
             throw new IllegalArgumentException("Repeated entries of points");
           }
        }
      }
    }


    public static void main(String[] args)
    {
    }
}     