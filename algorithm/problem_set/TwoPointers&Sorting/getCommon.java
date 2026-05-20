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