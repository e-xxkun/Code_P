package xxkun;

import java.util.Stack;

public class JZO_3 {

    public static int findRepeatNumber(int[] nums) {
        for (int i = 0;i < nums.length;i ++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }

                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
        Stack s = new Stack<>();
        s.empty();
    }
}
