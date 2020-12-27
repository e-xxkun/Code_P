package xxkun;

import java.util.ArrayList;
import java.util.List;

public class FinallyTest {

    public static List<Integer> result() {
        List<Integer> res = new ArrayList<>();
        try {
            res.add(1);
            return res;
        } finally {
            res.add(2);
        }
    }

    public static int result2() {
        int res = 100;
        try {
            return res;
        } finally {
            res = 1001;
        }
    }

    public static void main(String[] args) {
        List<Integer> res = result();
//        int res = result2();
        System.out.println("res = " + res);
    }
}
