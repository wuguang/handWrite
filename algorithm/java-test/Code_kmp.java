public class Code_kmp {

    public static void main(String[] args) {
        String str1 = "abbcabbcddddabbrabbderr";
        String str2 = "abbrabbderr";
        
        int result01 = getIndexOfOrgin(str1,str2);
        int result02 = getIndexOf(str1,str2);
        //"result01 = " + result01 + 
        System.out.println("result01 = " + result01 );
        System.out.println("; result02 = " + result02);
    }

    public static int getIndexOfOrgin(String s,String m){
        if(s == null || m == null || m.length()<1 || s.length()<m.length()){
            return -1;
        }  
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        //i1 是 str1 的下标
        int i1 = 0;

        while( i1<str1.length){
            //i2 是 str2 的下标
            // 的值代表已匹配到的 str2的长度
            int i2 = 0;
            //i2 是匹配的str2数目的个数
            //i1 是从哪开始匹配的
            while(i2<str2.length){
                if(i1+i2 < str1.length && str1[i1+i2] == str2[i2]){
                    i2 ++;
                }else{
                    break;
                }
            }

            if(i2 == str2.length){
                return i1;
            }
            i1++;
        }
        return -1;
    }
    //s是大字符串，被搜索的字符串
    //m是小字符串，是要搜索的字符串
    public static int getIndexOf(String s, String m){
        if(s == null || m == null || m.length()<1 || s.length()<m.length()){
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        
        // i1 ,i2 是明确的字符串下标!!!
        //i1 是 str1 的下标
        int i1 = 0;
        //i2 是 str2 的下标
        // 的值代表已匹配到的 str2的长度
        int i2 = 0;

        int[] next = getNextArray(str2);
        //保证下标不越界
        while(i1<str1.length && i2< str2.length){
            //3种情况
            //1、类似经典搜索，一步一步往后走走
            if(str1[i1] == str2[i2]){
                i1 ++;
                i2 ++;
            }else if(next[i2]== -1){
                i1 ++;
            }else{
                i2 = next[i2];
            }
        }
        // i2，即是str2的索引 又是str2完成匹配的个数
        // 如何匹配的个数 等于自身，则匹配完成，返回 i1- i2
        return i2 == str2.length?i1-i2:-1;
    }

    public static int[] getNextArray(char[] ms){
        /*
        int [] nextList = {1};
        return nextList;
        */
        if(ms.length == 1){
            return new int[] {-1};
        }
        int [] next = new int[ms.length];
        //人为定义的
        next[0] = - 1;
        next[1] = 0 ;
        int i = 2;
        int cn = 0;
        while(i<next.length){
            if(ms[i-1] == ms[cn]){
                next[i++] = ++cn;
            }else if(cn>0){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }
}
