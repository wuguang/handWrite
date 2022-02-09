import java.util.*;

class MaxTriaggleNums{
    public static void main(String[] args){
        int [] list = {2,2,3,4,5,6,7,8,9};
        getMaxTriaggleNums(list);
        triangleNumber(list);
    }

    static public int triangleNumber(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0;i < len;i++)
            for(int j = i-1;j >= 0;j--)
                for(int k = j-1;k >= 0;k--)
                    if(nums[i] < nums[j] + nums[k])
                        count++;
        System.out.println("count2 = " + count);
        return count;
    }


    //有待校验
    public static int getMaxTriaggleNums(int[] list){
        if(list.length<3){
            return 0;
        }
        int count = 0;
        //排序 使其成为有序数组
        Arrays.sort(list);
        //满足 a+b>c 即可
        for(int i=list.length-1; i>=2; i--){
            //i 最大值
            //j 次大值
            //k 最小值
            int j = i-1;
            int k = 0;
            while(j>k){
                //满足条件
                if(list[k] + list[j] > list[i]){
                    count += j;
                    System.out.println("j = " + j);
                    j--;
                }else{
                    k++;
                }
            }
        }

        System.out.println("count = " + count);
        return 1;
    }
}