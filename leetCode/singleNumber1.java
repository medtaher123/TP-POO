class Solution {
    public int singleNumber(int[] nums) {
        int s = 0;
        for(int n: nums){
            s ^= n;
        }
        return s;
    }
}