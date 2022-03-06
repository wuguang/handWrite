public class Kmp {
    public static int[] getNextArray(char[] targetArr){
        if(targetArr.length == 1){
            return new int[] {-1};
        }
        int [] nextArr = new int[targetArr.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int i = 2;
        while(i<targetArr.length){
            int  preMax = nextArr[i-1];
            if(targetArr[i-1] == targetArr[preMax]){
                nextArr[i] = preMax+1;
            }else if(targetArr[i-1] == targetArr[0]){
                nextArr[i] = 1;
            }else{
                nextArr[i] = 0;
            }
            i++;
        }

        return nextArr;
    }
    public static int kmpIndexOf(String srcStr,String targetStr){
        if(srcStr == null || targetStr==null || srcStr.length()<targetStr.length()){
            return -1; 
        }

        int i = 0;
        char[] srcStrArr = srcStr.toCharArray();
        char[] targetStrArr = targetStr.toCharArray();

        int targetStrLen = targetStrArr.length;
        int srcStrLen = srcStrArr.length;

        int[] nextArr = getNextArray(targetStrArr);

        int j = 0;

        while(i<=srcStrLen - targetStrLen){
            int matchNum = 0;
            int step = 1;
            System.out.println("111-i=" + i + "; step=" + step);
            while(j<targetStrLen){
                if(targetStrArr[j] == srcStrArr[i]){
                    matchNum++;
                }else{
                    step = j - nextArr[j];
                    j = nextArr[j];
                    break;
                }
            }
            if(matchNum == targetStrLen){
                return j;
            }
            System.out.println("i=" + i + "; step=" + step);
            i += step;
        }
        return -1;
    }
    public static void main(String[] args){
        String srcStr = "asdfqwerqwerasdfasdfasfqwwer";
        String targetStr = "fasdfasf";
        int result = kmpIndexOf(srcStr,targetStr);
        System.out.println("result=" + result);
    }
}
