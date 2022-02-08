import java.util.*;

public class Code03_MaxHappy {


    
    public static class Employee{
        public int happy;
        List <Employee> nexts = new ArrayList<>();

        public Employee(int happy){
            this.happy = happy;
        }
        public void add(Employee next){
            nexts.add(next);
        }
    }

    public static void main(String [] args){
        Employee boss = new Employee(18);
        Employee b_01 = new Employee(25);
        Employee b_02 = new Employee(23);
        Employee b_03 = new Employee(28);
        Employee b_01_01 = new Employee(45); 
        Employee b_01_02 = new Employee(49); 
        Employee b_01_03 = new Employee(55); 

        Employee b_02_01 = new Employee(57); 
        Employee b_02_02 = new Employee(65); 
        Employee b_02_03 = new Employee(65); 

        Employee b_03_01 = new Employee(53); 
        Employee b_03_02 = new Employee(69); 
        Employee b_03_03 = new Employee(75); 

        boss.add(b_01);
        boss.add(b_02);
        boss.add(b_03);

        b_01.add(b_01_01);
        b_01.add(b_01_02);
        b_01.add(b_01_03);

        b_02.add(b_02_01);
        b_02.add(b_02_02);
        b_02.add(b_02_03);

        b_03.add(b_03_01);
        b_03.add(b_03_02);
        b_03.add(b_03_03);


        int maxHappy = maxHappy(boss);
        System.out.println("maxHappy = " + maxHappy);
    }


    public static int maxHappy(Employee boss){
        Info headInfo = process(boss);
        return Math.max(headInfo.laiMaxHappy,headInfo.buMaxHappy);
    }

    public static class Info{
        public int laiMaxHappy;
        public int buMaxHappy;
        //初始化Info的值
        public Info(int lai,int bu){
            laiMaxHappy = lai;
            buMaxHappy = bu;
        }
    }


    //求一个员工有权限来的情况，happy最大值是多少
    public static Info process(Employee x){
        if(x.nexts.isEmpty()){
            return new Info(x.happy,0);
        }

        int lai = x.happy;
        int bu = 0;
        //该x 的下级
        for(Employee next: x.nexts){
            Info nextInfo = process(next);
            lai += nextInfo.buMaxHappy;
            bu += Math.max(nextInfo.laiMaxHappy,nextInfo.buMaxHappy);
        }

        return new Info(lai,bu);
    }
}
