/**
 * Bình phương của một mảng đã sắp xếp (Squares of a Sorted Array) sử dụng kỹ thuật hai con trỏ.
 * 
 * Ưu điểm:
 * - Tối ưu thời gian: O(N) bằng cách duyệt từ hai đầu của mảng, nhanh hơn nhiều so với việc 
 *   bình phương từng phần tử rồi sắp xếp lại (tốn O(N log N)).
 * - Tận dụng mảng đã được sắp xếp sẵn (các giá trị có giá trị tuyệt đối lớn nhất sẽ nằm ở 
 *   hai đầu mảng).
 * 
 * Nhược điểm:
 * - Cần sử dụng thêm mảng phụ để lưu kết quả (không gian O(N)).
 * 
 * Độ phức tạp:
 * - Thời gian: O(N) - duyệt qua mỗi phần tử của mảng một lần.
 * - Không gian: O(N) - không gian bổ sung để lưu trữ mảng kết quả.
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] sortedSquare = new int[n];
        
        int left = 0;
        int right = n - 1;

        for(int i = n - 1; i >= 0; i--){
            if(Math.abs(nums[left]) > Math.abs(nums[right])){
                sortedSquare[i] = nums[left] * nums[left];
                left++;
            }else{
                sortedSquare[i] = nums[right] * nums[right];
                right--;
            }
        }
        return sortedSquare;
    }
}