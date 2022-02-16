public class Robot {
    public static void main(String[] args){
        int p = 7;
        int cur = 2;
        int t = 3;
        int rest = 7;
        int result = getRobWays(p,t,cur,rest);
        System.out.println("result = " + result);

        //哪里越界了
        int [][] dp = new int[cur+1][rest+1];
        for(int i=0;i<cur;i++){
            for(int j=0;j<rest;j++){
                dp[i][j] = -1;
            }
        }

        //int result02 = getRobWaysUseDp(p,cur,t,rest);
        System.out.println("result02 = " + ways2(p,cur,t,rest));
        //System.out.println("result02 = " + result02);
    } 

    // p = [1,2,3,4,5,6], 位置数
    // 其实位置 cur = 2
    // 目的地 t = 3 
    // 行走步数 rest = 8
    // ? 几种行走方案
    // 限制 1只能到2
    // 6 只能到5 
    // 其他可以左右移动
    public static int getRobWays(int p,int t,int cur, int rest){
        if(rest == 0){
            return cur == t?1:0;
        }
        //getRobWays
        if(cur == 1 ){
            return getRobWays(p,t,cur+1,rest-1);
        }
        if(cur == p){
            return getRobWays(p,t,cur-1,rest-1);
        }
        return getRobWays(p,t,cur-1,rest-1) + getRobWays(p,t,cur+1,rest-1);
    }

    //缓存
    public static int getRobWaysUseDp(int p,int t,int cur, int rest,int[][]dp){
        //不是初始化的值那么缓存过，可以用
        if(dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        if(rest == 0){
            dp[cur][rest] = cur == t?1:0;
        }else if(cur == 1 ){
            dp[cur][rest] = getRobWaysUseDp(p,t,cur+1,rest-1,dp);
        }else if(cur == p){
            dp[cur][rest] = getRobWaysUseDp(p,t,cur-1,rest-1,dp);
        }else{
            dp[cur][rest] = getRobWaysUseDp(p,t,cur-1,rest-1,dp) + getRobWaysUseDp(p,t,cur+1,rest-1,dp);
        }
        return dp[cur][rest];
    }

    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		// 之前没算过！
		int ans = 0;
		if (rest == 0) {
			ans = cur == aim ? 1 : 0;
		} else if (cur == 1) {
			ans = process2(2, rest - 1, aim, N, dp);
		} else if (cur == N) {
			ans = process2(N - 1, rest - 1, aim, N, dp);
		} else {
			ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
		}
		dp[cur][rest] = ans;
		return ans;

	}

    public static int ways2(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		// dp就是缓存表
		// dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
		// dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
		// N+1 * K+1
		return process2(start, K, aim, N, dp);
	}
}
