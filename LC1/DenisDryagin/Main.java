class Main {
    public int[] twoSum(int[] nums, int target) {
       int[] answear = new int[2];

        outherLoop:
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    answear[0] = i;
                    answear[1] = j;
                    break outherLoop;
                }
            }
        }
       return answear;
    }
}
