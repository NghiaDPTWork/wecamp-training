/**
 * Kiểm tra tính hợp lệ của đường đi trên ma trận (Grid Path Validation).
 * 
 * ĐỀ BÀI:
 * Cho các ký hiệu hướng đi (lên, xuống, trái, phải). Kiểm tra xem khi đi từ vị trí bắt đầu
 * theo chuỗi ký hiệu đó, ta có đi đến được vị trí đích an toàn hay không (không ra ngoài biên,
 * không gặp vật cản, và dừng đúng điểm đích ở cuối hành trình).
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Mô phỏng trực tiếp từng bước (Direct Simulation)
 * - Ý tưởng: Dùng các biến chạy lưu tọa độ hiện tại `r` và `c`.
 *            Duyệt qua từng ký tự của chuỗi di chuyển. Với mỗi ký tự, ta tính toán tọa độ tiếp theo.
 *            Kiểm tra ngay lập tức nếu tọa độ mới vượt biên hoặc đâm vào vật cản (ô có giá trị 1).
 *            Nếu không hợp lệ, trả về false ngay (Short-circuit).
 *            Sau cùng, kiểm tra xem tọa độ hiện tại có trùng khớp với tọa độ đích hay không.
 * - Ưu điểm:
 *   + Hiệu năng tối ưu: Thời gian O(N) với N là chiều dài chuỗi di chuyển, bộ nhớ O(1).
 *   + Trả về kết quả sớm nếu phát hiện lỗi trên đường đi.
 * - Nhược điểm:
 *   + Code dài hơn nếu cần hỗ trợ nhiều loại ký hiệu hướng đi khác nhau (U/D/L/R vs L/X/T/P vs mũi tên).
 * 
 * CÁCH 2: Thiết kế Modular, hỗ trợ đa ký hiệu (Flexible Symbol Support)
 * - Ý tưởng: Tách biệt logic kiểm tra tính hợp lệ của ô `isValidCell` và logic giải mã ký hiệu hướng.
 *            Tự động nhận diện bộ ký hiệu (Ví dụ: tiếng Anh [U,D,L,R], tiếng Việt [L,X,T,P] hoặc Mũi tên [↑,↓,←,→])
 *            để tránh xung đột giữa chữ 'L' (Left tiếng Anh vs Lên tiếng Việt).
 * - Ưu điểm:
 *   + Dễ mở rộng và bảo trì (Clean Code): Hỗ trợ nhiều loại dữ liệu ký hiệu đầu vào một cách an toàn.
 *   + Tránh xung đột ký hiệu trùng lặp.
 * - Nhược điểm:
 *   + Tốn thêm một chút chi phí kiểm tra bộ ký hiệu ban đầu (không đáng kể).
 */

public class topic {

    // CÁCH 1: Mô phỏng trực tiếp, sử dụng chuẩn ký hiệu tiếng Anh phổ biến (U, D, L, R)
    public static boolean isValidPathEnglish(int[][] grid, int[] start, int[] dest, String moves) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int currRow = start[0];
        int currCol = start[1];

        // Kiểm tra điểm xuất phát có hợp lệ không
        if (currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols || grid[currRow][currCol] == 1) {
            return false;
        }

        for (int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);
            
            // Cập nhật tọa độ theo hướng đi
            switch (move) {
                case 'U': // Up - Lên
                    currRow--;
                    break;
                case 'D': // Down - Xuống
                    currRow++;
                    break;
                case 'L': // Left - Trái
                    currCol--;
                    break;
                case 'R': // Right - Phải
                    currCol++;
                    break;
                default:
                    return false; // Ký hiệu không hợp lệ
            }

