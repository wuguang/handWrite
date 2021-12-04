//大顶堆升序
class BigHeapSort{
    constructor(arr){
        this.arr = arr;
        if(Array.isArray(arr)&& arr.length>2){
            this.end = arr.length-1;
            this.buildSmallHeap();
            while(this.end>0){
                this.swap(); 
                //交换之后  继续调整
            }
           
            
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

    buildSmallHeap(){
        let len = this.arr.length;
        //从第一个非叶子节点开始调整，一直到，第一个节点
        //堆的自身的特点
        let startIndex = Math.floor(len/2 -1);
        for(let i=startIndex; i>=0; i--){
            startIndex = i;
            //堆的自身的特点
            let leftIndex = 2*startIndex + 1;
            let rightIndex = leftIndex + 1;
            this.shiftUp(i,leftIndex,rightIndex);
        }  
    }

    //[1,5,7,2,9,12,56,2,15,1,3]
    //往上交换
    shiftUp(targetIndex,leftIndex,rightIndex){
        let bigIndex = targetIndex;
        let leftValue = this.arr[leftIndex];
        let rightValue = this.arr[rightIndex];

        if(leftIndex<=this.end && typeof leftValue === 'number' && leftValue>this.arr[bigIndex]){
            //索引暂缓
            bigIndex = leftIndex;
        }

        if(rightIndex<=this.end && typeof rightValue === 'number' && rightValue>this.arr[bigIndex]){
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


//升序排列 
class SmallHeapSort{
    constructor(arr){
        this.arr = arr;
        if(Array.isArray(arr)&& arr.length>2){
            this.end = arr.length-1;
            this.buildSmallHeap();
            //反复提取
            while(this.end>0){
                this.swap(); 
                //交换之后  继续调整
            }
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
        this.shiftDown(0,leftIndex,rightIndex);
    }

    buildSmallHeap(){
        let len = this.arr.length;
        //从第一个非叶子节点开始调整，一直到，第一个节点
        //堆的自身的特点
        let startIndex = Math.floor(len/2 -1);
        for(let i=startIndex; i>=0; i--){
            startIndex = i;
            //堆的自身的特点
            let leftIndex = 2*startIndex + 1;
            let rightIndex = leftIndex + 1;
            this.shiftDown(i,leftIndex,rightIndex);
        }  
    }

    //[1,5,7,2,9,12,56,2,15,1,3]
    //往上交换
    shiftDown(targetIndex,leftIndex,rightIndex){
        let smallIndex = targetIndex;
        let leftValue = this.arr[leftIndex];
        let rightValue = this.arr[rightIndex];

        if(leftIndex<=this.end && typeof leftValue === 'number' && leftValue<this.arr[smallIndex]){
            //索引暂缓
            smallIndex = leftIndex;
        }

        if(rightIndex<=this.end && typeof rightValue === 'number' && rightValue<this.arr[smallIndex]){
            smallIndex = rightIndex;
        }
        //发生了交换
        if(smallIndex !== targetIndex){
            let tempValue = this.arr[targetIndex];
            this.arr[targetIndex] = this.arr[smallIndex];
            this.arr[smallIndex] = tempValue;

            let leftIndex02 = 2*smallIndex + 1;
            let rightIndex02 = leftIndex02 + 1;

            if(rightIndex02<=this.end){
                //检测或调整
                //交换的点继续往下迭代递归，至到调整完毕
                this.shiftDown(smallIndex,leftIndex02,rightIndex02);
            } 
        }

    }
}



test();

function test(){
    let heap = new SmallHeapSort([1,5,7,2,9,34,14,45,67,9,45,78,23,17,35]);
    console.log(JSON.stringify(heap.arr))
}