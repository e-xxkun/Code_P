import java.util.Arrays;
public class InsertSort{

    public static <T extends Comparable<? super T>> void insertSort(T[] arr){
        int j;
        for(int i=1;i<arr.length;i++){
            T tmp=arr[i];
            for(j=i;j>0 && tmp.compareTo(arr[j-1])<0;j--){
                arr[j]=arr[j-1];
            }
            arr[j]=tmp;
        }
    }

    public static void main(String[] args) {
        Integer[] arr={8,4,6,2,1,7,2,2,5,3,9,5};
        insertSort(arr);
        System.out.println(Arrays.asList(arr).toString());

    }
}