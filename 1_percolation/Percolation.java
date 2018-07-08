
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import edu.princeton.cs.algs4.StdIn;

public class Percolation
{

 private int n, virTopsite, virBottomsite, openSitescount = 0;
 private WeightedQuickUnionUF ufdtst, ufbackwash;
 private byte[] site;
 // protected int getopenSitescount(){  return openSitescount; }


 public Percolation(int nN)
 {
  n = nN;
  isNPositive(n);
  ufdtst = new WeightedQuickUnionUF(n*n + 1);
  ufbackwash = new WeightedQuickUnionUF(n*n + 2);
  site = new byte[n*n];
  virTopsite = n*n;
  virBottomsite = n*n + 1;
 }

 public void open(int row, int col)
 {

  int currentsite;
  isInRange(row, col);
  if (isOpen(row, col))
  {
   return;
  }
   currentsite = indexConvertToSiteNo(row, col);
   site[currentsite] = 1;
   openSitescount = openSitescount + 1;

  if (row == 1)
  {
   ufdtst.union(currentsite, virTopsite);
   ufbackwash.union(currentsite, virTopsite);
  }
  if (row == n)
  {
   // ufdtst.union(currentsite, virBottomsite);
   ufbackwash.union(currentsite, virBottomsite);
  }
  if (row > 1 && isOpen(row-1, col))
  {
   ufdtst.union(currentsite, indexConvertToSiteNo(row-1, col));
   ufbackwash.union(currentsite, indexConvertToSiteNo(row-1, col));

  }
  if (row < n && isOpen(row+1, col))
  {
   ufdtst.union(currentsite, indexConvertToSiteNo(row+1, col));
   ufbackwash.union(currentsite, indexConvertToSiteNo(row+1, col));

  }
  if (col > 1 && isOpen(row, col-1))
  {
   ufdtst.union(currentsite, indexConvertToSiteNo(row, col-1));
   ufbackwash.union(currentsite, indexConvertToSiteNo(row, col-1));
  }
  if (col < n && isOpen(row, col+1))
  {
   ufdtst.union(currentsite, indexConvertToSiteNo(row, col+1));
   ufbackwash.union(currentsite, indexConvertToSiteNo(row, col+1));
  }
 }

 private void isInRange(int row, int col)
 {
  
  if (row < 1 || row > n || col < 1 || col > n)
  {
   throw new IndexOutOfBoundsException("row and column out of range");
  }
 }

 private boolean isNPositive(int nSign)
 {
  if (nSign <= 0)
  {
   throw new IllegalArgumentException("grid dimension out of range");
  }
  return true;
 }

 public boolean isOpen(int row, int col)
 {
  isInRange(row, col);
  if (site[indexConvertToSiteNo(row, col)] == 1)
  {
   return true;
  }
  return false;
 }

 
 public boolean isFull(int row, int col)
 {
  int currentsite;
  isInRange(row, col);
  if (!isOpen(row, col))
  {
   return false;
  }
  currentsite = indexConvertToSiteNo(row, col);
  if (ufdtst.connected(currentsite, virTopsite))
  {
   return true;
  }
  return false;
 }

 private int indexConvertToSiteNo(int row, int col)
 {
  int siteno;
  siteno = n*(row-1) + (col-1);
  return siteno;
 }

 public int numberOfOpenSites()
 {
  return openSitescount;
 }


 public boolean percolates()
 {

  if (ufbackwash.connected(virTopsite, virBottomsite))
  {
   return true;
  }
  return false;
 }


 public static void main(String[] args)
 {

  /* int griddim, row, col;
  boolean fullstatus, percstatus = false;
  System.out.print("Enter n = ");
  griddim = StdIn.readInt();
  Percolation perc = new Percolation(griddim);
  while(percstatus == false)
  {
   System.out.println("Enter row and column indices");
   System.out.print("row = ");
   row = StdIn.readInt();
   System.out.print("col = ");
   col = StdIn.readInt();
   perc.open(1, 1);
   System.out.println(+perc.openSitescount);
   perc.open(1, 1);
   System.out.println(+perc.openSitescount);
   perc.open(3, 3);
   System.out.println(+perc.openSitescount);
   perc.open(2, 1);
   System.out.println(+perc.openSitescount);
   fullstatus = perc.isFull(row, col);
   System.out.println(fullstatus);
   percstatus = perc.percolates();
   System.out.println(percstatus);
  } */
 }
}