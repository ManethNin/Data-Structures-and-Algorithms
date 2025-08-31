import java.util.Arrays;

public class test1 {
    public static void main(String[] args) {
        System.out.println("Hello World!!!");
        Solution sol = new Solution();

        int[] arr = {-1,1,0,-3,3};
        int[] answer = sol.productExceptSelf(arr);
        for(int i : answer){
            System.out.println(i);
        }
    }
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        
        int[] result = new int[nums.length];
        
        int n = nums.length;

        Arrays.fill(result, 1);

        result[0] =1;
        result[n-1] = 1;
        int temp = 1;
        int pre =1;
        int suf =1 ; 

        for(int i = 1 ; i < nums.length ; i++){
            pre = pre * nums[i-1];
            suf = suf * nums[n-i];

            result[i] = result[i] * pre;
            result[n-1-i] *= suf;

            // result[i] = temp;
        }
        // for(int i = 0 ; i < nums.length ; i++){
        //     result[i] = pre[i] * suf[i];
        // }
        return result;
    }
}