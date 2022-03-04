public class Coin{
    public int coinChange(int [] moneyList,int amount){
        int [] amountList = new int[amount+1];
        int i,j;
        amountList[0] = 0;
        for(i=1;i<=amount;i++){
            // 每次默认拼不出来
            amountList[i] = -1;
            for(j=0; j<moneyList.length; j++){
                if(i >= moneyList[j]){
                    //amountList[i] = 
                    // 比当前数 小的数可以被分解，
                    if(amountList[i-moneyList[j]]!=-1){
                        if(amountList[i]==-1 || amountList[i]>amountList[i-moneyList[j]] + 1){
                            // 更新 [i]点的值!!
                            amountList[i] = amountList[i-moneyList[j]] + 1;
                        }
                    }
                }
            }
        };
        return amountList[i];
    }
}