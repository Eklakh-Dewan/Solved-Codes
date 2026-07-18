class Solution {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int x=nums[0];
        int gcd=1;
        for(int i=1;i<=x;i++){
            if (x%i==0 && nums[(nums.length-1)]%i==0 ){
                gcd=i;

            }
        
        }
       return gcd; 
        
    }
}