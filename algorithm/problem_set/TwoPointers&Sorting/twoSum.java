class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;

        for(int i = 0; i <= len - 2; i++){
            for(int j = i + 1; j <= len - 1; j++){
                if(numbers[i] + numbers[j] == target){
                    return new int[] {i + 1, j + 1};
                }
            }
        }

        return new int[] {};
    }
}