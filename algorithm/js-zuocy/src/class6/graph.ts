class Graph {
    nodes:Map<string,GraphNode>;
    edges:Set<Edge>;

    //总体描述
    constructor(){
        this.nodes = new Map();
        this.edges = new Set();
    }
}

class Edge{
    weight:number;
    from:string;
    to:string;
    //边的描述
    constructor(weight,from,to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}

interface GraphNodeObj{
    value:string|number;
    in:number;
    out:number;
    nexts:Set<GraphNodeObj>;
    edges:Set<Edge>
}

class GraphNodeClass implements GraphNodeObj{

    value:string|number;
    in:number;
    out:number;
    nexts:Set<GraphNodeObj>;
    edges:Set<Edge>;

    //点的描述
    constructor(value){
        this.value = value;
        this.in = 0;
        this.out = 0;
        //Map
        this.nexts = new Set();
        //Set
        this.edges = new Set();
    }
}

//矩阵 二维数组，转图
function createGraph(matrix){
    let graph = new Graph();
    for(let i=0;i<matrix.length;i++){
        let [from,to,weight] = matrix[i];
        if(!graph.nodes.has(from)){
            graph.nodes.set(from,new GraphNode(from));
        }

        if(!graph.nodes.has(to)){
            graph.nodes.set(to,new GraphNode(to));
        }

        let fromGraphNode = graph.nodes.get(from);
        let toGraphNode = graph.nodes.get(to);
        let newEdge = new Edge(weight,fromGraphNode,toGraphNode);
        fromGraphNode.nexts.add(toGraphNode);
        fromGraphNode.out ++;
        toGraphNode.in ++;
        fromGraphNode.edges.add(newEdge);
        graph.edges.add(newEdge);
    }

    return graph;
}

//传入一个节点
function graphBfs(node:GraphNodeObj){
    if(node === null){
        return;
    }
    let queue:GraphNodeObj[] = [];
    let checkMulSet:Set<GraphNodeObj> = new Set();
    //添加用push,取出用shift,即可模拟stack
    queue.push(node);
    checkMulSet.add(node);
   
    //队列里有 节点
    while(queue.length>0){
        //node类型 是的
        let cur:GraphNodeObj = queue.shift();
        //处理该节点内容
        console.log(`cur = ${cur.value}`);
        for(let nextItem of cur.nexts){
            if(!checkMulSet.has(nextItem)){
                checkMulSet.add(nextItem);
                queue.push(nextItem);
            }
        }
    }
}

function graphDfs(node:GraphNodeObj){
    if(node === null){
        return;
    }
    //追溯遍历深度用,用Array模拟,push(),shift()模拟
    let nodeStack:GraphNodeObj[] = [];
    //判断重复的set
    let nodeSet:Set<GraphNodeObj> = new Set();

    /*
        nodeStack，记录执行深度,nodeSet，去重处理
        while 循环处理的前置条件是，父节点已经处理完毕,现在需要处理父节点的下一个子节点,子节点处理完毕，重复父节点的动作,进入循环.

        如何能保证能进入循环，通过进栈，来控制!!
    */

    //添加一个新节点并执行
    const addProcessNode = (parentNode,node)=>{
        
        if(!nodeSet.has(node)){ 
            if(parentNode){
                nodeStack.push(parentNode);
            }
            nodeStack.push(node);
            nodeSet.add(node);
            console.log(`node.value = ${node.value}`);
            return true;
        }
        return false;
    }

    addProcessNode(null,node);

    while(nodeStack.length>0){
        let cur:GraphNodeObj = nodeStack.pop();
        for(let nextItem of cur.nexts){
            let hasProcessed = addProcessNode(cur,nextItem);
            if(hasProcessed){
                break;
            }
        }
    }



}


function sortedTopology(graph:Graph){
    let inMap:Map<GraphNodeObj,number> = new Map();
    let zeroInQueue:GraphNodeObj[] = [];
    let nodesMap:Map<string,GraphNodeObj> = graph.nodes;
    for(let mapItem of nodesMap){
        //mapItem : [string,GraphNodeObj]
        //是map的每个单条条目
        let node = mapItem[1];
        inMap.set(node,node.in);
        if(node.in === 0){
            zeroInQueue.push(node);
        }
    }
    // 对zeroInQueue 进行遍历
    let result:GraphNodeObj[] = [];
    while(zeroInQueue.length>0){
        let cur:GraphNodeObj = zeroInQueue.shift();
        result.push(cur);
        let nexts = cur.nexts;
        for(let nodeItem of nexts){
            //nodeItem.in --;
            if(inMap.has(nodeItem)){
                //第二次或更多次
                inMap.set(nodeItem,inMap.get(nodeItem)-1);
            }else{
                //第一次添加进入 inMap
                inMap.set(nodeItem,nodeItem.in-1);
            }
            
            //度的数据是在新inMap里维护的！！
            if(inMap.get(nodeItem) === 0){
                zeroInQueue.push(nodeItem);
            }
        }
    }
}


//简版 并查集
class MySets{
    setMap:Map<GraphNodeObj,Set<GraphNodeObj>>;
    constructor(nodes:GraphNodeObj[]){
        nodes.forEach(nodeItem=>{
            let nodeSet:Set<GraphNodeObj> = new Set();
            nodeSet.add(nodeItem);
            this.setMap.set(nodeItem,nodeSet);
        })
    }

    isSameSet(from:GraphNodeObj,to:GraphNodeObj):boolean{
        let fromSet:Set<GraphNodeObj> = this.setMap.get(from);
        let toSet:Set<GraphNodeObj> = this.setMap.get(to);
        return fromSet === toSet;
    }

    union(from:GraphNodeObj,to:GraphNodeObj){
        let fromSet:Set<GraphNodeObj> = this.setMap.get(from);
        let toSet:Set<GraphNodeObj> = this.setMap.get(to);

        for(let toNode of toSet){
            fromSet.add(toNode);
            this.setMap.set(toNode,fromSet);
        }
    }

}

// k 算法

// p 算法

