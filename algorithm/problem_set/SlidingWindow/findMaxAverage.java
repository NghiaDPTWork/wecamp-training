/**
 * Tìm giá trị trung bình lớn nhất của mảng con có độ dài k (Maximum Average Subarray I)
 * sử dụng kỹ thuật Cửa sổ trượt (Sliding Window).
 * 
 * Ưu điểm:
 * - Tối ưu thời gian: O(N) thay vì O(N * k). Mỗi bước dịch chuyển cửa sổ, ta chỉ cần 
 *   thêm phần tử mới ở bên phải và loại bỏ phần tử cũ ở bên trái trong O(1).
 * - Tiết kiệm không gian: O(1) do chỉ lưu trữ các biến tính toán trực tiếp mà không 
 *   cần mảng phụ.
 * 
 * Nhược điểm:
 * - Phải xử lý riêng biệt bước khởi tạo tổng cho cửa sổ đầu tiên.
 * 
 * Độ phức tạp:
 * - Thời gian: O(N) - duyệt qua mảng đúng một lần.
 * - Không gian: O(1) - bộ nhớ hằng số.
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int windowSum = 0;

        for(int i = 0; i <= k - 1; i++){
            windowSum += nums[i];
        }

        maxSum = windowSum;

        for(int i = k; i <= nums.length - 1; i++){
            windowSum += nums[i] - nums[i - k];

            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;
    }
}