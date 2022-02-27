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
        if(arr.length<=1){
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
        if(arr.length<=1){
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



    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        int[] arr = {12,13,5,67,9,13,56,1,3,12,3,4,1,5,6,3,5,8};
        //bubbleSort(arr);
        insertionSort(arr);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + "  ");
        }
    }
}
