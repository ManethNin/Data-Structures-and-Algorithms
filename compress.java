import java.util.*;

public class compress {

    public static void main(String[] args) {
        System.out.println("Hello World!!!");
        Solution sol = new Solution();

        char[] arr = {'b','b','a','a','a','b','b'};
        int answer = sol.compress(arr);
        System.out.println(answer);
        
    }
}
class Solution {
    public int compress(char[] chars) {
        char temp = chars[0];
        StringBuilder s = new StringBuilder();
        int count = 0 ;

        if(chars.length == 1){
            return 1;
        }
        for(int i = 0 ; i < chars.length; i++){

            // System.out.println(temp);
            if(temp == chars[i]){
                count +=1;
                if( i != chars.length -1 ){
                    continue;
                }
                
            }
            s.append(temp);
            if(count > 1){
                s.append(count);
            }

            if(temp != chars[i] && i == chars.length -1 ){
                s.append(chars[i]);
            }
            count = 1;
            temp = chars[i];

           
            
        }

        
        System.out.println(s);
        char[] my = s.toString().toCharArray();

        for(int i = 0 ; i < s.length(); i++){
            chars[i] = my[i];
        }
       

        return s.length();
        
    }
}