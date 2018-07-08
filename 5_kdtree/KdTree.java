import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
// import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree
{
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;   

    private Node root;
    private int treeSize;
    
    private static class Node
    {
       private Point2D p;      
       private RectHV rect;    
       private Node left;  
       private Node right;
 
       public Node(Point2D p, RectHV rect)
       {
            this.p = p;
            this.rect = rect;
       }
    }

    public KdTree()
    {
       treeSize = 0;
    }

    public boolean isEmpty()
    {
       return treeSize == 0;
    }
    
    public int size()
    {
        return treeSize;
    }

    public void insert(Point2D p)
    {
       if (p == null) throw new IllegalArgumentException("null point");
     
       root = insert(root, p, VERTICAL, 0, 0, 1, 1);
          // System.out.println(root.p);
       
    }

    private Node insert(Node node, Point2D p, boolean lineOrient, double xmin, double ymin, double xmax, double  ymax)
    {
       if (node == null)
       {
          this.treeSize++; 
         // System.out.println("yes.null");
          return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
       }

       if (node.p.equals(p))
       {

         // System.out.println("yes.equals");
          return node;
       }

       if (lineOrient == VERTICAL)
       {
          if (p.x() < node.p.x())
          {
         // System.out.println("yes.1st.if");
             node.left = insert(node.left, p, !lineOrient, node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
         // System.out.println("end1");

          }

          else
          {
        // System.out.println("yes.1st.else");
             node.right = insert(node.right, p, !lineOrient, node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
          }
       }

       else
       {
          if (p.y() < node.p.y())
          {
        // System.out.println("yes.2nd.if");
             node.left = insert(node.left, p, !lineOrient, node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
         // System.out.println("end3");
          }

          else
          {
        // System.out.println("yes.2nd.else");
             node.right = insert(node.right, p, !lineOrient, node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
         // System.out.println("end4");
          }
       }
         // System.out.println("return");          
// System.out.println(node.p);
       return node;
    }

    public boolean contains(Point2D p)
    {
       if (p == null) throw new IllegalArgumentException("null point");
     
       return contains(root, p, VERTICAL);
    }
  
    private boolean contains(Node node, Point2D p, boolean lineOrient)
    {
        if (node == null)
        {
            return false;
        }

        if (node.p.equals(p))
        {
// System.out.println("con equals");
            return true;
        }
     
        if (lineOrient == VERTICAL) 
        {
            if (p.x() < node.p.x())
            {
// System.out.println("con 1st if");
               return contains(node.left, p, !lineOrient);
            }
            else
            {
// System.out.println("con 1st else");
               return contains(node.right, p, !lineOrient);
            }
        }
        
        else
        {
            if (p.y() < node.p.y())
            {
// System.out.println("con 2nd if");
               return contains(node.left, p, !lineOrient);
            }
            else
            {
// System.out.println("con 2nd else");
               return contains(node.right, p, !lineOrient);
            }
        }
     }

     public void draw()
     {
// System.out.println("begin");
        draw(root, VERTICAL);
// System.out.println("public draw");
     }

     private void draw(Node node, boolean lineOrient)
     {
        if (lineOrient == VERTICAL)
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
// System.out.println("if");
        }    
        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
// System.out.println("else");
        }

        if (node.left != null)
        {
// System.out.println("left");
            draw(node.left, !lineOrient);

        }
      
        if (node.right != null)
        {
// System.out.println("right");
            draw(node.right, !lineOrient);

        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();
// System.out.println(node.p);
// System.out.println("end");
        
        // what about root square and will it draw all points?
     }

     public Iterable<Point2D> range(RectHV rect)
     {

       ArrayList<Point2D> pointsInRect = new ArrayList<>();

       range(root, rect, pointsInRect);
    System.out.println(pointsInRect);  
 return pointsInRect;
     }

     private void range(Node node, RectHV rect, ArrayList<Point2D> pointsInRect)
     {
        if (node == null)
        {
           return;
        }


        if (!rect.intersects(node.rect))      // enough to find line intersect with rect
        {
           return;
        }

        if (rect.contains(node.p))
        {
           pointsInRect.add(node.p);
        }
      
        range(node.left, rect, pointsInRect);     // need modification as per checklist
        
        range(node.right, rect, pointsInRect);
   
     }

     public Point2D nearest(Point2D p)
     {
        if (p == null) throw new IllegalArgumentException("null point");
        if (isEmpty()) return null;

        // Point2D nearestPoint;
        return nearest(root, p, Double.POSITIVE_INFINITY);
        // return nearestPoint;

     }

     private Point2D nearest(Node node, Point2D p, double distance)
    
     {

        if (node == null) {
            return null;
        }

        if (node.rect.distanceTo(p) >= distance) {
            return null;
        }

        Point2D nearestPoint = null;
        double nearestDistance = distance;
        double d;

        d = p.distanceTo(node.p);
        if (d < nearestDistance) {
            nearestPoint = node.p;
            nearestDistance = d;
        }

        // Choose subtree that is closer to point.

        Node firstNode = node.left;
        Node secondNode = node.right;

        if (firstNode != null && secondNode != null) {
            if (firstNode.rect.distanceTo(p) > secondNode.rect.distanceTo(p)) {
                firstNode = node.right;
                secondNode = node.left;
            }
        }

        Point2D firstNearestPoint = nearest(firstNode, p, nearestDistance);
        if (firstNearestPoint != null) {
            d = p.distanceTo(firstNearestPoint);
            if (d < nearestDistance) {
                nearestPoint = firstNearestPoint;
                nearestDistance = d;
            }
        }

        Point2D secondNearestPoint = nearest(secondNode, p, nearestDistance);
        if (secondNearestPoint != null) {
            d = p.distanceTo(secondNearestPoint);
            if (d < nearestDistance) {
                nearestPoint = secondNearestPoint;
                nearestDistance = d;
            }
        }

        return nearestPoint;
    }


public static void main(String[] args) {
/*  
KdTree kdtree = new KdTree();

        // kdtree.insert(new Point2D(.7, .2));
     // System.out.println("size: " + kdtree.size());
kdtree.insert(new Point2D(.7, .2));
      System.out.println("size: " + kdtree.size());
kdtree.insert(new Point2D(.5, .4));
      System.out.println("size: " + kdtree.size());
  kdtree.insert(new Point2D(.2, .3));
      System.out.println("size: " + kdtree.size());

  kdtree.insert(new Point2D(.4, .7));
      System.out.println("size: " + kdtree.size());

  kdtree.insert(new Point2D(.9, .6));
      System.out.println("size: " + kdtree.size());
  kdtree.insert(new Point2D(.1, .2));
      System.out.println("size: " + kdtree.size());

// kdtree.contains(new Point2D(.7, .2));
kdtree.contains(new Point2D(.5, .4));

kdtree.draw();

kdtree.range(new RectHV(0, 0, 1, 1));
// System.out.println(pointsInRect);
 */
} 
}

