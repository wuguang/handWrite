//大顶堆升序
class BigHeapSort{
    constructor(arr){
        this.arr = arr;
        if(Array.isArray(arr)&& arr.length>2){
            this.end = arr.length-1;
            this.buildBigHeap();
            this.swap(); 
        }  
    }
    //交换提取
    swap(){
        if(this.end<1) return;
        
        let firstVal = this.arr[0];
        let lastVal = this.arr[this.end];

        this.arr[0] = lastVal;
        //最大值放在最后
        this.arr[this.end] = firstVal;
        this.end -= 1;
        //交换之后 继续调整
        //堆的自身的特点
        let leftIndex =  1;
        let rightIndex = leftIndex + 1;
        this.shiftUp(0,leftIndex,rightIndex);

    }

    buildBigHeap(){
        let len = this.arr.length;
        //从第一个非叶子节点开始调整，一直到，第一个节点
        //堆的自身的特点
        let startIndex = len/2 -1;
        for(let i=startIndex; i>=0; i--){
            //堆的自身的特点
            let leftIndex = 2*startIndex + 1;
            let rightIndex = leftIndex + 1;
            this.shiftUp(i,leftIndex,rightIndex);
        }  
    }

    //往上交换
    shiftUp(targetIndex,leftIndex,rightIndex){
        let bigIndex = targetIndex;
        let leftValue = this.arr[leftIndex];
        let rightValue = this.arr[rightIndex];

        if(typeof leftValue === 'number' && leftValue>this.arr[bigIndex]){
            //索引暂缓
            bigIndex = leftIndex;
        }

        if(typeof rightValue === 'number' && rightValue>this.arr[bigIndex]){
            bigIndex = rightIndex;
        }
        //发生了交换
        if(bigIndex !== targetIndex){
            let tempValue = this.arr[targetIndex];
            this.arr[targetIndex] = this.arr[bigIndex];
            this.arr[bigIndex] = tempValue;

            let leftIndex02 = 2*bigIndex + 1;
            let rightIndex02 = leftIndex02 + 1;

            if(rightIndex02<=this.end){
                //检测或调整
                //交换的点继续往下迭代递归，至到调整完毕
                this.shiftUp(bigIndex,leftIndex02,rightIndex02);
            } 
        }

    }
}



test();

function test(){
    let heap = new BigHeapSort([1,5,7,2,9,12,56,2,15,1,3]);
    console.log(JSON.stringify(heap.arr))
}