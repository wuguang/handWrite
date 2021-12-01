let preValue = -1;


function Node(value){
    return{
        value,
        left:null,
        right:null
    }
}

function isBst(node){
    if(node === null){
        return true;
    }
    let {left,right} = node;
    if(left!==null){
        if(left>node){
            return false;
        }
    }
    if(right!==null){
        if(node>right){
            return false;
        }
    }

    let leftIsBst = isBst(left);
    if(!leftIsBst){
        return false;
    }
    let rightIsBst = isBst(right);
    if(!rightIsBst){
        return false;
    }
    return true;
}

function test(){
    let head = new Node(10);
    head.left = new Node(15);
    head.right = new Node(28);
    head.left.left = new Node(14);
    head.left.right = new Node(16);
    head.right.left = new Node(25);
    head.right.right = new Node(31);
    head.right.right.left = new Node(30);

    let result = isBst(head);

    console.log(result);
}

test();