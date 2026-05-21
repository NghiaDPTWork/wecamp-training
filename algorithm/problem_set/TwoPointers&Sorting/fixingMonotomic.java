/**
 * Kiểm tra mảng đơn điệu (Monotonic Array).
 * 
 * Ưu điểm:
 * - Đạt hiệu năng tối ưu O(N) nhờ duyệt qua mảng đúng một lần.
 * - Cơ chế dừng sớm (short-circuiting): Trả về false ngay khi phát hiện cả hai cờ 
 *   isIncreasing và isDecreasing đều thành false (tức là mảng vừa tăng vừa giảm).
 * - Sử dụng O(1) không gian bộ nhớ.
 * 
 * Nhược điểm:
 * - Tên file hiện tại bị sai chính tả nhẹ ("Monotomic" thay vì "Monotonic").
 * 
 * Độ phức tạp:
 * - Thời gian: O(N) - trong trường hợp xấu nhất phải duyệt toàn bộ mảng.
 * - Không gian: O(1).
 */
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                isIncreasing = false; 
            }
            if (nums[i] < nums[i + 1]) {
                isDecreasing = false; 
            }
            
            if (!isIncreasing && !isDecreasing) {
                return false;
            }
        }

        return true; 
    }
}