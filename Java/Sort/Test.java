import java.util.Arrays;

public class Test{

    public static void main(String[] args) {
        int[] arr={0,1,2,3,4,5,6};
        int j=3;
        int i=arr[j--]+arr[j];
        System.out.println(i);
        System.out.println("Success!!!");
    }
}