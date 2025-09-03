
public class increasingTriplet {

    public static void main(String[] args) {
        System.out.println("Hello World!!!");
        Solution sol = new Solution();

        int[] arr = {-1,1,4,-3,3};
        boolean answer = sol.productExceptSelf(arr);
        System.out.println(answer);
        
    }
}

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int max1 = nums[0];
        int max2 = Integer.MAX_VALUE;;
        int max3 = Integer.MAX_VALUE; ;

        for(int i = 0 ; i < nums.length; i++){
            if(max1 < nums[i]){
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            }
        }

        if(max2 > max1 || max3 > max1){
            return false;
        }
        return true;
    }
}