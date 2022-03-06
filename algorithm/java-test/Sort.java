public class Sort {
    public static int[] SelectionSort(int[] arr){
        if(arr == null || arr.length==1){
            return arr;
        }
        int minIndex = Integer.MIN_VALUE;
        for(int i=0;i<arr.length; i++){
            minIndex = arr[i];
            for(int j=i+1; j<arr.length; j++){
                //minIndex = Math.min(arr[j],minIndex); 
            }
            arr[i] = minIndex;
        }
        return arr;
    }

    public static void swrap(int a,int b,int[] arr){
        int temp = arr[a]; 
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args){
        int [] arr = {29,13,6,9,12,2,3,7,8};
        arr = SelectionSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(" arr["+ i +"] = " + arr[i]);
        }
    }
}
