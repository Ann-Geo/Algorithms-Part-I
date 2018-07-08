/* import edu.princeton.cs.algs4.StdIn;
import java.util.*;


public class Permutation 
{
   
   public static void main(String[] args)
   {
             Scanner sc = new Scanner(System.in);
     int k;
     RandomizedQueue<String> perq = new RandomizedQueue<String>();

     System.out.print("Enter k = ");
     k = StdIn.readInt();
    char ch;
    String str;
    String strch = " ";
     System.out.println("Enter strings ");

     while (sc.hasNextLine() && !(strch = sc.nextLine()).equals(""))
        {  // System.out.println("yes");
  
            
            strch = strch.replaceAll(" ",",");
System.out.println(strch);
int count = strch.length();
System.out.println(+count);
for (int j = 0; j < count; j++)
 {System.out.println("yes");

             ch = StdIn.readChar();
System.out.println(ch);
if(ch != ',')
{
             str = StdIn.readString();
System.out.println(str);
  
             perq.enqueue(strch);
             
             
        }}}


     for (int i = 0; i < k; i++)
     {
       
      System.out.println(perq.dequeue());
     } 

  }
}

 */

/* 


import edu.princeton.cs.algs4.StdIn;



public class Permutation 
{
   
   public static void main(String[] args)
   {
     
     int k;
     RandomizedQueue<String> perq = new RandomizedQueue<String>();


     System.out.print("Enter k = ");
     k = StdIn.readInt();
   
    String str;
// boolean a, b;




   
     System.out.println("Enter strings ");

     
String newLine = System.getProperty("line.separator").toString();
System.out.println(newLine);

while (!StdIn.isEmpty())

{ 
str = StdIn.readString();

if (str.equals(newLine)) 
{
break;
 System.out.println("yes");            
             
}
perq.enqueue(str);

}


     for (int i = 0; i < k; i++)
     {
       
      System.out.println(perq.dequeue());
     } 

  }
}




 */

/* 


import edu.princeton.cs.algs4.StdIn;
//import java.io.Console;
import java.io.*;



public class Permutation 
{
   
   public static void main(String[] args)
   {
     
     int k;
     RandomizedQueue<String> perq = new RandomizedQueue<String>();


     System.out.print("Enter k = ");
     k = StdIn.readInt();
   
    String str;

//Console cons;
 System.out.println("Enter strings ");


while (System.in.available() == 0) //((cons = System.console()) != null)

{ 
try{
str = StdIn.readString();

perq.enqueue(str);

}

catch (IOException ex) {}

}




     for (int i = 0; i < k; i++)
     {
       
      System.out.println(perq.dequeue());
     } 

  }
}
 */



import edu.princeton.cs.algs4.StdIn;


public class Permutation 
{

    public static void main(String[] args) 
    {
       
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> perq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty())
        {     
          String str = StdIn.readString();
          perq.enqueue(str);
        }


        for (int i = 0; i < k; i++) 
        {
            System.out.println(perq.dequeue());
        }

    }

}
