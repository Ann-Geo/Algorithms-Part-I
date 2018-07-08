import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> 
{
  private Item[] a; 
  private int countOfItems;

  public RandomizedQueue()
  {

   a = (Item[]) new Object[2];
   countOfItems = 0;
  }

  public boolean isEmpty()
  {

   return countOfItems == 0;
  }

  public int size()
  {
    return countOfItems;
  }

  public void enqueue(Item item)
  {
   if (item == null)
   {
     throw new NullPointerException("The item is null");
   }

   if (countOfItems == a.length) 
   {
     resize(2*a.length);
   }
     a[countOfItems++] = item;
         // System.out.println("yes yes"); 
  }

  private void resize(int capacity) 
  {
        assert capacity >= countOfItems;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < countOfItems; i++) {
            temp[i] = a[i];
        }
        a = temp;

   }
  
  public Item dequeue()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("Stack underflow");
    }   
        int index = StdRandom.uniform(countOfItems);
        Item item = a[index];
        a[index] = a[countOfItems - 1];
        a[countOfItems - 1] = null;
        countOfItems = countOfItems - 1;
        
        if (countOfItems > 0 && countOfItems == a.length/4)
        {
          resize(a.length/2);
        }
        return item;
  }

  public Item sample()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("Stack underflow");
    }   
        int index = StdRandom.uniform(countOfItems);
        Item item = a[index];
        return item;
  }


  public Iterator<Item> iterator()
  {

   return new RandomIterator();
  }

  private class RandomIterator implements Iterator<Item>
  {

   private int countofElements = countOfItems;
   private Item[] temp;

   public RandomIterator()
   {
      temp = (Item[]) new Object[countofElements];
      for (int i = 0; i < countofElements; i++)
      {
         temp[i] = a[i];
      }

   }
  
   public boolean hasNext()
   {

      return countofElements != 0;
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
    int randIndex = StdRandom.uniform(countofElements);
    Item randItem = temp[randIndex];
    if (randIndex == countofElements - 1)
    {
      countofElements = countofElements - 1;
      return randItem;
    }
    
    else
    {
      temp[randIndex] = temp[countofElements - 1];
      temp[countOfItems - 1] = randItem;
      countofElements = countofElements - 1;
      return randItem;
    }

   }
  }
   
   public static void main(String[] args)
   { /* 
      RandomizedQueue<Integer> myrq = new RandomizedQueue<Integer> ();
             System.out.println("yes"); 
     myrq.enqueue(1);
     myrq.enqueue(2);
     myrq.enqueue(3);
     myrq.enqueue(4);
     myrq.dequeue();
     myrq.dequeue();
     myrq.sample();
   
     //for (int i = 0; i < myrq.countOfItems; i++)
     //{
       //Integer intg = (Integer)myrq.a[i];
      //System.out.println(intg);
      //}
    Iterator<Integer> myit = myrq.iterator();
    while(myit.hasNext())
    {

     System.out.println(+myit.next());
     }  */
   }

}