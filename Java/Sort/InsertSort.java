import java.util.Arrays;
public class InsertSort{

    private static <T extends Comparable<? super T>> void swap(T[] arr,int p1,int p2) {
        T tmp=arr[p1];
        arr[p1]=arr[p2];
        arr[p2]=tmp;
    }

    public static <T extends Comparable<? super T>> void insertSort(T[] arr){
        for(int i=1;i<arr.length;i++){
            int j=i;
            while(j>0&&arr[j].compareTo(arr[--j])<0){
                swap(arr,j,j+1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr={8,4,6,2,1,7,2,2,5,3,9,5};
        insertSort(arr);
        System.out.println(Arrays.asList(arr).toString());

    }
}