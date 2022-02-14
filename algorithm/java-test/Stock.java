/*

买卖一次: 121. Best Time to Buy and Sell Stock
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

2
给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
返回 你能获得的 最大 利润 


输入: prices = [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



## 第3题   leetcode#188
给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 
示例 1：
输入：k = 2, prices = [2,4,1]
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



不限买卖次数: 122. Best Time to Buy and Sell Stock II
买卖两次: 123. Best Time to Buy and Sell Stock III
买卖k次: 188. Best Time to Buy and Sell Stock IV
带有冷却的股票买卖问题: 309. Best Time to Buy and Sell Stock with Cooldown
带有交易费用的股票买卖问题: 714. Best Time to Buy and Sell Stock with Transaction Fee
*/

public class Stock{

    // stock 03   leetcode#188
    // 限制交易次数k次
    public static int maxProfitUseDp03(int[] prices,int k){
        if(prices.length<1){
            return 0;
        }
        if(k<1){
            return 0;
        }
        int days = prices.length;

        // k 交易次数
        int [][] dp = new int[k+1][2];
        for(int i=0; i<days; i++){
            //dp[i][1] = Math.max(); 

            dp[0][0] = 0;
            dp[0][1] = Integer.MIN_VALUE;

            for(int t=1;t<k; t++){
                //持仓
                dp[t][1] = Math.max(dp[t][1], dp[t-1][0]-prices[i]);
                dp[t][0] = Math.max(dp[t][0],dp[t][1] + prices[i]);
            }

        }
        System.out.println("dp[k][0] = " + dp[k-1][0]);
        //交易k 次后最高收入;
        return dp[k-1][0];        
    }

    //限制交易 k次
    public static int maxProfitUseDp0302(int[] prices,int k){
        if(prices.length<1){
            return 0;
        }
        if(k<1){
            return 0;
        }
        // profitStatus
        int days = prices.length;
        int [][][] ps = new int[days+1][k+1][2];

        ps[0][0][0] = 0;
        ps[0][0][1] = Integer.MIN_VALUE;

        ps[0][1][0] = 0;
        ps[0][1][1] = 0;

        // d; 第几天，人类理解的顺序天数
        // t; 交易次数，人类理解次数
        for(int d=1; d<days+1; d++){
            for(int t=1; t<k+1; t++){
                ps[d][t][0] = Math.max(ps[d-1][t][0],ps[d-1][t][1] + prices[d-1]);
                ps[d][t][1] = Math.max(ps[d-1][t][1],ps[d-1][t-1][0] - prices[d-1]);

                System.out.println("ps["+d+"]["+t+"][0] =" + ps[d][t][0]);
                System.out.println("ps["+d+"]["+t+"][1] =" + ps[d][t][1]);
            }
        }

        System.out.println("ps[days][k][0] = " + ps[days][k][0]);
        //在days后，经过k次交易，空仓状态下，我的收入是多少
        return ps[days][k][0];  
        
    }

    public static int maxProfit03(int[] prices,int k) {
        if (prices.length==0)return 0;
        int n = prices.length;
        k = Math.min(k,n/2);
        int[][] buy = new int[n][k+1];
        int[][] sell = new int[n][k+1];
        buy[0][0] = -prices[0];
        for (int i = 1; i <= k; i++) 
          buy[0][i] = sell[0][i] = Integer.MIN_VALUE/2;   //表示在刚开始就已有交易产生，不合逻辑，设置为负数边界
        int max = 0;
        for (int i = 1; i < n; i++) {
          buy[i][0] = Math.max(buy[i-1][0],sell[i-1][0]-prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i-1][j],sell[i-1][j]-prices[i]);
                sell[i][j] = Math.max(sell[i-1][j],buy[i-1][j-1]+prices[i]);
                max = Math.max(max,sell[i][j]);
            }
        }
        System.out.println("max = " + max);
        return max;
      }

    /*
    第三种方法：DP动态规划，第i天只有两种状态，不持有或持有股票，当天不持有股票的状态可能来自昨天卖出或者昨天也不持有，同理，当天持有股票的状态可能来自昨天买入或者昨天也持有中，取最后一天的不持有股票状态就是问题的解

    class Solution:
        def maxProfit(self, prices: List[int]) -> int:
            if not prices:
                return 0
            n = len(prices)
            dp = [[0]*2 for _ in range(n)]
            # dp[i][0]表示第i天不持有股票, dp[i][1]表示第i天持有股票
            dp[0][0], dp[0][1] = 0, - prices[0]
            for i in range(1, n):
                dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
                dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
            return dp[n-1][0]
    */
    //02
    // 不限交易次数
    public static int maxProfitUseDp02(int[] prices){
        if(prices.length<1){
            return 0;
        }
        int days = prices.length;
        int [][]dp = new int [days][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1; i< days; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] - prices[i]);
        }
        System.out.println(" ");
        System.out.print("dp[days-1][0] = " + dp[days-1][0]);
        return dp[days-1][0];
    }

    // 02
    // 不限交易次数
    public static int maxProfitUseTech02(int[] prices){
        if(prices.length<1){
            return 0;
        }
        int days = prices.length;
        int profit = 0;

        for(int i=0; i< days-1; i++){
            if(prices[i]<prices[i+1]){
                profit += (prices[i+1]-prices[i]);
            }
        }
        System.out.println(" ");
        System.out.print("profit = " + profit);
        return profit;
    }




    //一次交易
    public static int maxProfitUseDp01(int[] prices){
        if(prices.length<1){
            return 0;
        }
        int buyPrice = prices[0];
        int profit = 0;

        for(int i=0;i<prices.length;i++){
            //购买价格最低
            buyPrice = Math.min(buyPrice,prices[i]);
            //利润最大
            profit = Math.max(profit,prices[i]-buyPrice);
        }
        System.out.println(" ");
        System.out.print("profit = " + profit);
        return profit;
    }


    // 输入 [2,4,6,7,8,1,2], k = 1
    public static void maxProfitUseLoop01(int[] prices) {
        //int [][] dp = null;
        int d = prices.length;
        int result = 0;
        for(int i=0;i<d;i++){
            for(int j=i+1; j<d; j++){
                if(prices[j]-prices[i]>result){
                    result = prices[j]-prices[i];
                }
            }
        }
        System.out.print("result = " + result);
    }



    public static void main(String[] args){

        int [] prices = {8,3,4,6,7,9,12,3,23,12,3,13,15,56,18,12,5,1,36};

        /*
        maxProfitUseLoop01(prices);
        maxProfitUseDp01(prices);

        maxProfitUseDp02(prices);
        maxProfitUseTech02(prices);
        */

        maxProfitUseDp03(prices,5);
        maxProfit03(prices,5);
        maxProfitUseDp0302(prices,5);
    }
}
