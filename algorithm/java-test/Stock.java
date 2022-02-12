public class Stock{
    public static void getMaxMoney(int[] prices,int k) {
        int [][] dp = null;
        int days = prices.length;

        dp[0][0] = 0;



        //return Math.max(getMaxMoney(days,k),dp[days][1]); 

        /*
        for(int i=1; i<=days; i++){
            for(int action=0; action<2; i++){
                if(action == 0){
                    // 今天保持不变
                    dp[i][action] = dp[i-1][action];
                }else if(action == 1){
                    // 今天有操作
                    k -= 1;
                    if(k>0){
                        dp[i][action] = -prices[i];
                    }
                }
            }
        }
        */
        

        //dp[i][0] = Math.max(dp[i-1][0] + 0, dp[i-1][1] + prices[i]);
        //dp[i][1] = Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);


    }

    public static void buy(){

    }
}
