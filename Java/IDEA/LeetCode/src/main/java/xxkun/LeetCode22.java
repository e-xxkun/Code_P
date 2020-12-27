package xxkun;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {
    @Test
    public static List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        _generate(0,0,n,"",res);
        return res;
    }

    private static void _generate(int left, int right, int n, String parenthesis, List<String> res) {
        if(left==n && right==n){
            res.add(parenthesis);
            return;
        }

        if(left<n)
            _generate(left+1,right,n,parenthesis+"(",res);
        if(right<left)
            _generate(left,right+1,n,parenthesis+")",res);

    }

    public static void main(String[] args) {
        generateParenthesis(5);
    }
}
