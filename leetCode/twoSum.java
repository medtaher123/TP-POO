class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> m = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            Integer k = m.get(Integer.valueOf(target-nums[i]));
            if(k!=null){
                return new int[] {k,i};
            }
            m.put(nums[i],i);
        }
        return null;
    }
}