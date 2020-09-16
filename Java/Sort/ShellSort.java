import java.util.Arrays;

public class ShellSort{

    public static <T extends Comparable<? super T>> void shellSort(T[] arr){
        int j;
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                T tmp=arr[i];
                for(j=i;j-gap>=0 && tmp.compareTo(arr[j-gap])<0;j-=gap){
                    arr[j]=arr[j-gap];
                }
                arr[j]=tmp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr={0,31,53,59,88,41,58,57,97};
        System.out.println(Arrays.asList(arr).toString());
        shellSort(arr);
        System.out.println(Arrays.asList(arr).toString());
    }
}