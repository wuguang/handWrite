public class SmallSumMergeSort {
    public static int smallSum(int[] arr,int left,int right){
        if(arr == null || right == left){
            return 0;
        }
        int mid = left + ((right - left)>>1);
        int sum = 0;
        sum += smallSum(arr, left, mid);
        sum += smallSum(arr, mid+1, right);
        return sum + merge(arr,left,mid,right);
    }

    /*
    合并2个有序链表
    */
    public static int merge(int []arr,int left,int mid,int right){
        int i = left;
        int j = mid+1;
        int [] tempArr = new int [right-left+1];
        int t = 0;
        int sum = 0;
        while(i<=mid && j<=right){
            //方式 不稳定发生，不做交换
            if(arr[i]<arr[j]){
                tempArr[t++] = arr[i];
                sum += (right-j+1)*arr[i];
                i++;
            }else{
                tempArr[t++] = arr[j];
                j++;
            }
        }
        while(i<=mid){
            tempArr[t++] = arr[i++];   
        }
        while(j<=right){
            tempArr[t++] = arr[j++];   
        }
        //覆盖原数组
        t = 0;
        while(t<tempArr.length){
            arr[left++] = tempArr[t++];
        }
        return sum;
    }

    public static void mergeSort(int[] arr,int left,int right){
        if(arr==null || left == right){
            return;
        }
        int mid = left + ((right-left)>>1);
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    public static void testSmallSum(){
        int [] arr = {2,7,1,5,4,12,3,15,3,19,2,1};
        int sum = smallSum(arr,0,arr.length-1);
        for(int v:arr){
            System.out.print( v + " ");
        }
        System.out.println("sum = " + sum);
    }

    public static void testMergeSort(){
        int [] arr = {2,7,1,5,4,9,1,13,2,14,2,34,5};
        mergeSort(arr,0,arr.length-1);
        for(int v:arr){
            System.out.print( v + " ");
        }
    }

    //对数器
    public static void main(String [] args){
        testSmallSum();
        testMergeSort();
    }

}
