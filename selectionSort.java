import java.util.Arrays;

public class selectionSort {

    public static void main(String[] args) {
        int[] arr={2,5,7,3,9,1,6,5};
        for(int j = 0 ; j < arr.length; j++){
            int min = arr[j];
            int index = j;
            for(int i = j ; i < arr.length ; i++){
                if(arr[i]<min){
                    min=arr[i];
                    index = i ;
                }
            }
            int temp = arr[j];
            arr[j] = min;
            arr[index] = temp;
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));

    }
}
