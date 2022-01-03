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
        if(left.value>node.value){
            return false;
        }
    }
    if(right!==null){
        if(node.value>right.value){
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

function isBst02(head){
    return process(head).isBst;
}

function process(node){
    if(node===null){
        return null;
    }
    
    let max = node.value;
    let min = node.value;
    let {left,right,value:curVal} = node;


    let rightObj = process(right);
    let leftObj = process(left);

    if(rightObj !==null){
        max = Math.max(max,rightObj.max);
        min = Math.min(min,rightObj.min)
    }
    if(leftObj !== null){
        max = Math.max(max,leftObj.max);
        min = Math.min(min,leftObj.min)
    }

    /*
    let isBst = true;

    if(leftObj!==null && (!leftObj.isBst||leftObj.max>=curVal)){
        isBst = false
    }

    if(rightObj!==null && (!rightObj.isBst||rightObj.min<=curVal)){
        isBst = false
    }
    */

    let isBst = false;
    let leftBoolean = leftObj!==null ? (leftObj.isBst &&  leftObj.max < curVal):true;
    let rightBoolean = rightObj !==null?  (rightObj.isBst &&  rightObj.min > curVal):true;
    if(leftBoolean && rightBoolean){
        isBst = true;
    }

    return {
        isBst,
        max,
        min,
    }
}

function isFullTree(head){
    return processFull(head).isFull;
}

function processFull(node){
    if(node===null){
        return{
            height:0,
            num:0,
            isFull:true
        }
    }
    
    let {left,right} = node;
    let leftObj = processFull(left);
    let rightObj = processFull(right);
    let height = Math.max(leftObj.height,rightObj.height) + 1;
    let num = leftObj.num + rightObj.num + 1;
    let isFull = Math.pow(2,height) -1 === num?true:false;
    
    return {
        height,
        num,
        isFull
    }
    
}

function test(){
    let head = new Node(16);
    head.left = new Node(15);
    head.right = new Node(28);
    head.left.left = new Node(14);
    head.left.right = new Node(13);
    head.right.left = new Node(25);
    head.right.right = new Node(31);
    //head.right.right.left = new Node(30);
    let result = isFullTree(head);
    console.log(result);
}

test();