/**
 * //值不重复
 * 输入二叉树的前序遍历和中序遍历结果,重建该二叉树。
 */


 const treeNode = (val,left,right)=>{
    return{
        value:val,
        left:left||null,
        right:right||null
    }
}

function reBuildTree(preArr,midArr){
    if(preArr.length ===0 || midArr.length ===0){
        return null;
    }
    
    let head = preArr[0];
    let midHeadIndex = null;
    midArr.find((item,index)=>{
        if(item===head){
            midHeadIndex  = index;
            return true;
        }
    });
    let tree = treeNode(head);
    let preTreeLeftArr = preArr.slice(1,midHeadIndex+1);
    let midTreeLeftArr = midArr.slice(0,midHeadIndex);
    tree.left = reBuildTree(preTreeLeftArr,midTreeLeftArr);
    let preTreeRightArr = preArr.slice(midHeadIndex+1);
    let midTreeRightArr = midArr.slice(midHeadIndex+1);
    tree.right = reBuildTree(preTreeRightArr,midTreeRightArr);

    return tree;
}



test();
function test(){
    let preArr = [1,2,5,6,3,7,8];
    let midArr = [5,2,6,1,7,3,8]; 

    let tree = reBuildTree(preArr,midArr);

    console.log(JSON.stringify(tree));
}