package xxkun;

import java.util.*;

public class LeetCode15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> numSet = new HashSet<>();
        for (int i = 0;i < nums.length;++ i) {
            numSet.add(nums[i]);
        }
        Iterator<Integer> it1 = numSet.iterator();
        Iterator<Integer> it2 = numSet.iterator();
        while (it1.hasNext()) {
            int t1 = it1.next();
            while (it2.hasNext()) {
                int t2 = it2.next();
                if (numSet.contains(-t1 - t2)) {
                    ArrayList<Integer> item = new ArrayList<>();
                    item.add(t1);
                    item.add(t2);
                    item.add(-t1-t2);
                    res.add(item);
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0;i < nums.length;++ i) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int p = i+1;
            int q = nums.length - 1;
            while (p < q) {
                int s = nums[p] + nums[q] + nums[i];
                if(s < 0) {
                    p ++;
                }else if(s > 0) {
                    q --;
                }else {
                    res.add(Arrays.asList(nums[p], nums[q], nums[i]));
                    while (p < q && nums[p] == nums[++ p]);
                    while (p < q && nums[q] == nums[-- q]);
                }
            }
        }
        System.out.println("res = " + res.toString());
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = threeSum2(nums);
        System.out.println("res = " + res.toString());
    }
}
