给出一个三角形，计算从三角形顶部到底部的最小路径和，每一步都可以移动到下面一行相邻的数字，
例如，给出的三角形如下：
[↵     [2],↵    [3,4],↵   [6,5,7],↵  [4,1,8,3]↵]
最小的从顶部到底部的路径和是2 + 3 + 5 + 1 = 11。
注意：
如果你能只用O（N）的额外的空间来完成这项工作的话，就可以得到附加分，其中N是三角形中的行总数。
import java.util.*;
public class Solution {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int size=triangle.size();
        int[] dp=new int[size];
        for(int i=0;i<size;i++){
            dp[i]=triangle.get(size-1).get(i);
        }
        size--;
        while(size>0){
            for(int i=0;i<size;i++){
                dp[i]=triangle.get(size-1).get(i)+Math.min(dp[i],dp[i+1]);
            }
            size--;
        }
        return dp[0];
    }
}


一个机器人在m×n大小的地图的左上角（起点，下图中的标记“start"的位置）。
机器人每次向下或向右移动。机器人要到达地图的右下角。（终点，下图中的标记“Finish"的位置）。
可以有多少种不同的路径从起点走到终点？

import java.util.*;


public class Solution {
    /**
     * 
     * @param m int整型 
     * @param n int整型 
     * @return int整型
     */
    public int uniquePaths (int m, int n) {
        // write code here
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for(int i=0;i<n;i++){
            dp[0][i]=1;
        }
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}

继续思考题目"Unique Paths":
如果在图中加入了一些障碍，有多少不同的路径？
分别用0和1代表空区域和障碍
import java.util.*;


public class Solution {
    /**
     * 
     * @param obstacleGrid int整型二维数组 
     * @return int整型
     */
    public int uniquePathsWithObstacles (int[][] obstacleGrid) {
        // write code here
        int m=obstacleGrid.length;
        if(m==0) return 0;
        int n=obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1||obstacleGrid[m-1][n-1]==1) return 0;
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==1) break;
            dp[i][0]=1;
        }
        for(int i=0;i<n;i++){
            if(obstacleGrid[0][i]==1) break;
            dp[0][i]=1;
        }
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==0)
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}


给定一个由非负整数填充的m x n的二维数组，现在要从二维数组的左上角走到右下角，请找出路径上的所有数字之和最小的路径。
注意：你每次只能向下或向右移动。

import java.util.*;


public class Solution {
    /**
     * 
     * @param grid int整型二维数组 
     * @return int整型
     */
    public int minPathSum (int[][] grid) {
        // write code here
        int n=grid.length;
        if(n==0) return 0;
        int m=grid[0].length;
        int[][] dp=new int[n][m];
        dp[0][0]=grid[0][0];
        for(int i=1;i<n;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }
        for(int i=1;i<m;i++){
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[n-1][m-1];
    }
}

给出一个字符串s，分割s使得分割出的每一个子串都是回文串
计算将字符串s分割成回文分割结果的最小切割数
例如:给定字符串s="aab",
返回1，因为回文分割结果["aa","b"]是切割一次生成的。
import java.util.*;


public class Solution {
    /**
     * 
     * @param s string字符串 
     * @return int整型
     */
    public int minCut (String s) {
        // write code here
        int n=s.length();
        int[] dp=new int[n];
        if(n<=1) return 0;
        for(int i=1;i<n;i++){
            dp[i]=i;
        }
        for(int j=1;j<n;j++){
            for(int i=0;i<j;i++){
                if(i==0){
                    if(isPalid(s,0,j)) dp[j]=0;
                    else{
                        if(isPalid(s,1,j)) dp[j]=Math.min(dp[j],dp[i]+1);
                    } 
                }else {
                    if(isPalid(s,i+1,j))
                     dp[j]=Math.min(dp[j],dp[i]+1);
                }
            }
        }
        return dp[n-1];
    }
    private boolean isPalid(String s,int left,int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
给定无序整数序列，求连续非空子串最大和，例如{-23 17 -7 11 -2 1 -34}，子串为{17,-7,11}，最大和为21
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String[] str=sc.nextLine().split(" ");
            int n=str.length;
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=Integer.parseInt(str[i]);
            }
            int max=arr[0];
            int sum=0;
            for(int i:arr){
                if(sum<0){
                    sum=i;
                }else{
                    sum+=i;
                }
                if(max<sum){
                    max=Math.max(max,sum);
                }
            }
            System.out.println(max);
        }
    }
}


