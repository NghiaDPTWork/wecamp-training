# Tìm hiểu Big O qua các bài toán thực hành

Ở phần luyện tập, mình đã giải quyết 3 bài toán về mảng. Việc kết hợp giữa sắp xếp và tìm kiếm không phải là ngẫu nhiên, mà nó liên quan trực tiếp đến cách tối ưu hiệu năng cho hệ thống. Để đo lường được điều đó, khái niệm Big O ra đời như một thước đo tiêu chuẩn.

---

## Bản chất của Big O là gì?

Big O không được đo bằng thời gian chạy thực tế (giây, mili-giây), vì tốc độ phụ thuộc nhiều vào phần cứng máy tính. Thay vào đó, Big O đo lường sự tăng trưởng của số lượng phép tính khi lượng dữ liệu đầu vào (ký hiệu là N) tăng lên.

Hãy hình dung đơn giản thế này:

- Khi N tăng gấp 10 lần, khối lượng công việc có tăng gấp 10 không? (Đó là tuyến tính O(N)).
- Hay nó lại tăng vọt lên gấp 100 lần? (Đó là bình phương O(N^2)).
  Việc nắm chắc Big O giúp chúng ta biết trước chương trình sẽ hoạt động ổn định hay chậm chạp khi lượng dữ liệu lớn lên.

---

## Bảng tổng hợp các trường hợp thời gian (Time Complexity Cases)

Dựa trên kiến thức đã cài đặt ở phần code, mỗi thuật toán đều có 3 kịch bản chính: Tốt nhất, Trung bình và Tệ nhất.

| Thuật toán        | Trường hợp Tốt nhất (Best Case) | Trường hợp Trung bình (Average Case) | Trường hợp Tệ nhất (Worst Case) |
| :---------------- | :------------------------------ | :----------------------------------- | :------------------------------ |
| **Bubble Sort**   | **O(N)**                        | **O(N^2)**                           | **O(N^2)**                      |
| **Binary Search** | **O(1)**                        | **O(log N)**                         | **O(log N)**                    |

Dưới đây, mình sẽ khai thác chi tiết từng trường hợp để hiểu rõ bản chất hoạt động của chúng:

---

## Khai thác chi tiết theo từng thuật toán

Dựa vào logic mà mình vừa cài đặt trong các bài toán ở thư mục `problem_practice` để thấy rõ bản chất của chúng.

### 1. Phân tích chi tiết Bubble Sort

Trong 2 bài toán Easy, thì mình đã tự tay viết hàm `bubbleSort`. Thuật toán này có 2 vòng lặp for lồng nhau.

#### A. Trường hợp Tốt nhất: O(N)

- **Xảy ra khi nào:** Khi mảng đưa vào đã được sắp xếp đúng thứ tự từ trước (ví dụ: `[1, 2, 3, 4, 5]`).
- **Lý do logic:** Nhờ vào biến cờ `swapped` mà mình đã code. Ở vòng lặp đầu tiên đi qua hết mảng (mất N bước), nếu không có bất kỳ cặp nào bị sai thứ tự để hoán đổi, thuật toán sẽ `break` ngay lập tức và kết thúc.
- **Kết quả:** Cực nhanh, khối lượng công việc tăng tỉ lệ thuận đúng bằng số phần tử N.

#### B. Trường hợp Tệ nhất & Trung bình: O(N^2)

- **Xảy ra khi nào:** Khi mảng hoàn toàn lộn xộn, hoặc tệ nhất là bị sắp xếp ngược chiều hoàn toàn (ví dụ: `[5, 4, 3, 2, 1]`).
- **Lý do logic:** Mọi cặp số đều phải hoán đổi. Vòng lặp bên trong phải chạy đi chạy lại liên tục cho đến khi đẩy được phần tử lớn nhất về cuối. Cứ N phần tử lặp lại N lần tạo thành N x N phép toán.
- **Tác động:** Với mảng lớn, số phép toán sẽ bùng nổ nhanh chóng (ví dụ: 1.000 phần tử mất tới 1.000.000 phép so sánh), dẫn đến chương trình bị treo.

---

### 2. Phân tích chi tiết Binary Search

Trong tất cả 3 bài, mình đều lồng ghép hàm `binarySearch` sau khi đã có một mảng sorted để truy vết dữ liệu cực nhanh.

#### A. Trường hợp Tốt nhất: O(1)

- **Xảy ra khi nào:** Khi giá trị cần tìm (`target`) nằm CHÍNH GIỮA mảng ngay từ lần tính `mid` đầu tiên.
- **Lý do logic:** Thuật toán nhảy vào vị trí `mid`, thực hiện phép so sánh duy nhất `arr[mid] === target` và trả về kết quả ngay.
- **Kết quả:** Hoàn hảo, không phụ thuộc vào độ lớn của mảng. Chỉ tốn đúng 1 bước.

#### B. Trường hợp Tệ nhất & Trung bình: O(log N)

- **Xảy ra khi nào:** Khi phần tử nằm ở rìa mảng (đầu hoặc cuối), hoặc thậm chí không tồn tại trong mảng.
- **Lý do logic:** Thuật toán buộc phải liên tục chặt đôi mảng lại cho đến khi vùng tìm kiếm chỉ còn duy nhất 1 phần tử.
- **Lợi ích thực tiễn:** Sự chia đôi lặp đi lặp lại có sức mạnh cực đại. Dù mảng có 1 triệu hay 1 tỷ phần tử, thì số bước tối đa (Tệ nhất) bạn cần làm cũng chỉ tương đương với log cơ số 2 của N (khoảng 20 đến 30 bước).

---

## Tổng kết độ phức tạp của các giải pháp vừa làm

Tiến vào phân tích các file bài tập mà mình đã ứng dụng để giải:

### Trường hợp 1: Giải pháp trong bài Easy (Bài 1365 & 1346)

Quy trình xử lý gồm:

1. Chạy Bubble Sort để sắp xếp mảng: Mất **O(N^2)** (trường hợp trung bình).
2. Lặp qua mảng N lần, mỗi lần chạy Binary Search: Mất N \* O(log N).
   -> **Độ phức tạp tổng thể:** Vẫn tính theo bước tốn kém nhất là Bubble Sort: **O(N^2)**.

### Trường hợp 2: Giải pháp trong bài Medium (Bài 2300)

Ở bài toán Medium, do mảng đầu vào của Leetcode có thể rất lớn, chúng ta đã được khuyên không dùng Bubble Sort:

1. Dùng thuật toán sắp xếp mặc định của ngôn ngữ (QuickSort/MergeSort): Mất khoảng **O(N log N)**.
2. Lặp qua mảng spells và chạy Binary Search trên mảng potions: Tốn N \* O(log M).
   -> **Độ phức tạp tổng thể:** Quy về khoảng **O(N log N)**.

---

### Điểm cốt lõi đúc rút ra:

- Sắp xếp dữ liệu trước đôi khi mất chút thời gian ban đầu, nhưng đổi lại, nó cho phép ta sử dụng các công cụ mạnh mẽ như Binary Search để truy vấn cực nhanh về sau.
- Khi thiết kế giải pháp, hãy luôn nhìn vào kịch bản **Tệ nhất (Worst Case)** để đảm bảo hệ thống của bạn vẫn chạy an toàn ngay cả khi dữ liệu rơi vào trạng thái hỗn loạn nhất.
