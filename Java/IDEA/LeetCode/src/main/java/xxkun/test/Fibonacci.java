package xxkun.test;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    public static List<Integer> fibonacci(int n) {
        List<Integer> res = new ArrayList<>();
        int f1 = 0;
        int f2 = 1;
        res.add(f1);
        res.add(f2);
        for (int i = 0; i < n - 2; i++) {
            int _f1 = f1;
            f1 = f2;
            f2 = f1 + _f1;
            res.add(f2);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(fibonacci(n).toString());
    }
}