import java.util.*;

public class Algorithms
{

    public static void algorithmOne(int mergeSortArray[], int x, int y, int z)
    {

        int q = 0;
        int w = 0;
        int k = x;

        int subArray1 = (y - x) + 1;
        int subArray2 = (z - y);


        int tempArray1 [] = new int[subArray1];
        int tempArray2 [] = new int[subArray2];

        for (int i = 0; i < subArray1; ++i)
            tempArray1[i] = mergeSortArray[x + i];
        for (int j = 0; j < subArray2; ++j)
            tempArray2 [j] = mergeSortArray[y + 1 + j];


        while (q < subArray1 && w < subArray2) {
            if (tempArray1[q] <= tempArray2 [w]) {
                mergeSortArray[k] = tempArray1 [q];
                q++;
            }
            else {
                mergeSortArray[k] = tempArray2 [w];
                w++;
            }
            k++;
        }

        while (q < subArray1) {
            mergeSortArray[k] = tempArray1[q];
            q++;
            k++;
        }


        while (w < subArray2) {
            mergeSortArray[k] = tempArray2 [w];
            w++;
            k++;
        }
    }

    public void mergeSort(int array[], int x, int r)
    {
        if (x < r) {

            int m = x + ((r- x)/2);

            mergeSort(array, x, m);
            mergeSort(array, m + 1, r);

            algorithmOne(array, x, m, r);
        }
    }




    public static int quickSortIterative (int mergeArray[], int small, int large) {
        int i = (small - 1);
        int tempArray;
        int pivotPosition = mergeArray[large];
        for (int j = small; j <= large - 1; j++) {
            if (mergeArray[j] <= pivotPosition) {
                i++;


                tempArray = mergeArray[i];
                mergeArray[i] = mergeArray[j];
                mergeArray[j] = tempArray;
            }
        }

        tempArray = mergeArray[i + 1];
        mergeArray[i + 1] = mergeArray[large];
        mergeArray[large] = tempArray;

        return (i + 1);

    }




    public static int quickSortRecursive (int[] quickSortArray, int small, int large) {
        int pivotPosition = quickSortArray[large];
        int pivot1 = small;
        for (int i = small; i <= large; i++) {
            if (quickSortArray[i] < pivotPosition) {
                int tempArray = quickSortArray[i];
                quickSortArray[i] = quickSortArray[pivot1];
                quickSortArray[pivot1] = tempArray;
                pivot1++;
            }
        }

        int tempArray = quickSortArray[large];
        quickSortArray[large] = quickSortArray[pivot1];
        quickSortArray[pivot1] = tempArray;

        return pivot1;
    }




    public static int FindMedian(int[] arr, int left, int right){
        Arrays.sort(arr, left, right);

        return arr[left + (right - left)/2];

    }

    public static int Partition(int[] arr, int left, int right, int value){
        int count;

        for (count = left; count < right; count++){
            if (arr[count] == value){
                int temp = arr[count];
                arr[count] = arr[right];
                arr[right] = temp;

            }
        }

        count = left;
        for (int i = left; i < right; i++) {
            if (arr[i] <= value) {
                int temp = arr[count];
                arr[count] = arr[i];
                arr[i] = temp;

                count++;
            }
        }
        int temp = arr[count];
        arr[count] = arr[right];
        arr[right] = temp;

        return count;

    }

    public static  int mmRulePartition (int[] arr, int left, int right, int k){
        if (k > 0 && k <= right - left + 1){
            int numOfElements = right - left + 1;
            int numOfGroups = (numOfElements + 6) / 7;
            int[] median = new int[numOfGroups];
            int count;

            for(count = 0; count < numOfElements / 7; count++){
                median[count]= FindMedian(arr,left + count * 7,left + count * 7 + 7);

            }

            if (count * 7 < numOfElements){
                median[count]= FindMedian(arr, left + count * 7, left + count * 7 + numOfElements % 7);
                count++;

            }

            int mOfm;
            if (count == 1){
                mOfm = median[count - 1];
            }else {
                mOfm = mmRulePartition(median, 0, count - 1, count / 2);
            }

            int location = Partition(arr, left, right, mOfm);

            if (location - left == k - 1){
                return arr[location];

            }else if (location - left > k - 1){
                return mmRulePartition(arr, left, location - 1, k);

            }
            else {
                return mmRulePartition(arr, location + 1, right, k - location + left - 1);

            }

        }else {
            int v = -1;
            return v;
        }


    }



    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        Algorithms algorithms = new Algorithms();
        int n,k,algo;
        long start, end, total;
        System.out.println("Please enter the number of elements of the array: ");
        n = scanner.nextInt();
        int array[] = new int[n];
        System.out.println("Please enter the k th smallest element you want to find: ");
        k = scanner.nextInt();
        System.out.println("1. Algorithm 1 (Merge Sort) ");
        System.out.println("2. Algorithm 2 (QuickSort with Partition) ");
        System.out.println("3. Algorithm 3 (Quicksort with Iterative) ");
        System.out.println("4. Algorithm 4 (QuickSort Partition with MM rule) ");
        System.out.println("Please enter the number of algorithm you want to use: ");
        algo = scanner.nextInt();


        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(100);
        }

        switch (algo){
            case 1:
                start = System.nanoTime();
                algorithms.mergeSort(array,k,array.length - 1);
                end = System.nanoTime();
                total = end - start;
                System.out.println("The algo 1 run time is: ");
                System.out.println(total);
                break;
            case 2:
                start = System.nanoTime();
                quickSortRecursive(array,0,array.length - 1);
                end = System.nanoTime();
                total = end - start;
                System.out.println("The algo 2 run time is: ");
                System.out.println(total);
                break;
            case 3:
                start = System.nanoTime();
                quickSortIterative(array,0,array.length - 1);
                end = System.nanoTime();
                total = end - start;
                System.out.println("The algo 3 run time is: ");
                System.out.println(total);
                break;
            case 4:
                start = System.nanoTime();
                mmRulePartition(array,0, array.length - 1, k);
                end = System.nanoTime();
                total = end - start;
                System.out.println("The algo 4 run time is: ");
                System.out.println(total);
                break;
            default:
                System.out.println(" -1 ");
                break;

        }

    }
}


