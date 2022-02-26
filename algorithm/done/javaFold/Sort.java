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

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        int[] arr = {3,6,12,67,9,13,56,1,3};
        bubbleSort(arr);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + "  ");
        }
    }
}
