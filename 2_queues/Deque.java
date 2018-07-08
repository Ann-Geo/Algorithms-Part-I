import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item>
{

  private Node first, last;
  private int countOfItems;

  private class Node
  {

   private Item item;
   private Node next;
   private Node prev;
  }

  public Deque()
  {
   first = null;
   countOfItems = 0;
   
   
  }

  public boolean isEmpty()
  {

   return first == null;
  }
  

  public int size()
  {
  
   return countOfItems;
  }

  public void addFirst(Item item) // stack: adds item to first node
  {
   if (item == null)
   {
     throw new NullPointerException("The item is null");
   }
   countOfItems = countOfItems + 1;   

   if (isEmpty()) 
   {
     first = new Node();
     first.item = item;
     first.next = null;
     first.prev = null;
     last = first;
   }
   
   else
   {
    Node temp = new Node();
    temp.item = item;
    temp.prev = null;
    temp.next = first;
    first.prev = temp;
    first = temp;
   }
  }

  public void addLast(Item item) // queue: adds item to last node
  {
   if (item == null)
   {
     
     throw new NullPointerException("The item is null");
   }

   countOfItems = countOfItems + 1; 

   if (isEmpty())
   {
     // System.out.println("empty");
     last = new Node();
     last.item = item;
     last.next = null;
     last.prev = null;
     first = last;
   }

   else
   {
    // System.out.println("not empty");
    Node temp = new Node();
    temp.item = item;
    temp.next = null;
    temp.prev = last;
    last.next = temp;
    last = temp; 
   }
  }


  public Item removeFirst()
  {
   
   if (isEmpty()) 
   {
    // System.out.println("yes"); 
    throw new NoSuchElementException("Stack underflow");
      
   }

   
   if (countOfItems == 1)
   {
      // System.out.println("yes");
      Item item = first.item;
      first = null;
      last = first;
      countOfItems = countOfItems - 1; 
      return item;   
   }

     Item item = first.item;
     // Node temp = first;
     first = first.next;
     first.prev = null;   
     countOfItems = countOfItems - 1; 
     return item;
   
  }

  public Item removeLast()
  {
   if (isEmpty()) 
   {
     throw new NoSuchElementException("Stack underflow");
      
   }

    

  if (countOfItems == 1)
   {
      Item item = last.item;
      last = null;
      first = last;
      countOfItems = countOfItems - 1;
      return item;   
   }

      Item item = last.item;
      // Node temp = last;
      last = last.prev;
      last.next = null;
      countOfItems = countOfItems - 1;
      return item;
   
  }

  public Iterator<Item> iterator()
  {

   return new FrontToendIterator();
  }

  private class FrontToendIterator implements Iterator<Item>
  {

   private Node current = first;
  
   public boolean hasNext()
   {

      return current != null;
   }
    
   public void remove()
   {
    
    throw new UnsupportedOperationException();
   }
   
   public Item next()
   {
    
    if (!hasNext()) 
    {
      throw new NoSuchElementException();
    }
    Item item = current.item;
    current = current.next;
    return item;
   }
 }
   public static void main(String[] args)
   {
    /* Deque<Integer> mydeq = new Deque<Integer>();
     mydeq.addFirst(1);
     mydeq.addFirst(2);
     mydeq.addFirst(3);
     mydeq.addFirst(4);
 
System.out.println("count = " +mydeq.countOfItems);

     mydeq.addLast(5);
     mydeq.addLast(6);
     mydeq.addLast(7);
     mydeq.addLast(8);

System.out.println("count = " +mydeq.countOfItems);
   
     for (Integer s : mydeq)
        System.out.println(s);

     System.out.println("removing items");
     mydeq.removeFirst();
     mydeq.removeFirst();
     mydeq.removeFirst();
     mydeq.removeFirst();
     // mydeq.removeFirst();
     // mydeq.removeFirst();
     // mydeq.removeFirst();
     // mydeq.removeFirst();

System.out.println("count = " +mydeq.countOfItems);

     for (Integer s : mydeq)
        System.out.println(s);


     mydeq.removeLast();
    mydeq.removeLast();
     mydeq.removeLast();
     mydeq.removeLast();
     mydeq.removeLast();
     // mydeq.removeLast();
     
     // Iterator iter = mydeq.iterator();
     // iter.remove();
     // System.out.println(iter.next());
     // System.out.println(iter.next());

System.out.println("count = " +mydeq.countOfItems);

      for (Integer s : mydeq)
        System.out.println(s);
     */
   }
}









