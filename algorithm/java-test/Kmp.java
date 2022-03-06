import java.util.Random;

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

        

        while(i<=srcStrLen - targetStrLen){
            int matchNum = 0;
            int step = 1;
            System.out.println("111-i=" + i + "; step=" + step);
            int j = 0;
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

	public static int[] getNextArray02(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while (pos < next.length) {
			if (ms[pos - 1] == ms[cn]) {
				next[pos++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[pos++] = 0;
			}
		}
		return next;
	}
    public static void testGetNextArray(){
        //定义一个字符串(A-Z，a-z，0-9)即62位；
        String str = "abcdefghijklmnopqrstuvwxyz";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb = new StringBuffer();
        char[] arr01 = new char[50];
        char[] arr02 = new char[50];
        //长度为几就循环几次
        for(int i=0; i<50;i++){
            //产生0-61的数字
            int number = random.nextInt(4);
            //将产生的数字通过length次承载到sb中
            arr01[i] = str.charAt(number);
            arr02[i] = str.charAt(number);
        }

        int[] next01 = getNextArray(arr01);
        int[] next02 = getNextArray02(arr02);

        int index = 0;
        for(int i :next01){
            System.out.print(i + " ");
            if(i != next02[index]){
                System.out.print("arr01["+index+"] = " + arr01[index] + "---" + "arr02["+index+"] = " + arr02[index]);
            }
            index++;
        }
    }

    public static void main(String[] args){
        testGetNextArray();
        /*
        String srcStr = "asdfqwerqwerasdfasdfasfqwwer";
        String targetStr = "fasdfasf";
        int result = kmpIndexOf(srcStr,targetStr);
        System.out.println("result=" + result);
        */
    }
}
