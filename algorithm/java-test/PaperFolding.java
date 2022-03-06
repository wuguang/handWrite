

// 一张纸条，保持正面对自己，从上往下折，
// 不断重复，
// n次后，展开，
// 打印纸条从上到下的折痕凹凸性s

public class PaperFolding{
    public static void printAllFolds(int N){
        printProcess(1,N,true);
    }

    // 递归过程，来到了某一个节点
    // i是节点的层数，N一共的层数， down == true 凹  down == false 凸
    public static void printProcess(int i,int N,boolean down){
        if(i>N){
            return;
        }
        // i 是来到某个节点， 该节点在 i 层， 一共N层
        // 收集该层的 值  left self right
        // 左节点
        // i+1 是左节点的层次
        printProcess(i+1,N,true);
        // 自己 的凹凸性 有其父节点决定的
        System.out.println(down?"凹":"凸");
        // 右节点
        printProcess(i+1,N,false);
    }



    public static void main(String[] arg){
        int N = 4;
        printAllFolds(N);
        System.out.println("MyResult = " + printResult(N));
    }


    public static String printResult(int n){
        //从第一层开始
        int i = 1;
        return printOneNode(i,n,true);
    }


    //非递归方式，以后再考虑
    
    public static String nonRecursive(int i,int n){
        /*
        LinkedList<Node> curQueue = new LinkedList<> ();
        LinkedList<Node> nextQueue = new LinkedList<> ();
        while(i<n){

        }
        */

        return "";
    }
    //递归方式
    public static String printOneNode(int i,int n, boolean isDown){
        //退出终止 递归
        if(i>n){
            return "";
        }
        String result = "";
        //左节点 凹 "true"
        result += printOneNode(i+1,n,true);
        result += (isDown?"凹":"凸");
        //System.out.println(isDown?"凹":"凸");
        //右节点 凸 "false"
        result += printOneNode(i+1,n,false);
        return result;

    }
    
}
