import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000,800);
        StdDraw.setXscale(0,199);
        StdDraw.setYscale(159,0);
        int n=200,m=160;
        int cnt=0;
        double chance=0.35;
        int p1=2, p2=3;
        int[][] arr1 = new int[n][m];
        int[][] arr2 = new int[n][m];
        int[][] tmp = new int[n][n];
        for(int i=1; i<n-1; i++)
            for(int j=1; j<m-1; j++)
            {
                arr1[i][j]=(int)(Math.round( Math.random()-chance));
            }
        while (true)
        {
            for(int i=1; i<n-1; i++)
            {
                for(int j=1; j<m-1; j++)
                {
                    if(tmp[i][j]!=arr1[i][j]) // оптимизация
                    {
                        if(arr1[i][j]==1)
                        {
                            StdDraw.setPenColor(Color.BLACK);
                            StdDraw.filledRectangle(i,j,0.4,0.4);
                        }
                        if(arr1[i][j]==0)
                        {
                            StdDraw.setPenColor(Color.WHITE);
                            StdDraw.filledRectangle(i, j, 0.5, 0.5);
                        }
                    }
                    if(arr1[i-1][j-1]==1)cnt++;
                    if(arr1[i-1][j]==1)cnt++;
                    if(arr1[i-1][j+1]==1)cnt++;
                    if(arr1[i][j-1]==1)cnt++;
                    if(arr1[i][j+1]==1)cnt++;
                    if(arr1[i+1][j-1]==1)cnt++;
                    if(arr1[i+1][j]==1)cnt++;
                    if(arr1[i+1][j+1]==1)cnt++;
                    if(arr1[i][j]==1 && (cnt<p1 || cnt>p2))arr2[i][j]=0;
                    if(arr1[i][j]==1 && (cnt==p1 || cnt==p2))arr2[i][j]=1;
                    if(arr1[i][j]==0 && cnt==p2)arr2[i][j]=1;
                    //if(cnt==3 || (arr1[i][j]==1 && cnt==2))arr2[i][j]=1;
                    //else arr2[i][j]=0;
                    cnt=0;
                }
            }
            for(int i=0;i<n;i++)
                tmp[i]=arr1[i].clone();
            for(int i=0;i<n;i++)
                arr1[i]=arr2[i].clone();
        }
    }
}