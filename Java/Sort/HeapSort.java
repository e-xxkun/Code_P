import java.util.Arrays;

public class HeapSort{

    private static <T extends Comparable<? super T>> void swap(T[] arr,int p1,int p2){
        T tmp=arr[p1];
        arr[p1]=arr[p2];
        arr[p2]=tmp;
    }

    private static <T extends Comparable<? super T>> void percDown(T[] arr,int i,int len){
        int child=2*i+1;
        T tmp=arr[i];
        for(;child<len;i=child,child=2*i+1){
            if(child+1<len&&arr[child].compareTo(arr[child+1])<0){
                child+=1;
            }
            if(tmp.compareTo(arr[child])<0)
                arr[i]=arr[child];
            else
                break;
        }
        arr[i]=tmp;
    }

    public static <T extends Comparable<? super T>> void heapSort(T[] arr){
        for(int i=arr.length/2-1;i>=0;i--){
            percDown(arr,i,arr.length);
        }
        for(int i=arr.length-1;i>0;i--){
            swap(arr,0,i);
            percDown(arr,0,i);
        }
    }

    public static void main(String[] args) {
        Integer[] arr={0,31,53,59,88,41,58,57,97};
        System.out.println(Arrays.asList(arr).toString());
        heapSort(arr);
        System.out.println(Arrays.asList(arr).toString());
    }
}