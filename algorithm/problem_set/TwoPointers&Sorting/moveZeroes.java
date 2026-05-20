class Solution {
    public void moveZeroes(int[] nums) {
        int count = 0; 
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count] = nums[i];
                count++;
            }
        }

        while (count < nums.length) {
            nums[count] = 0;
            count++;
        }
    }
}

// ============================================
class Solution2 {
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                // Tránh swap không cần thiết khi con trỏ trùng nhau
                if (cur != lastNonZeroFoundAt) {
                    int temp = nums[lastNonZeroFoundAt];
                    nums[lastNonZeroFoundAt] = nums[cur];
                    nums[cur] = temp;
                }
                lastNonZeroFoundAt++;
            }
        }
    }
}