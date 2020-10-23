package rejectStrategy;

import java.util.Arrays;
import java.util.List;

public class TestBinary {
    static List<Integer> list = Arrays.asList(2,4,7,9,11,13);

    public static void main(String[] args) {
        System.out.println(binarySearch(14));
    }
    public static int binarySearch(int hashValue){
        int start = 0;
        int end = list.size();
        int middle = 0;
        while(start<end){
            middle = (start+end)/2;
            System.out.println(middle);
            if(list.get(middle)<hashValue){
                start = middle+1;
            }
            else{
                end = middle;
            }
        }
        return start;
    }
}
