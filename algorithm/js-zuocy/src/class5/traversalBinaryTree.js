
function Node(value){
    return{
        value,
        left:null,
        right:null
    }
}

function preOrderTraversal(node,result=[]){
    //
    if(node!==null){
        result.push(node.value);
        preOrderTraversal(node.left,result);
        preOrderTraversal(node.right,result);
        console.log(JSON.stringify(result));
    }
}


function middleOrderTraversal(node,result=[]){
    //
    if(node!==null){
        preOrderTraversal(node.left,result);
        result.push(node.value);
        preOrderTraversal(node.right,result);
        console.log(JSON.stringify(result));
    }
}


function postTraversal(node,result=[]){
    //
    if(node!==null){
        preOrderTraversal(node.left,result);
        preOrderTraversal(node.right,result);
        result.push(node.value);
        console.log(JSON.stringify(result));
    }
}

function test(){
    let head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right = new Node(5);
    head.right.left = new Node(6);
    head.right.right = new Node(7);
    head.right.right.left = new Node(8);

    preOrderTraversal(head);
    middleOrderTraversal(head);
    postTraversal(head);
}

test();