
public class RobotWalk {

    // N 个位置[1,N] 必须是1到N,每次robot可以向左或向右移动, 1只能往右移，N只能往左移
    // E 结束位置
    // S 剩余多少部  rest
    // K 当前位置i

    public static int walkWays03(int N, int E, int rest, int cur){
        int [][]dp = new int [rest+1][N+1];
        //dp[0][]
        //初始化 dp 表格
        for(int i=0;i<rest+1;i++){
            for(int j=0;j<N+1;j++){
                dp[i][j] = -1;
            }
        }
        // 真实的计算路径
        return getStatusWays(N,E,rest,cur,dp);
    }

    public static int getStatusWays(int N, int E, int rest, int cur,int[][]dp){
        if(dp[rest][cur] != -1){
            return dp[rest][cur];
        }
        //dp[i][cur] = dp[i-1][cur+1] + dp[i-1][cur-1];
        if(rest==0){
            dp[rest][cur] = cur == E?1:0;
        }else{
            if(cur == 1){
                dp[rest][cur] = getStatusWays(N,E,rest-1,cur+1,dp);
            }else if(cur == N){
                dp[rest][cur] = getStatusWays(N,E,rest-1,cur-1,dp);
            } else{
                dp[rest][cur] = getStatusWays(N,E,rest-1,cur+1,dp) + getStatusWays(N,E,rest-1,cur-1,dp);
            }
        }
        return  dp[rest][cur];
    }


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
        int ways = walkWays(9,4,8,2);
        int ways2 = walkWays2(9,4,8,2);
        int ways3 = walkWays03(9,4,8,2);

        System.out.println("ways = " + ways);
        System.out.println("ways2 = " + ways2);
        System.out.println("ways3 = " + ways3);
    }
}
