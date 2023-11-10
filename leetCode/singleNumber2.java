class Solution {
    public int singleNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (list.contains(num)) {
                list.remove(Integer.valueOf(num));
            } else {
                list.add(num);
            }
        }
        return list.get(0);
    }
}