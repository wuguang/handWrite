/*
折纸问题
微软面试原题
一根长纸条，面对着自己，折一次一个凹折痕，折2次凹凸凹，....，折7次，依次打印折痕的凹凸结果

例如，折2次，打印 凹凸凹
*/

function createNode(value){
    return {
        value,
        left:null,
        right:null
    }
}

function printAllFolds(n){

    //n 可视为2叉的层数
    let tree = null;

    //构建树
    const buildLayer = (value,n)=>{
        let node = createNode(value);
        n -= 1;
        if(n>0){
            node.left = buildLayer(0,n);
            node.right = buildLayer(1,n);
        }
        return node;
    }

    if(n>0){
        tree = buildLayer(0,n);
    }
    // 中序遍历  0为凹， 1为凸
    let result = [];

    //隐形栈
    const printNode =(node)=>{
        if(node === null) return;
        printNode(node.left);
        let val = node.value === 0?'凹':'凸';
        result.push(val);
        printNode(node.right);
    }

    printNode(tree);

    console.log(JSON.stringify(result));
}

test();

function test(){

    printAllFolds(4);
}