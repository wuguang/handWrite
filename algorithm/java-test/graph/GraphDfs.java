package graph;
import java.util.*;



public class GraphDfs {
    public static void dfs(Node node){
        if(node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        //添加第一个node 
        stack.add(node);
        //防止重复的记录
        set.add(node);
        System.out.println(node.value);
        while(!stack.isEmpty()){
            //弹出栈里节点
            Node cur = stack.pop();
            //遍历该节点的邻居
            // 取一个作为深度的追随
            for(Node next:cur.nexts){
                //如果next 是没有别处理过的节点
                // 则入栈，下次处理
                
                if(!set.contains(next)){
                    //cur 为啥重新入栈
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
                
            }

        }
    }
}
