import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints
{


   private int countofSegsFast = 0;
   private ArrayList<LineSegment> segmentListFast = new ArrayList<>();

   public FastCollinearPoints(Point[] points)
   {
      findRepeatedpoints(points);


      Point[] sPtsFast = Arrays.copyOf(points, points.length);
      Arrays.sort(sPtsFast);

      for (int i = 0; i < sPtsFast.length; i++)
      {
         Arrays.sort(sPtsFast);
         Arrays.sort(sPtsFast, sPtsFast[i].slopeOrder());
         
         

         int originP, compareStart, compareEnd;
 
         for (originP = 0, compareStart = 1, compareEnd = 2; compareEnd < sPtsFast.length; compareEnd++)
         {

            while (compareEnd < sPtsFast.length && Double.compare(sPtsFast[originP].slopeTo(sPtsFast[compareStart]), sPtsFast[originP].slopeTo(sPtsFast[compareEnd])) == 0)
            {
               compareEnd = compareEnd + 1;
            }
        
            if (compareEnd - compareStart >= 3 && sPtsFast[originP].compareTo(sPtsFast[compareStart]) < 0)
            {

               segmentListFast.add(new LineSegment(sPtsFast[originP], sPtsFast[compareEnd-1]));
               countofSegsFast++;
            }
            
            compareStart = compareEnd;
         }
       }
    

      // lineSegsFast = segmentListFast.toArray(new LineSegment[countofSegsFast]);
    }


    public int numberOfSegments()
    {
      return countofSegsFast;
    }

    public LineSegment[] segments()
    {
      return segmentListFast.toArray(new LineSegment[countofSegsFast]);
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