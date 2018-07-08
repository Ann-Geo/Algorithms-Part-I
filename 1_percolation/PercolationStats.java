// import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats
{
    private int gn, tT;
    private double[] percThreshold;
    private double sampleMean, sampleStddev, confLo, confHigh;


    
    public PercolationStats(int n, int trials)
    {
        int ranSite, row, col;    
        int[] openSitesInATrial;
        gn = n;
        tT = trials;
        isInputsPositive(gn, tT);
        percThreshold = new double[tT];
        openSitesInATrial = new int[tT];
        for (int i = 0; i < tT; i++)
        {
            boolean percstatus = false;
            openSitesInATrial[i] = 0;
            Percolation perc = new Percolation(gn);
 
            while (!percstatus)
            {
             ranSite = StdRandom.uniform(gn*gn);
             row = (ranSite/gn) +1;
             col = (ranSite % gn) +1;
             if (!perc.isOpen(row, col))
             {
              perc.open(row, col);
              openSitesInATrial[i] = openSitesInATrial[i] + 1;
              // System.out.println(+openSitesInATrial[i]);
             }
             percstatus = perc.percolates();
            }
             
        percThreshold[i] = (double) openSitesInATrial[i]/(gn*gn);
        // System.out.printf("%f\n", percThreshold[i]);
        }

        mean();
        stddev();
        confidenceLo();
        confidenceHi();
    }
           
    private boolean isInputsPositive(int gno, int sT)
    {
        if (gno <= 0 || sT <= 0)
        {
            throw new IllegalArgumentException("invalid inputs");
        }
        return true;
    }


    public double mean()
    {

        sampleMean = StdStats.mean(percThreshold);
        return sampleMean;
    }


    public double stddev()
    {

        sampleStddev = StdStats.stddev(percThreshold);
        return sampleStddev;
    }
            

    public double confidenceLo()
    {
        confLo = sampleMean - (1.96*sampleStddev)/Math.sqrt(tT);
        return confLo;
    }

    public double confidenceHi()
    {
        confHigh = sampleMean + (1.96*sampleStddev)/Math.sqrt(tT);
        return confHigh;
    }
    
    
    public static void main(String[] args)
    {

        int n, trials;
        System.out.print("Enter n = ");
        n = StdIn.readInt();
        System.out.print("Enter trials = ");
        trials = StdIn.readInt();
        PercolationStats pstat = new PercolationStats(n, trials);
        System.out.printf("sample mean = %f\n", pstat.sampleMean);
        System.out.printf("sample std deviation = %f\n", pstat.sampleStddev);
        System.out.printf("low  endpoint of 95%% confidence interval = %f\n", pstat.confLo);
        System.out.printf("high  endpoint of 95%% confidence interval = %f\n", pstat.confHigh);
    }
}