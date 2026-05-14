/**
 * 4. Median of Two Sorted Arrays (Tìm trung vị của hai mảng đã sắp xếp)
 */

/**
 * CÁCH 1: Hợp nhất hai mảng rồi tìm Trung vị
 * - Độ phức tạp thời gian: O(M + N) - Duyệt qua cả 2 mảng.
 * - Độ phức tạp không gian: O(M + N) - Tạo một mảng mới để chứa kết quả trộn.
 */
function mergeSorted(arr1, arr2) {
  let merged = [];
  let i = 0;
  let j = 0;

  while (i < arr1.length && j < arr2.length) {
    if (arr1[i] < arr2[j]) {
      merged.push(arr1[i]);
      i++;
    } else {
      merged.push(arr2[j]);
      j++;
    }
  }

  // Gom hết phần thừa nếu có
  while (i < arr1.length) {
    merged.push(arr1[i]);
    i++;
  }
  while (j < arr2.length) {
    merged.push(arr2[j]);
    j++;
  }

  return merged;
}

function findMedianSortedArraysMerge(arr1, arr2) {
  const merged = mergeSorted(arr1, arr2);
  const mid = Math.floor(merged.length / 2);
  const n = merged.length;

  if (n % 2 === 0) {
    return (merged[mid - 1] + merged[mid]) / 2;
  }
  return merged[mid];
}

/**
 * CÁCH 2 (ĐÚNG YÊU CẦU): Dùng Binary Search để giải quyết
 * - Độ phức tạp thời gian cực kỳ tối ưu: O(log(min(M, N))).
 * - Độ phức tạp không gian: O(1) - Không tạo thêm mảng phụ nào.
 * - Ý tưởng: Tìm nhị phân điểm cắt vách ngăn sao cho tập hợp bên trái nhỏ hơn tập hợp bên phải.
 */
function findMedianSortedArraysOptimal(nums1, nums2) {
  // Để tối ưu hóa, ta luôn thực hiện Binary Search trên mảng ngắn hơn
  if (nums1.length > nums2.length) {
    return findMedianSortedArraysOptimal(nums2, nums1);
  }

  const m = nums1.length;
  const n = nums2.length;
  let left = 0;
  let right = m;

  while (left <= right) {
    // Tìm các điểm phân hoạch (cắt mảng)
    const partitionX = Math.floor((left + right) / 2);
    const partitionY = Math.floor((m + n + 1) / 2) - partitionX;

    // Lấy 4 giá trị bao quanh vách ngăn:
    // Nếu vách cắt nằm sát lề, ta coi như giá trị đó cực đại/cực tiểu để phép so sánh vẫn đúng
    const maxX = partitionX === 0 ? -Infinity : nums1[partitionX - 1];
    const minX = partitionX === m ? Infinity : nums1[partitionX];

    const maxY = partitionY === 0 ? -Infinity : nums2[partitionY - 1];
    const minY = partitionY === n ? Infinity : nums2[partitionY];

    // Kiểm tra điều kiện vách cắt hợp lệ
    if (maxX <= minY && maxY <= minX) {
      // Tìm thấy vách ngăn chính xác!
      // Trả về trung vị dựa trên tổng số phần tử là chẵn hay lẻ
      if ((m + n) % 2 === 0) {
        return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
      } else {
        return Math.max(maxX, maxY);
      }
    } else if (maxX > minY) {
      // Phân hoạch ở nums1 quá sâu về bên phải -> Dịch vách cắt sang trái
      right = partitionX - 1;
    } else {
      // Phân hoạch ở nums1 quá nông về bên trái -> Dịch vách cắt sang phải
      left = partitionX + 1;
    }
  }

  throw new Error("Đầu vào không đúng định dạng mảng đã sắp xếp.");
}
