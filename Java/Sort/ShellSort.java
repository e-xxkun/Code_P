import java.util.Arrays;

public class ShellSort{

    public static <T extends Comparable<? super T>> void shellSort(T[] arr){
        int j;
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=1;i<arr.length;i+=gap){
                j=i;
                while(j>0&&arr[j].compareTo(arr[--j])<0){
                    
                }
            }
        }
    }

    public static void main(String[] args) {
        
    }
}