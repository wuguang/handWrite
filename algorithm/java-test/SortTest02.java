
public class SortTest02 {

    public static void quickSortPrivotLeft(int[] arr,int start,int end){
        if(arr == null || arr.length == 1||end<start){
            return;
        }
        int left = start;
        int pivot = arr[start];
        int right = end;
        while(left<right){
            while(arr[right]>pivot && left<right){
                right--;
            }
            while(arr[left]<=pivot && left<right){
                left++;
            }
            swap(arr,left,right);
        }
        swap(arr,start,left);
        quickSortPrivotLeft(arr,start,left-1);
        quickSortPrivotLeft(arr,left+1,end);
    }


    public static void quickSortPrivotRight(int[] arr,int start,int end){
        if(arr == null || arr.length == 1||end<start){
            return;
        }
        int left = start;
        int pivot = arr[end];
        int right = end;
        while(left<right){
            while(arr[left]<pivot && left<right){
                left++;
            }
            while(arr[right]>=pivot && left<right){
                right--;
            }
            swap(arr,left,right);
        }

        swap(arr,right,end);
        quickSortPrivotRight(arr,start,right-1);
        quickSortPrivotRight(arr,right+1,end);
    }

    public static void quickSortByFlags(int[]arr,int left,int right){
        if(arr == null|| right<left){
            return;
        }
        int [] returnArr = netherlandsFlags(arr,left,right,arr[left]);
        if(returnArr[0] - left >1){
            quickSortByFlags(arr,left,returnArr[0]);
        }
        if(right - returnArr[1]>1){
            quickSortByFlags(arr,returnArr[1],right);
        }
    }

    public static int[]  netherlandsFlags(int arr[],int start,int end,int target){
        if(arr ==null || arr.length==1){
            return arr;
        }

        int left = start-1;
        int right = end + 1;
        int i = start;
        while(i<right){
            if(arr[i]<target){
                if(i!=left){
                    swap(arr,i,left+1);
                }
                left++;
                i++;
            }else if(arr[i]>target){
                swap(arr,i,right-1);
                right--;
            }else{
                i++;
            }
        }
        int [] returnArr = {left,right}; 
        return returnArr;
    }

    //数组交换
    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和，求一个数组的小和
    */

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
        System.out.println("sum=" + sum + "; left " + left + "; mid =" + mid + "; right = " + right);
        return sum;
    }

    /*
    在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有逆序对
    逆序对问题
    */
    public static void testSmallSum(){
        int [] arr = {2,7,1,5,4};
        int sum = smallSum(arr,0,arr.length-1);
        for(int v:arr){
            System.out.println( v + " ");
        }
        System.out.println("sum = " + sum);
    }

    //对数器
    public static void main(String [] args){
        testSmallSum();
        /*
        Random rand = new Random();
        for(int i=0;i<10000;i++){
            int[] arr01 = new int[30];
            int[] arr02 = new int[30];
            int[] arr03 = new int[30];
            int j = 0;
            while(j<30){
                arr01[j] = rand.nextInt(50);
                arr02[j] = rand.nextInt(50);
                arr03[j] = rand.nextInt(50);
                j++;
            }
            quickSortByFlags(arr01,0,arr01.length-1);
            quickSortPrivotLeft(arr02,0,arr02.length-1);
            quickSortPrivotRight(arr03,0,arr03.length-1);
            if(StringUtils.join(arr01) == StringUtils.join(arr02) && StringUtils.join(arr01) == StringUtils.join(arr03)){
                System.out.println("yes i = " + i);
            }else{
                System.out.println(StringUtils.join(arr01) + "---" + StringUtils.join(arr02));
            }
        }
        */
    }

}
