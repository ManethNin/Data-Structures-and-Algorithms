import java.util.Arrays;

public class bubbleSort {
    public static void main(String[] args) throws Exception {
        int[] arr={2,5,8,4,9,1,3,0,-8,4};
        
        for(int j = 0 ; j < arr.length; j++){
            int max = arr[0];
            for(int i = 1 ; i < arr.length - j; i++){
                
                if(arr[i]>max){
                    max = arr[i];
                }else{
                    int temp = arr[i];
                    arr[i] = max;
                    arr[i-1] = temp;                
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
