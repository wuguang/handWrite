function newNode(value){
    return {
        value,
        left:null,
        right:null
    }
}


/*
1、将树以先序方式序列化，
2、在将字符串以先序方式反序列化
*/

function createNode(value){
    return{
        value,
        left:null,
        right:null
    }
}

//先序   序列化
function serialByPre(node,targetStr = ''){
    if(node === null){
        return targetStr += '#_'; 
    }else{ 
        targetStr += `${node.value}_`;
        targetStr = serialByPre(node.left,targetStr);
        targetStr = serialByPre(node.right,targetStr);
        return targetStr;
    }
}


//反序列化
function unserialByPre(targetStr = ''){
    let arr = targetStr.split('_');
    arr.pop();
    let  createTree = ()=>{
        if(arr.length==0) return;
        let firstNode = arr.shift();
        if(firstNode==='#'){
            return null
        }else{
            let curNode = createNode(firstNode);
            curNode.left = createTree(arr);
            curNode.right = createTree(arr);
            return curNode
        }
    }

    let head = createTree();
    return head;
}

test();

function test(){
    let tree = createNode(1);
    tree.left = createNode(2);
    tree.right = createNode(3);
    tree.left.left = createNode(4);
    tree.left.right = createNode(5);
    tree.left.left.left = createNode(6);
    tree.left.left.right = createNode(7);

    let serialStr = serialByPre(tree);

    console.log(`serialStr = ${serialStr}`);

    let myTree = unserialByPre(serialStr);

    console.log(JSON.stringify(myTree));
}


function buildLeft(node,strArr){
    if(strArr.length === 0) return;
    let value = strArr.shift();
    if(value !== '#'){
        node.left = newNode(value);
    }
    if(node.left){
        buildNode(node.left,strArr);
    }
}

function buildRight(node,strArr){
    if(strArr.length === 0) return;
    let value = strArr.shift();
    if(value !== '#'){
        node.right = newNode(value);
    }
    if(node.right){
        buildNode(node.right,strArr);
    }
}

function buildNode(node,strArr){
    buildLeft(node,strArr);
    buildRight(node,strArr);
}

//为空的话用'null'表示
function unserialByPre(strArr,root,curNode){
    let value = strArr.shift();
    if(!root){
        root = newNode(value);
    }

    buildNode(root);
    return root;

}


test();
function test(){
    let root = newNode(1);
    root.left = newNode(2);
    root.right = newNode(3);

    root.left.left = newNode(4);
    root.left.right = newNode(5);
    root.left.right.right = newNode(6);

    root.right.left = newNode(7);
    root.right.right = newNode(8);
    root.right.right.left = newNode(9);
    root.right.right.left.left = newNode(10);

    let str = serialByPre(root);
    console.log(`str = ${str}`);

}