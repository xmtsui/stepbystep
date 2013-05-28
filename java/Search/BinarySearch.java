/**
 * Binary search
 * 
 * @author xmtsui
 * @version v1.0
 */
class BinarySearch
{
    //非递归
    static int binary_search(int[] array, int value)
    {
        int low = 0;
        int high = array.length-1;
        while(low <= high)
        {
            int middle = (low+high)/2;
            if(value == array[middle])
                return middle;
            else if(value < array[middle])
                high = middle - 1;
            else
                low = middle + 1;
        }
        return -1;
    }

    //递归
    static int binary_search(int[] array, int value, int start, int end)
    {
        int middle = (end + start)/2;
        if(start > end)
            return -1;
        if(array[middle]>array[end] || array[middle]<array[start])
            return -1;
        if(value == array[middle])
            return middle;
        else if(value < array[middle])
            return binary_search(array, value, start, middle-1);
        else
            return binary_search(array, value, middle+1, end);
    }

    public static void main(String[] args)
    {
        int[] array = {1,2,3,4,5,6,7,8,9};
        System.out.println(binary_search(array, 6));
        System.out.println(binary_search(array, 10, 0, array.length-1));
    }
}