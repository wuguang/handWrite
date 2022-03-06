import java.util.Random;

public class SortTest {
    //包含关系 left,right
    public static void quickSortLeftPivot(int[]arr, int left, int right){
        if(arr == null || left>=right){
            return;
        }

        int pivot = arr[left];
        int i = left;
        int j = right;
        
        //多余的pivot跳过
        while(i<j){
            while(arr[j]>=pivot && i<j){
                j--;
            }
            while(arr[i]<=pivot && i<j){
                i++;
            }
            if(j>i){
                swap(arr,i,j);
            }
        }
        
        swap(arr,left,i);
        quickSortLeftPivot(arr,left,i-1);
        quickSortLeftPivot(arr,i+1,right);

    }


    public static void quickSortRightPivot(int[]arr, int left, int right){
        if(arr == null || left>=right){
            return;
        }

        int pivot = arr[right];
        int i = left;
        int j = right;
        
        //多余的pivot跳过
        while(i<j){
            while(arr[i]<=pivot && i<j){
                i++;
            }
            while(arr[j]>=pivot && i<j){
                j--;
            } 
            if(j>i){
                swap(arr,i,j);
            }
        }
        
        swap(arr,right,j);
        quickSortRightPivot(arr,left,i-2);
        quickSortRightPivot(arr,i,right);

    }

    public static void quickSortNet(int[] arr, int begin, int end){
        if(begin > end)
            return;
        int tmp = arr[begin];
        int i = begin;
        int j = end;
        while(i != j){
            while(arr[j] >= tmp && j > i)
                j--;
            while(arr[i] <= tmp && j > i)
                i++;
            if(j > i){
                //交换
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        //6,3,2,9,2,8,3,6
        //交换起始值
        arr[begin] = arr[i];
        arr[i] = tmp;
        quickSortNet(arr, begin, i-1);
        quickSortNet(arr, i+1, end);
    }

    public static void  netherlandsFlags(int arr[],int target){
        if(arr ==null || arr.length==1){
            return;
        }
        int left = -1;
        int right = arr.length;
        int i = 0;
        while(i<right){
            if(arr[i]<target){
                left++;
                if(i!=left){
                    swap(arr,i,left);
                }
                i++;
            }else if(arr[i]>target){
                swap(arr,i,right-1);
                right--;
            }else{
                i++;
            }
        }
    }

    public static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String [] args){
        int []arr = {4,5,2,5,6,3,5,7,4,2,68,1,2,4,0};
        netherlandsFlags(arr,5);
        for(int val:arr){
            System.out.print(val + " ");
        }
        /*
        Random rand = new Random();
        for(int i=0;i<10000;i++){
            int[] arr01 = new int[30];
            int[] arr02 = new int[30];
            int j = 0;
            while(j<30){
                arr01[j] = rand.nextInt(50);
                arr02[j] = rand.nextInt(50);
                j++;
            }
            quickSortLeftPivot(arr01,0,arr01.length-1);
            quickSortRightPivot(arr02,0,arr02.length-1);
            if(StringUtils.join(arr01) == StringUtils.join(arr02)){
                System.out.println("yes i = " + i);
            }else{
                System.out.println(StringUtils.join(arr01) + "---" + StringUtils.join(arr02));
            }
        }
        */

    }

}
