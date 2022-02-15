public class Code02_robwalk {
    // N 个位置[1,N]
    // E 结束位置
    // S 剩余多少部
    // K 当前位置i
    public static int walkWays(int N,int E,int rest, int cur){
        return f(N,E,rest,cur);
    }

    public static int f(int N,int E,int rest, int cur){
        if(rest == 0){
            return cur == E?1:0;
        }

        if(cur == 1){
            //下一步只能去,2位置了
            return f(N,E,rest-1,2);
        }

        if(cur == N){
            return f(N,E,rest-1,N-1);
        }

        return f(N,E,rest-1,cur-1) + f(N,E,rest-1,cur+1);
    }


    public static int walkWays2(int N,int E,int rest, int cur){
        int [][]dp = new int[rest+1][N+1];
        for(int i=0;i<rest+1;i++){
            for(int j=0;j<N+1;j++){
                dp[i][j] = -1;
            }
        }
        return f2(N,E,rest,cur,dp);
    }
    public static int f2(int N,int E,int rest, int cur,int[][] dp){
        if(dp[rest][cur] != -1){
            return dp[rest][cur];
        }
        if(rest == 0){
            dp[rest][cur] = cur == E?1:0;
        }else if(cur == 1){
            dp[rest][cur] = f2(N,E,rest-1,2,dp);
            //下一步只能去,2位置了
        }else if(cur == N){
            dp[rest][cur] = f2(N,E,rest-1,N-1,dp);
        }else{
            dp[rest][cur] = f2(N,E,rest-1,cur-1,dp) + f2(N,E,rest-1,cur+1,dp);
        }

        return dp[rest][cur];
    }

    public static void main(String [] args){
        //int N,int E,int rest, int cur
        int ways = walkWays(5,4,4,2);
        int ways2 = walkWays2(5,4,4,2);
        System.out.println("ways = " + ways);
        System.out.println("ways2 = " + ways2);
    }
}