            // Kiểm tra xem vị trí mới có nằm ngoài biên hoặc đâm vào vật cản (1) không
            if (currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols || grid[currRow][currCol] == 1) {
                return false;
            }
        }

        // Kiểm tra xem cuối hành trình có trùng với điểm đích không
        return currRow == dest[0] && currCol == dest[1];
    }

    // CÁCH 2: Tự động nhận diện và hỗ trợ nhiều bộ ký hiệu (EN, VN, Mũi tên)
    public static boolean isValidPathFlexible(int[][] grid, int[] start, int[] dest, String moves) {
        int rows = grid.length;
        int cols = grid[0].length;

        int currRow = start[0];
        int currCol = start[1];

        if (!isSafe(grid, currRow, currCol, rows, cols)) {
            return false;
        }

        // Nhận diện loại ngôn ngữ ký hiệu dựa trên ký tự đầu tiên
        // Tránh xung đột chữ 'L' (Left trong tiếng Anh và Lên trong tiếng Việt)
        String scheme = detectScheme(moves);

        for (int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);
            int[] dir = getDirectionOffset(move, scheme);
            
            if (dir == null) {
                return false; // Ký hiệu không hợp lệ
            }

            currRow += dir[0];
            currCol += dir[1];

            if (!isSafe(grid, currRow, currCol, rows, cols)) {
                return false;
            }
        }

        return currRow == dest[0] && currCol == dest[1];
    }

    private static boolean isSafe(int[][] grid, int r, int c, int rows, int cols) {
        return r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 0;
    }

    private static String detectScheme(String moves) {
        for (char c : moves.toCharArray()) {
            if (c == 'U' || c == 'D' || c == 'R') return "EN"; // Chắc chắn là tiếng Anh
            if (c == 'X' || c == 'T' || c == 'P') return "VN"; // Chắc chắn là tiếng Việt (Xuống, Trái, Phải)
            if (c == '↑' || c == '↓' || c == '←' || c == '→') return "ARROW"; // Mũi tên
        }
        // Mặc định nếu chỉ có chữ 'L' (hoặc chuỗi rỗng), ta giả định là EN
        return "EN";
    }

    private static int[] getDirectionOffset(char move, String scheme) {
        if ("EN".equals(scheme)) {
            switch (move) {
                case 'U': return new int[]{-1, 0}; // Lên
                case 'D': return new int[]{1, 0};  // Xuống
                case 'L': return new int[]{0, -1}; // Trái
                case 'R': return new int[]{0, 1};  // Phải
            }
        } else if ("VN".equals(scheme)) {
            switch (move) {
                case 'L': return new int[]{-1, 0}; // Lên
                case 'X': return new int[]{1, 0};  // Xuống
                case 'T': return new int[]{0, -1}; // Trái
                case 'P': return new int[]{0, 1};  // Phải
            }
        } else if ("ARROW".equals(scheme)) {
            switch (move) {
                case '↑': return new int[]{-1, 0};
                case '↓': return new int[]{1, 0};
                case '←': return new int[]{0, -1};
                case '→': return new int[]{0, 1};
            }
        }
        return null;
    }

    // Phương thức main để chạy thử nghiệm các trường hợp kiểm thử (test cases)
    public static void main(String[] args) {
        // Ma trận 3x3: 0 là đi được, 1 là vật cản
        // 0  0  0
        // 1  0  1
        // 0  0  0
        int[][] grid = {
            {0, 0, 0},
            {1, 0, 1},
            {0, 0, 0}
        };

        int[] start = {0, 0};
        int[] dest = {2, 1};

        // Test Case 1: Đi vòng qua vật cản bằng ký hiệu tiếng Anh (R, D, D, L) -> Thành công
        // Đường đi: (0,0) -> (0,1) -> (1,1) -> (2,1) -> (2,0)
        int[] destCorrect = {2, 0};
        String movesEN = "RDDL";
        System.out.println("Test Case 1 (EN): " + isValidPathEnglish(grid, start, destCorrect, movesEN)); // true

        // Test Case 2: Đi thẳng đâm vào vật cản (D) -> Thất bại
        String movesObstacle = "D";
        System.out.println("Test Case 2 (Obstacle): " + isValidPathEnglish(grid, start, destCorrect, movesObstacle)); // false

        // Test Case 3: Đi vượt biên grid (L) -> Thất bại
        String movesOutOfBounds = "L";
        System.out.println("Test Case 3 (Bounds): " + isValidPathEnglish(grid, start, destCorrect, movesOutOfBounds)); // false

        // Test Case 4: Đi đúng điểm đích nhưng sai đích mong muốn ban đầu (chỉ đến [2,1] thay vì [2,0]) -> Thất bại
        System.out.println("Test Case 4 (Wrong Dest): " + isValidPathEnglish(grid, start, destCorrect, "RDD")); // false

        // Test Case 5: Sử dụng Cách 2 với tiếng Việt (P, X, X, T) -> Thành công
        String movesVN = "PXXT";
        System.out.println("Test Case 5 (VN Scheme): " + isValidPathFlexible(grid, start, destCorrect, movesVN)); // true

        // Test Case 6: Sử dụng Cách 2 với Mũi tên (→, ↓, ↓, ←) -> Thành công
        String movesArrow = "→↓↓←";
        System.out.println("Test Case 6 (Arrow Scheme): " + isValidPathFlexible(grid, start, destCorrect, movesArrow)); // true
    }
}