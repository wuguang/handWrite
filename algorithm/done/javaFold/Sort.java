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
    public static int[] mergeSort(int[] arr,int left,int right){
        if(arr == null || arr.length<=1){
            return arr;
        }
        //向下取整 类似Math.floor
        
        if(left < right){
            int mid = left + ((right - left)>>1);
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
        

        return arr;
    }

    //递归的最后一步
    public static void merge(int[] arr,int left,int mid,int right){
        int i = left; //[left,mid]
        int j = mid+1; //[mid+1,right]
        int t = 0;
        int [] tempArr = new int[right-left+1];

        //各自从自己的起始点，到各自数组的终点
        while(i<=mid && j <= right){
            /*
            if(arr[i]<=arr[j]){
                tempArr[t++] = arr[i++];
            }else{
                tempArr[t++] = arr[j++];
            }
            */
            tempArr[t++] = arr[i]<=arr[j]? arr[i++]:arr[j++];
        }
        while(i<=mid){
            tempArr[t++] = arr[i++];
        }
        while(j<=right){
            tempArr[t++] = arr[j++];
        }

        //将tempArr 复制到 arr
        int tempLeft = left;
        t = 0;
        //System.out.println("start-------");
        while(tempLeft <= right){
            arr[tempLeft] = tempArr[t];
            t++;
            tempLeft ++;
        }
    }


    //并归排序
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    荷兰国旗问题  flag of the netherlands
    给定一个整数数组，给定一个值K，这个值在原数组中一定存在，要求把数组中小于K的元素放到数组的左边，大于K的元素放到数组的右边，等于K的元素放到数组的中间，最终返回一个整数数组，其中只有两个值，分别是等于K的数组部分的左右两个下标值。
    例如，给定数组：[2, 3, 1, 9, 7, 6, 1, 4, 5]，给定一个值4，那么经过处理原数组可能得一种情况是：[2, 3, 1, 1, 4, 9, 7, 6, 5]，需要注意的是，小于4的部分不需要有序，大于4的部分也不需要有序，
    返回该数组
    等于4部分的左右两个下标，即[4, 4]
    */

    public static int [] netherlandsFlag(int [] arr,int target){
        if(arr==null || arr.length<=1){
            return arr;
        }
        //左右都处在不存在的区域,left代表左右区域的范围
        int left = -1;
        int right = arr.length;
        int cur = 0;

        while(cur < arr.length){
            if(arr[cur]<target){
                left ++;
                if(cur != left){
                    //将当前值放置 left 位置
                    swap(arr,left,cur);
                }
                cur ++;
            }else if(arr[cur]>target){
                right --;
                if(cur != right){
                    swap(arr,right,cur);
                }
                cur ++;
            }
        }
        

        return arr;
    }



    public static void main(String[] args){
        int[] arr = {12,13,5,67,12,9,13,56,1,3,12,3,4,1,5,6,3,5,8,142,16,19};
        //bubbleSort(arr);
        //mergeSort(arr,0,arr.length-1);
        netherlandsFlag(arr,12);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + "  ");
        }
    }
}
