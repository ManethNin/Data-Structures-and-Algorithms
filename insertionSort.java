import java.util.Arrays;

public class insertionSort {
    public static void main(String[] args){
        int[] arr={2,5,1,7,3,9,4,6,1};
        for(int i = 0 ; i < arr.length-1;){
           if(arr[i]>arr[i+1]){
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
            if(i==0){
                i=0;
            }
            else{
                i=i-1;
            }
           }else{
            i++;
           }
           System.out.println(Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));
    }
}
