/**
 * Hai con trỏ (Two Pointers) cho bài toán Two Sum II - Mảng đầu vào đã sắp xếp.
 * 
 * Ưu điểm:
 * - Tối ưu thời gian: Chỉ mất O(N) nhờ tận dụng tính chất mảng đã được sắp xếp tăng dần.
 * - Tiết kiệm không gian: O(1) do không cần cấu trúc dữ liệu bổ trợ (như HashMap).
 * 
 * Nhược điểm:
 * - Ràng buộc: Chỉ áp dụng trực tiếp được khi mảng đầu vào ĐÃ SẮP XẾP. Nếu chưa sắp xếp, 
 *   ta sẽ phải tốn O(N log N) để sắp xếp mảng trước, hoặc phải dùng HashMap tốn O(N) bộ nhớ.
 * 
 * Độ phức tạp:
 * - Thời gian: O(N) - duyệt qua mảng tối đa một lần bằng hai con trỏ.
 * - Không gian: O(1) - không sử dụng thêm bộ nhớ ngoài các biến con trỏ.
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left + 1, right + 1}; // Trả về kết quả 1-indexed
            } else if (sum < target) {
                left++; // Cần tổng lớn hơn -> dịch con trỏ trái sang phải
            } else {
                right--; // Cần tổng nhỏ hơn -> dịch con trỏ phải sang trái
            }
        }

        return new int[] {};
    }
}