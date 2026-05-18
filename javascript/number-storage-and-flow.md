# CS Fundamentals: Cơ Chế Lưu Trữ Số Và Luồng Điều Khiển (Java vs. JavaScript)

---

## 1. Phân Biệt `break` và `continue` trong Vòng Lặp

Khi một vòng lặp (ví dụ `for` chạy 10 lần) đang diễn ra, `break` và `continue` là 2 nút "can thiệp khẩn cấp" để thay đổi dòng đời của vòng lặp:

### break - Dừng hẳn cuộc chơi

- **Cơ chế:** Lập tức kết thúc **hoàn toàn** vòng lặp hiện tại, thoát ngay ra ngoài để chạy các dòng code phía dưới vòng lặp.
- **Ví dụ:** Tìm người có ID là `X` trong mảng. Khi đã thấy rồi (`swapped === true`), ta `break` ngay lập tức, không cần duyệt nốt phần còn lại của mảng nữa (đỡ tốn tài nguyên CPU).

### continue - Bỏ qua một nhịp (Làm lại ván mới)

- **Cơ chế:** Không thoát khỏi vòng lặp! Nó chỉ lập tức **bỏ qua phần code còn lại của lượt lặp hiện tại** và nhảy phắt lên vòng lặp tiếp theo (tăng biến `i` lên).
- **Ví dụ:** Bạn đang in số từ 1 đến 5, nhưng bạn không thích số 3. Bạn viết `if (i == 3) continue;`. Vòng lặp sẽ in ra `1, 2, 4, 5` (bỏ qua 3).

---

## 2. Tại Sao `int x = (int) 2.9;` Trong Java Lại Trở Thành `2` (Hiện Tượng Cắt Số)?

Hiện tượng `2.9` biến thành `2` chứ không phải `3` (không làm tròn) được gọi là **Integer Truncation (Sự cắt bỏ số nguyên)**.

### Giải thích hiện tượng (Java)

Trong các ngôn ngữ tĩnh như Java:

1.  `2.9` là kiểu **Floating Point** (Số thực dấu phẩy động), máy tính lưu trữ nó bằng cách tách riêng phần nguyên, phần thập phân và mũ.
2.  `int` là kiểu **Integer** (Số nguyên), máy tính quy định bộ nhớ của nó **chỉ được chứa chuỗi bit thể hiện số nguyên**, tuyệt đối không có chỗ cho bất kỳ số lẻ nào đằng sau dấu chấm.
3.  Khi bạn ép kiểu `(int) 2.9`, Java **không thực hiện thuật toán làm tròn (`Math.round`)**, mà nó sẽ thô bạo **chặt đứt toàn bộ phần đuôi thập phân** (`.9`) đi để tống khứ phần nguyên vừa khít vào ô nhớ của `int`.

### Sự khác biệt với JavaScript:

Trong JS, bạn khai báo `let x = 2.9`. Vì JavaScript **không có kiểu dữ liệu nguyên gốc `int`**, biến `x` vẫn sẽ giữ nguyên là `2.9`.

- Nếu muốn JS chặt đuôi giống Java, ta phải dùng hàm: `Math.trunc(2.9)` hoặc `Math.floor(2.9)`.

---

## 3. So Sánh Cơ Chế Lưu Trữ Số: Java vs. JavaScript

Đây là phần quan trọng bậc nhất về kiến trúc máy tính:

### Java: Vũ Trụ "Phân Loại Cứng Nhắc"

Java có hệ thống kiểu dữ liệu số nguyên thủy (Primitives) vô cùng khắt khe để tối ưu bộ nhớ:

| Kiểu       | Kích thước | Cơ chế lưu trữ                      | Khoảng giá trị           |
| :--------- | :--------- | :---------------------------------- | :----------------------- |
| **byte**   | 8-bit      | Số nguyên bù hai (Two's Complement) | -128 đến 127             |
| **short**  | 16-bit     | Số nguyên bù hai                    | -32,768 đến 32,767       |
| **int**    | 32-bit     | Số nguyên bù hai                    | ~ âm 2 tỷ đến dương 2 tỷ |
| **long**   | 64-bit     | Số nguyên bù hai                    | Cực kỳ khổng lồ          |
| **float**  | 32-bit     | Dấu phẩy động chuẩn IEEE 754        | Số thực độ chính xác đơn |
| **double** | 64-bit     | Dấu phẩy động chuẩn IEEE 754        | Số thực độ chính xác kép |

Ưu điểm: Tiết kiệm bộ nhớ tối đa. Cần lưu số tuổi? Dùng `byte` là đủ, không tốn tài nguyên.

---

### JavaScript: Vũ Trụ "Double" Thống Nhất (IEEE 754)

JavaScript được thiết kế để dễ học, nên người tạo ra nó đã đưa ra một quyết định lịch sử: **TẤT CẢ CÁC SỐ ĐỀU LÀ FLOATING POINT 64-BIT**.

- Trong JS, **không hề có kiểu `int` thật sự** (mãi sau này mới có thêm `BigInt` cho số siêu lớn).
- Cho dù bạn viết `let a = 5;` hay `let b = 5.5;`, JavaScript bên dưới Engine V8 đều lưu chúng dưới dạng **Số thực dấu phẩy động độ chính xác kép 64-bit (IEEE 754 Double Precision)**.

#### "Tác dụng phụ" nổi tiếng của cơ chế này trong JS:

Vì mọi số là số thực dấu phẩy động, hệ nhị phân của máy tính không thể biểu diễn chính xác 100% một số phân số thập phân (giống như hệ thập phân không thể viết hết `1/3 = 0.333333...`).

Dẫn đến phép toán huyền thoại:

```javascript
console.log(0.1 + 0.2 === 0.3); // -> FALSE!!!
// Vì thực tế 0.1 + 0.2 trong bộ nhớ JS sẽ ra: 0.30000000000000004
```

### Bảng So Sánh Tổng Quan:

| Đặc tính                | Java                                            | JavaScript                                   |
| :---------------------- | :---------------------------------------------- | :------------------------------------------- |
| **Kiểu dữ liệu**        | Phân chia rạch ròi (`int`, `double`, `long`...) | Chỉ có 1 kiểu `Number` chung duy nhất        |
| **Ép kiểu `int = 2.9`** | Tự động chặt đuôi mất `.9` -> `2`                | Không đổi, vẫn là `2.9` (phải chặt thủ công) |
| **Tiêu chuẩn**          | Hỗ trợ cả Số nguyên bù hai & IEEE 754           | Hoàn toàn tuân theo IEEE 754 64-bit          |
| **Ứng dụng**            | Cực nhanh, quản lý RAM siêu chặt chẽ            | Linh hoạt, dễ code nhưng dễ dính sai số nhỏ  |

---

Hy vọng bài viết chuyên sâu này giúp bạn có cái nhìn xuyên thấu qua tầng phần cứng máy tính! Nếu có bất kỳ phần nào còn mơ hồ, Nghĩa cứ hỏi tôi giải đáp tiếp nhé!
