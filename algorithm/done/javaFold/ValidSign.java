
import java.util.*;

/*
括号有效配对
(单一的括号)
1)任何一个左括号都能找到和其正确配对的右括号 (单一的括号)
2)任何一个右括号都能找到和其正确配对的括左号 (单一的括号)
有效的:()((()))()(())
无效的:(()((
    问题一：怎么判断一个括号字符串有效?
    问题二：如果一个括号字符串无效，返回至少填几个字符让其整体有效(单一括号类型)?
    问题三:怎么判断一个,有不同类型的括号({([(([{}])))])}{})字符串有效?
*/

/*
最长有效字符串
括号有效配对是指:
4)返回一个括号字符串中，最长的括号有效子串的长度

*/

public class ValidSign {
    public static boolean isValid01UseStack(String str){
        if(str == null){
            return false;
        };
        Stack<String> myStack = new Stack<>();
        
        String left = "(";
        String right = ")";
        
        String [] strArr = str.split("");
        for(int i=0;i<strArr.length;i++){
            if(strArr[i].equals(left)){
                myStack.push(left);
            }else if(strArr[i].equals(right)){
                if(myStack.isEmpty()){
                    return false;
                }
                myStack.pop();
            }
        }
        return myStack.isEmpty();
    }

    public static boolean isValid01UseCount(String str){
        if(str == null){
            return false;
        };
        int count = 0;
        String left = "(";
        String right = ")";
        String [] strArr = str.split("");
        for(int i=0;i<strArr.length;i++){
            if(strArr[i].equals(left)){
                count ++;
            }else if(strArr[i].equals(right)){
                count --;
            }
            if(count<0){
                return false;
            }
        }
        return count==0?true:false;
    }


    public static int getmMinNums02(String str){
        if(str == null){
            return 2;
        }
        int count = 0;
        String left = "(";
        String right = ")";
        String[] strArr = str.split("");
        int target = 0;
        for(int i=0;i<strArr.length;i++){
            if(strArr[i].equals(left)){
                count ++;
            }else if(strArr[i].equals(right)){
                count --;
            }
            if(count<0){
                target += Math.abs(count);
                count = 0;
            }
        }
        if(count>0){
            target += count;
        }

        return target;
    }

    //({([(([{
    public static boolean validSign03(String str){
            String [] strArr = str.split("");
            HashMap<String,String> signMap = new HashMap<>();
            signMap.put("(",")");
            signMap.put("[","]");
            signMap.put("{","}");
            Stack<String> myStack = new Stack<String>();
            for(int i=0;i<strArr.length; i++){
                //是左符号
                if(signMap.containsKey(strArr[i])){
                    myStack.push(strArr[i]);
                }else{
                    if(!myStack.isEmpty()){
                        if(!strArr[i].equals(myStack.pop())){
                            return false;
                        }
                    }else{
                        return false;
                    }
                }
            }
            return myStack.isEmpty();
        }
    
    
    /*
    4)返回一个括号字符串中，最长的括号有效子串的长度

    {}   [](){[({{{[](){}
    {{{[]}}}
    ]]]][]

    左括号，0
    有括号，往前找一位，如果有匹配，至少2位，往前再加上前一位的对应的最大子串值

    */
    public static int[] getMaxLenUseLoop04(String str){
        if(str == null){
            return null;
        }
        //转成数组字符串
        String[] strArr = str.split("");
        //HashMap<Integer,Integer> myIndexMap = new HashMap<>();
        int [] indexMaxChildNum = new int[strArr.length];
        HashMap<String,String> typeMap = new HashMap<>();
        typeMap.put("(",")");
        typeMap.put("[","]");
        typeMap.put("{","}");
        //这里获取的最大值
        int maxLen = 0;
        //[({()()})]
        for(int i=0;i<strArr.length;i++){
            //以左括号结束的
            if(typeMap.containsKey(strArr[i])){
                indexMaxChildNum[i] = 0;
            //以有括号结尾
            }else{
                Stack<String> myStack = new Stack<>();
                //以当前位置向前推
                int j = i;
                //满足条件
                while(j>-1){
                    //第一个必然push,不为空
                    if(typeMap.containsValue(strArr[j])){
                        myStack.push(strArr[j]);
                        //以有括号结尾
                    }else if(typeMap.containsKey(strArr[j])){
                        myStack.pop();
                    }
                    if(myStack.isEmpty()){
                        //暂停循环
                        break;
                    }
                    j--;
                }
                if(!myStack.isEmpty()){
                    indexMaxChildNum[i] = 0;
                }else{
                    //第一层
                    indexMaxChildNum[i] = i-j+1;
                    if(j-1>-1){
                        indexMaxChildNum[i] += indexMaxChildNum[j-1];
                    }
                    maxLen = Math.max(maxLen,indexMaxChildNum[i]); 
                }
            }
        }
        return indexMaxChildNum;
    }

    public static int getCurMaxNums(String[] strArr,int i,int[] dp){
        if(dp[i]!= -1){
            return dp[i];
        }
        HashMap<String,String> typeMap = new HashMap<>();
        typeMap.put("(",")");
        typeMap.put("[","]");
        typeMap.put("{","}"); 
        
        if(typeMap.containsKey(strArr[i])){
            // 左括号直接退出
            dp[i] = 0;
            return dp[i];
        }else{
            int j = i;
            Stack<String> myStrStack = new Stack<>();
            //
            while(j>-1){
                //右括号 反推
                //{{[()]}}
                if(typeMap.containsValue(strArr[j])){
                    myStrStack.push(strArr[1]);
                }else{
                    if(!myStrStack.isEmpty()){
                        myStrStack.pop();
                    }else{
                        // 没有匹配
                        dp[i] = 0;
                        break;
                    }
                }
                //{([]])}
                if(myStrStack.isEmpty()){
                    break;
                }else{
                    j--;
                }
            }
            //是成对出现的!
            if(myStrStack.isEmpty()){
                dp[i] = i-j+1;
                if(j-1>-1){
                    dp[i] += dp[j-1];
                }
            }
            return dp[i];
        }
        
    }

    public static int getMaxLenUseDp04(String str){
        if(str == null){
            return 0;
        }
        String[] strArr = str.split("");
        int[] dp = new int[strArr.length+1];
        dp[0] = 0;
        int target = 0;
        for(int i=0;i<strArr.length;i++){
            dp[i] = -1;
        }
        dp[0] = 0;
        for(int i=1;i<strArr.length;i++){
            dp[i] = getCurMaxNums(strArr,i,dp);
            target = Math.max(dp[i],target);
        }
        System.out.println(Arrays.toString(dp));
        return target;
    }

    public static void main(String[] args){
        /*
        String str = "()))))((((";
        Boolean result01 = isValid01UseStack(str);
        Boolean result02 = isValid01UseCount(str);
        System.out.println("result02=" + result02 + "; result01=" + result01);
        int nums = getmMinNums02(str);
        System.out.println("nums=" + nums);
        //{}()[{()}]
        String str02 = "{}[](){[({{{";
        Boolean result03 = validSign03(str02);
        System.out.println("result03 = " + result03);
        */

        String str04 = "{}[{(){[({{{()}]({)))[[[[()({{}})})";
        int[] res = getMaxLenUseLoop04(str04);
        System.out.println( Arrays.toString(res));

        int res02 = getMaxLenUseDp04(str04);
        System.out.println(" res02 = " + res02);
    }

}
