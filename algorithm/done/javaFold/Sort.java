public class Sort {
    //冒泡排序
    public static int[] bubbleSort(int [] arr){
        if(arr.length <=1){
            return arr;
        }
        int len = arr.length;
        //闭区间
        //i索引 [0 --- n-2]
        //j索引 [i+1---n-1]
        for(int i=0;i <=len-2; i++){
            for(int j=i+1;j<=len-1;j++){
                if(arr[i]>arr[j]){
                    swap(arr,i,j);
                }
            }
        }
        return arr;
    }

    //选择排序
    public static int[] selectionSort(int [] arr){
        if(arr==null || arr.length<=1){
            return arr;
        }
        int len = arr.length;
        int minIndex = -1;
        for(int i=0;i<=len-2;i++){
            minIndex = i;
            for(int j=i+1;j<=len-1;j++){
                if(arr[minIndex]>arr[j]){
                    minIndex = j;
                }
            }
            if(i!=minIndex){
                swap(arr, i, minIndex);
            }
        }
        return arr;
    }

    //插入排序
    //任务前面是排好序的，找到合适的地方，插入进去
    public static int [] insertionSort(int[] arr){
        if(arr==null || arr.length<=1){
            return arr;
        }
        int len = arr.length;
        int minIndex = -1;
        //[0,n-2]
        for(int i=1;i<=len-1;i++){
            minIndex = i;
            //需要比较的数的索引
            //需要一个可以终止的循环
            for(int j=i-1;j>=0;j--){
                if(arr[j]>arr[minIndex]){
                    //交换最小值和j的索引
                    swap(arr, j, minIndex);
                    minIndex = j;
                }else{
                    //如果有一个满足条件，那么后面的都满足条件，需终止
                    break;
                }
            }
        }
        return arr;
    }



        //插入排序
    //任务前面是排好序的，找到合适的地方，插入进去
    public static int [] insertionSort02(int[] arr){
        if(arr==null || arr.length<=1){
            return arr;
        }
        int len = arr.length;
        int minIndex = -1;
        //[0,n-2]
        for(int i=1;i<=len-1;i++){
            minIndex = i;
            //需要比较的数的索引
            //需要一个可以终止的循环,选while 
            for(int j=i-1;j>=0;j--){
                if(arr[j]>arr[minIndex]){
                    //交换最小值和j的索引
                    swap(arr, j, minIndex);
                    minIndex = j;
                }
            }
        }
        return arr;
    }


    //希尔排序
    //第一个突破O(n^2)的算法
    public static int[] shellSort(int [] arr){
        if(arr==null || arr.length<=1){
            return arr;
        }
        int len = arr.length;
        //i,j,k
        for(int gap=len/2;gap>0;gap/=2){
            //从第二个元素开始
            for(int i=gap;i<=len-1;i+=gap){
                //arr[i]
                int minIndex = i;
                //前面一个数
                int j = i-gap;
                while(j>=0 && arr[j]>arr[minIndex] ){
                    swap(arr,minIndex,j);
                    minIndex = j;
                    j -= gap;
                }
            }
        }
        return arr;
    }

    //归并排序
    public static int[] mergeSort(int[] arr,int left,int right,int[] tempArr){
        if(arr == null || arr.length<=1){
            return arr;
        }
        //向下取整 类似Math.floor
        
        if(left < right){
            int mid = left + ((right - left)>>1);
            mergeSort(arr,left,mid,tempArr);
            mergeSort(arr,mid+1,right,tempArr);
            merge(arr,left,mid,right,tempArr);
        }
        

        return arr;
    }

    //递归的最后一步
    public static void merge(int[] arr,int left,int mid,int right,int[] tempArr){
        int i = left;
        int j = mid+1;
        int t = 0;
 
        //各自从自己的起始点，到各自数组的终点
        while(i<=mid && j <= right){
            if(arr[i]<=arr[j]){
                tempArr[t] = arr[i];
                i++;
            }else{
                tempArr[t] = arr[j];
                j++;
            }
            t++;
        }
        while(i<=mid){
            tempArr[t] = arr[i];
            i++;
            t++;
        }
        while(j<=right){
            tempArr[t] = arr[i];
            j++;
            t++;
        }

        //将tempArr 复制到 arr
        int tempLeft = left;
        t = 0;
        System.out.println("start-------");
        while(tempLeft <= right){
            arr[tempLeft] = tempArr[t];
            System.out.print(", arr[tempLeft]=" + arr[tempLeft]);
            t++;
            tempLeft ++;
            
        }
        System.out.println("");
        System.out.println("end-------");

    }


    //并归排序
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        int[] arr = {12,13,5,67,9,13,56,1,3,12,3,4,1,5,6,3,5,8,142,16,19};
        //bubbleSort(arr);
        int [] tempArr = new int[arr.length];
        mergeSort(arr,0,arr.length-1,tempArr);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + "  ");
        }
    }
}
