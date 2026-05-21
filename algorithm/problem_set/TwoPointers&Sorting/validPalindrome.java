/**
 * Hai con trỏ (Two Pointers) cho bài toán Valid Palindrome.
 * 
 * Ưu điểm:
 * - Tối ưu bộ nhớ cực tốt: Đạt O(1) space do kiểm tra trực tiếp trên chuỗi gốc, không cần
 *   tạo chuỗi con mới (substring) hoặc xóa ký tự không hợp lệ bằng Regex.
 * - Tối ưu thời gian: Chỉ cần một vòng lặp duyệt qua chuỗi một lần.
 * 
 * Nhược điểm:
 * - Cài đặt phức tạp hơn so với cách dùng Regex + so sánh chuỗi đảo ngược (tuy nhiên cách đó tốn O(N) bộ nhớ).
 * - Cần kiểm tra cẩn thận điều kiện biên (`left < right`) trong các vòng lặp phụ để tránh lỗi vượt quá chỉ mục chuỗi.
 * 
 * Độ phức tạp:
 * - Thời gian: O(N) - N là độ dài của chuỗi s.
 * - Không gian: O(1) - không cấp phát thêm bộ nhớ cho chuỗi mới.
 */
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Bỏ qua ký tự không phải chữ cái hoặc chữ số từ bên trái
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Bỏ qua ký tự không phải chữ cái hoặc chữ số từ bên phải
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // So sánh không phân biệt chữ hoa / chữ thường
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

