/**
 * Kiểm tra phần tử trùng lặp trong khoảng cách k (Contains Duplicate II)
 * sử dụng kỹ thuật Cửa sổ trượt kết hợp HashSet (Sliding Window & HashSet).
 * 
 * Ưu điểm:
 * - Tối ưu thời gian: O(N) nhờ duyệt qua mảng đúng một lần. Thao tác thêm, xóa, kiểm tra 
 *   phần tử trong HashSet chỉ mất trung bình O(1).
 * - Tiết kiệm không gian: O(min(N, k)) do ta chỉ duy trì tối đa k phần tử trong HashSet 
 *   tại bất kỳ thời điểm nào, tránh việc phải lưu trữ toàn bộ mảng.
 * 
 * Nhược điểm:
 * - HashSet có chi phí bộ nhớ phụ (overhead) đáng kể cho mỗi nút và hiệu năng phụ thuộc 
 *   vào hàm băm (hash function).
 * 
 * Độ phức tạp:
 * - Thời gian: O(N) - duyệt qua mảng một lần duy nhất.
 * - Không gian: O(min(N, k)) - HashSet lưu tối đa k phần tử.
 */

import java.util.HashSet;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();

        for(int i = 0; i <= nums.length - 1; i++){
            if(window.contains(nums[i])){
                return true;
            }

            window.add(nums[i]);

            if(window.size() > k){
                window.remove(nums[i - k]);
            }
        }
        return false;
    }
}