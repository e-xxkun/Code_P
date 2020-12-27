import java.util.Arrays;

public class QuickSort{

    final static int LENGTH_MIN_LEN=7;

    private static <T extends Comparable<? super T>> void swap(T[] arr,int p1,int p2){
        T tmp=arr[p1];
        arr[p1]=arr[p2];
        arr[p2]=tmp;
    }

    private static <T extends Comparable<? super T>> T median3(T[] arr,int left,int right){
        int center =(left+right)/2;
        if(arr[center].compareTo(arr[left])<0){
            swap(arr,center,left);
        }
        if(arr[right].compareTo(arr[left])<0){
            swap(arr,right,left);
        }
        if(arr[right].compareTo(arr[center])<0){
            swap(arr,right,center);
        }
        swap(arr,center,right);
        return arr[right];
    }

    private static <T extends Comparable<? super T>> void insertSort(T[] arr){
        for(int i=1;i<arr.length;i++){
            int j=i;
            while(j>0&&arr[j].compareTo(arr[--j])<0){
                swap(arr,j,j+1);
            }
        }
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] arr,int left,int right){
        if(right-left>LENGTH_MIN_LEN){
            T medT=median3(arr,left,right);
            int r=right;
            int l=left;
            for(;;){
                while(arr[++l].compareTo(medT)<0);
                while(arr[--r].compareTo(medT)>0);

                if(l<r)
                    swap(arr,l,r);
                else
                    break;
            }
            swap(arr,l,right);

            quickSort(arr,left,l-1);
            quickSort(arr,l,right);
        }else{
            insertSort(arr);
        }
    }

    public static void main(String[] args) {
        Integer[] arr={9,5,8,3,7,6,4,1,1,6,2,9,5,8,3,7,6,4,1,1,6,2,9,5,8,3,7,6,4,1,1,6,2,9,5,8,3,7,6,4,1,1,6,2};
        System.out.println(Arrays.asList(arr).toString());
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.asList(arr).toString());
    }
}