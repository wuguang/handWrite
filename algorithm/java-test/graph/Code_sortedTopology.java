package graph;

import java.util.*;

public class Code_sortedTopology {
    public static List<Node> sortedTopology(Graph graph){
        // key 某个节点
        // value :剩余的入度
        HashMap<Node,Integer> inMap = new HashMap<>();
        // 入度为0 的点， 才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();

        for(Node node:graph.nodes.values()){
            //初始化入度个数
            inMap.put(node,node.in);

            //入度为零 进队列
            if(node.in == 0){
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while(!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for(Node next :cur.nexts){
                inMap.put(next,inMap.get(next)-1);
                if(inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
} 
