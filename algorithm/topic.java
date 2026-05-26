// Cho các kí hiệu lên, xuống, trái, phải 
// hãy check true flase sao cho khi đi theo string mẫu được cho
// thì có đi hết từ đầu đến đích hay không 
/*
    Nhóm tình huống:
    1. Đi lên, xuống, trái, phải
*/

// Đề 2: 

// HƯỚNG GIẢI:
// 1. Khởi tạo vị trí hiện tại bằng vị trí xuất phát start (currRow, currCol).
// 2. Duyệt qua từng ký tự trong chuỗi di chuyển (moves).
// 3. Giải mã ký tự thành hướng di chuyển cụ thể:
//    - Tiếng Anh: U (Lên), D (Xuống), L (Trái), R (Phải)
//    - Tiếng Việt: L (Lên), X (Xuống), T (Trái), P (Phải)
//    - Mũi tên: ↑, ↓, ←, →
// 4. Kiểm tra điều kiện an toàn sau mỗi bước đi:
//    - Tọa độ mới phải nằm trong phạm vi ma trận (0 <= row < maxRows, 0 <= col < maxCols).
//    - Ô tại tọa độ mới không phải là vật cản (grid[row][col] != 1).
//    - Nếu vi phạm bất kỳ điều kiện nào, trả về false ngay lập tức.
// 5. Sau khi đi hết chuỗi di chuyển, kiểm tra xem vị trí cuối cùng có khớp với tọa độ đích hay không.

public class topic {

    public static boolean isValidPath(int[][] grid, int[] start, int[] dest, String moves) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int currRow = start[0];
        int currCol = start[1];

        // Kiểm tra vị trí bắt đầu có hợp lệ không
        if (currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols || grid[currRow][currCol] == 1) {
            return false;
        }

        // Tự động nhận diện hệ ký hiệu di chuyển (EN: U/D/L/R, VN: L/X/T/P, ARROW: ↑/↓/←/→)
        String scheme = detectScheme(moves);

        for (int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);
            int[] offset = getDirectionOffset(move, scheme);
            
            if (offset == null) {
                return false; // Ký tự di chuyển không hợp lệ
            }

            currRow += offset[0];
            currCol += offset[1];

            // Kiểm tra giới hạn biên và vật cản
            if (currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols || grid[currRow][currCol] == 1) {
                return false;
            }
        }

        // Kiểm tra xem đã đến đúng đích chưa
        return currRow == dest[0] && currCol == dest[1];
    }

    private static String detectScheme(String moves) {
        for (char c : moves.toCharArray()) {
            if (c == 'U' || c == 'D' || c == 'R') return "EN";
            if (c == 'X' || c == 'T' || c == 'P') return "VN";
            if (c == '↑' || c == '↓' || c == '←' || c == '→') return "ARROW";
        }
        return "EN"; // Mặc định
    }

    private static int[] getDirectionOffset(char move, String scheme) {
        if ("EN".equals(scheme)) {
            switch (move) {
                case 'U': return new int[]{-1, 0};
                case 'D': return new int[]{1, 0};
                case 'L': return new int[]{0, -1};
                case 'R': return new int[]{0, 1};
            }
        } else if ("VN".equals(scheme)) {
            switch (move) {
                case 'L': return new int[]{-1, 0};
                case 'X': return new int[]{1, 0};
                case 'T': return new int[]{0, -1};
                case 'P': return new int[]{0, 1};
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

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {1, 0, 1},
            {0, 0, 0}
        };

        int[] start = {0, 0};
        int[] dest = {2, 0};

        // Test English: R -> D -> D -> L (Đi vòng qua vật cản)
        System.out.println("Test EN (RDDL): " + isValidPath(grid, start, dest, "RDDL")); // true

        // Test VN: P -> X -> X -> T (Đi vòng qua vật cản)
        System.out.println("Test VN (PXXT): " + isValidPath(grid, start, dest, "PXXT")); // true

        // Test Arrow: → -> ↓ -> ↓ -> ← (Đi vòng qua vật cản)
        System.out.println("Test Arrow (→↓↓←): " + isValidPath(grid, start, dest, "→↓↓←")); // true

        // Test Obstacle: D (Đâm vào vật cản)
        System.out.println("Test Obstacle (D): " + isValidPath(grid, start, dest, "D")); // false

        // Test Out of bounds: L (Vượt biên)
        System.out.println("Test Out of bounds (L): " + isValidPath(grid, start, dest, "L")); // false
    }
}