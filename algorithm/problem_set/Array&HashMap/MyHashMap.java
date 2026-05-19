/**
 * [LeetCode 706] Design HashMap
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA GIẢI PHÁP:
 * 
 * GIẢI PHÁP: Thiết kế HashMap bằng phương pháp Băm chuỗi (Chaining) sử dụng danh sách liên kết (LinkedList)
 * - Ý tưởng: Sử dụng một mảng có kích thước cố định (chọn số nguyên tố 769 để giảm thiểu xung đột băm) chứa các LinkedList.
 *            Mỗi phần tử trong LinkedList là một cặp key-value dưới dạng mảng hai phần tử int[] {key, value}.
 *            Khi thực hiện put, get, remove, ta tính chỉ số bucket bằng hàm băm (hash) và thao tác trực tiếp trên LinkedList tương ứng.
 * - Ưu điểm:
 *   + Giải quyết xung đột băm hiệu quả: Các phần tử trùng mã băm chỉ đơn giản được thêm vào cuối LinkedList của bucket đó.
 *   + Bộ nhớ linh hoạt: Chỉ cấp phát các Node LinkedList thực tế khi có phần tử được thêm vào, tối ưu hơn mảng tĩnh khổng lồ.
 *   + Không giới hạn cứng dung lượng: Có thể chứa nhiều phần tử hơn ARRAY_SIZE do LinkedList có thể phát triển động liên tục.
 * - Nhược điểm:
 *   + Hiệu suất giảm khi xảy ra xung đột cao: Trong trường hợp xấu nhất, nếu nhiều key bị băm vào cùng một bucket, 
 *     các thao tác put, get, remove sẽ mất thời gian O(K) với K là độ dài danh sách (duyệt tuyến tính), không còn là O(1) tối ưu.
 *   + Tốn chi phí bộ nhớ phụ: Cấu trúc LinkedList của Java tiêu tốn bộ nhớ cho các con trỏ liên kết (next, prev) của từng Node.
 *   + Kích thước mảng cố định: Không hỗ trợ tự động mở rộng kích thước mảng (rehashing/resizing) khi số lượng phần tử tăng lên quá nhiều, 
 *     dẫn đến hệ số tải (load factor) tăng cao và suy giảm hiệu năng.
 */

import java.util.LinkedList;

class MyHashMap {
    private final int ARRAY_SIZE = 769;
    private LinkedList<int[]>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[ARRAY_SIZE];
        for(int i = 0; i < ARRAY_SIZE; i++){
            buckets[i] = new LinkedList<>();
        }
    }
    
    private int hash(int key){
        return Math.abs(Integer.hashCode(key)) % ARRAY_SIZE;
    }

    public void put(int key, int value) {
        int idx = hash(key);
        LinkedList<int[]> bucket = buckets[idx];

        for(int[] pair: bucket){
            if(pair[0] == key){
                pair[1] = value;
                return;
            }
        }
        bucket.add(new int[]{key, value});
    }
    
    public int get(int key) {
        int idx = hash(key);
        LinkedList<int[]> bucket = buckets[idx];

        for(int[] pair: bucket){
            if(pair[0] == key){
                return pair[1];
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int idx = hash(key);
        LinkedList<int[]> bucket = buckets[idx];

        for(int[] pair: bucket){
            if(pair[0] == key){
                bucket.remove(pair);
                return;
            }
        }
    }
}
