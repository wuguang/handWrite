
//先序   序列化
function serialByPre(node,targetStr = ''){
    
    if(node === null){
        return targetStr += '#_'; 
    }

    if(node.value){
        targetStr += `${node.value}_`;
        if(node.left){
            serialByPre(node.left,targetStr);
        }else if(node.right){
            serialByPre(node.right,targetStr);
        }
        return targetStr;
    }
}

function unserialByPre(node,targetStr = ''){
    
    if(node === null){
        return targetStr += '#_'; 
    }

    if(node.value){
        targetStr += `${node.value}_`;
        if(node.left){
            serialByPre(node.left,targetStr);
        }else if(node.right){
            serialByPre(node.right,targetStr);
        }
        return targetStr;
    }
}

function test(){

}