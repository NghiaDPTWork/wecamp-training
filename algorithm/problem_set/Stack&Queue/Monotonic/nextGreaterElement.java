class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[nums1.length];

        // Duyệt nums2 tìm next greater
        for(int num: nums2){

            while(!stack.isEmpty() && num > stack.peek()){
                map.put(stack.pop(), num);
            }

            stack.push(num);
        }

        // Duyệt nums1 và trả result
        for(int i = 0; i <= nums1.length - 1; i++){
            result[i] = map.getOrDefault(nums1[i], -1);
        }

        return result;
    }
}

====
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        int[] map = new int[10001];
        int[] stack = new int[nums2.length];
        int[] result = new int[nums1.length];
        int top = -1;

        // Duyệt nums2 tìm next greater
        for(int num: nums2){

            while(top >= 0 && num > stack[top]){
                map[stack[top]] = num;
                top--;
            }

            top++;
            stack[top] = num;
        }

        // Duyệt nums1 và trả result
        int nextGreater = 0;
        for(int i = 0; i <= nums1.length - 1; i++){
            nextGreater = map[nums1[i]];

            result[i] = (nextGreater == 0) ? -1 : nextGreater;
        }

        return result;
    }
}