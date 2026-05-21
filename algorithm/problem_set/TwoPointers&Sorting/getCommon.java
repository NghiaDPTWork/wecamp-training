/**
 * Hai con trỏ (Two Pointers) cho bài toán Minimum Common Value (Tìm giá trị chung nhỏ nhất).
 * 
 * Ưu điểm:
 * - Đạt hiệu năng tối ưu O(N + M) khi kích thước hai mảng tương đồng nhau (N ≈ M).
 * - Sử dụng O(1) không gian bộ nhớ.
 * 
 * Nhược điểm:
 * - Kém hiệu quả nếu một mảng cực kỳ nhỏ so với mảng còn lại (ví dụ N << M).
 *   Trong trường hợp đó, việc duyệt từng phần tử của mảng nhỏ và dùng Tìm kiếm Nhị phân (Binary Search)
 *   trên mảng lớn sẽ nhanh hơn rất nhiều, đạt O(N log M) so với O(N + M).
 * 
 * Độ phức tạp:
 * - Thời gian: O(N + M) - N, M lần lượt là độ dài của nums1 và nums2.
 * - Không gian: O(1).
 */
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int len1 = nums1.length - 1;
        int len2 = nums2.length - 1;

        int i = 0;
        int j = 0;

        while(i <= len1 && j <= len2){
            if(nums1[i] == nums2[j]){
                return nums1[i];
            }else if(nums1[i] < nums2[j]){
                i++;
            }else{
                j++;
            }
        }
        return -1;
    }
}