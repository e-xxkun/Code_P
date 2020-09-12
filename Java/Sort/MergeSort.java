import java.util.Arrays;

public class MergeSort{

    public static <T extends Comparable<? super T>> void mergeSort(T[] arr,T[] tmpArr,int left,int right){
        if(right>left){
            int center=(right+left)/2;
            mergeSort(arr,tmpArr,left,center);
            mergeSort(arr,tmpArr,center+1,right);
            merge(arr,tmpArr,left,center+1,right);
        }
    }

    public static <T extends Comparable<? super T>> void merge(T[] arr,T[] tmpArr,int leftPos,int rightPos,int rightEnd){
        int leftEnd=rightPos-1;
        int tmpPos=leftPos;
        int len=rightEnd-leftPos+1;
        while(leftPos<=leftEnd && rightPos<=rightEnd){
            if(arr[rightPos].compareTo(arr[leftPos])>0){
                tmpArr[tmpPos++]=arr[leftPos++];
            }else{
                tmpArr[tmpPos++]=arr[rightPos++];
            }
        }
        while(leftPos<=leftEnd){
            tmpArr[tmpPos++]=arr[leftPos++];
        }
        while(rightPos<=rightEnd){
            tmpArr[tmpPos++]=arr[rightPos++];
        }
        for(int i=0;i<len;i++,rightEnd--){
            arr[rightEnd]=tmpArr[rightEnd];
        }
    }

    public static void main(String[] args) {
        Integer[] arr={2,5,4,9,6,7,3,8,1};
        Integer[] tmpArr=new Integer[arr.length];

        mergeSort(arr,tmpArr,0,arr.length-1);

        // merge(arr,tmpArr,0,(arr.length-1)/2,arr.length-1);

        System.out.println(Arrays.asList(arr).toString());
        System.out.println("Success!!!");
    }
}