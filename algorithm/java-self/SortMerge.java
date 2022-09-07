public class SortMerge {
    public static int[] mergeSort(int[] arr,int left, int right){
        if(arr == null || right - left == 0){
            return arr;
        }
        //right 是包含关系，[left,right],闭区间
        int mid =  (right+left-1)/2;
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);
        merge(arr,left,mid,right);
        return arr;
    }

    // 类似合并2个有序链表，使之成为一条有序链表
    public static void merge(int[] arr,int left, int mid,int right){
        int lIndex = left;
        int rIndex = mid+1;
        int[] newArr = new int[right-left+1];
        //newArr.push(1);
        int i =0;
        while(lIndex<=mid && rIndex <=right){
            //可用比较器对比
            if(arr[lIndex]<arr[rIndex]){
                newArr[i++] = arr[lIndex];
                lIndex++;
            }else {
                newArr[i++] = arr[rIndex];
                rIndex++;
            }
        }
        while(lIndex <= mid){
            newArr[i++] = arr[lIndex++];
        }

        while(rIndex <= right){
            newArr[i++] = arr[rIndex++];
        }
        i = 0;
        int startLeft = left;
        while(i<=newArr.length-1){
            arr[startLeft++] = newArr[i++];
        }
    }


    /***
     描述
     在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
     ***/

    public static int getSmallTotal(int[] array) {

        //每个数乘上 右边比它大的数个数


        return 0;
    }

    public static void main(String[] args){
        int [] arr = {13,6,8,9,15,4,21,3,25,6,34,3,67,1,45};
        mergeSort(arr,0,arr.length-1);
        for(int i:arr){
            System.out.print( i + " ");
        }
        System.out.println( " ");
    }


}
