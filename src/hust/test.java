package hust;

import java.math.BigDecimal;

/**
 * @Author: lizhaofu
 * @See:
 * @Description:
 * @Date: Created in 10:10 2019/3/21
 * @Modified:
 */
public class test {
    public static void main(String[] args) {
        String a="376.7301713";
        String b = "我们";
        double d=Double.parseDouble(a);
        BigDecimal bd=new BigDecimal(a);
//        if (bd > 100000){
//
//        }
        System.out.println(b);
        System.out.println(bd);
    }
}
