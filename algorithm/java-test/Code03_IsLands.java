public class Code03_IsLands {
    // m 是初始矩阵
    public static int countIslands(int[][] m){
        if(m == null || m[0] == null){
            return 0;
        }
        // 行
        int N = m.length;
        // 列
        int M = m[0].length;
        // 要计算岛的个数
        int res = 0;
        //遍历整个矩阵
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                //需要连接的值
                if(m[i][j] == 1){
                    infect(i,j,m,N,M);
                }
            }
        }
        return res;
    }

    public static void infect(int i, int j,int[][] m,int N, int M){
        //不满足条件的，推出函数
        // 只有值为1 才可以相连
        if(i<0 || i>=N || j<0 || j>= M || m[i][j] !=1){
            return;
        }
        //将连在一起的岛感染，
        // 将值修改掉
        m[i][j] = 2;
        infect(i+1,j,m,N,M);
        infect(i-1,j,m,N,M);
        infect(i,j+1,m,N,M);
        infect(i,j-1,m,N,M);
    }
}
