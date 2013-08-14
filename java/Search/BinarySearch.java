/**
 * Binary search第一次实现
 * 本例中记录了常犯的错误
 * 
 * @author xmtsui
 * @version v1.0 2013-08-01
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

    /**
     * 犯了几个典型错误的非递归二分查找
     * @param  seq  [description]
     * @param  key  [description]
     * @param  low  [description]
     * @param  high [description]
     * @return      [description]
     */
    @deprecated
    private static int binary_search_error1(int[] seq, int key)
    {
        if(seq == null)
            return -1;
        int low=0;
        int high=seq.length;//典型错误，应该是seq.length-1
        while(low <= high)
        {
            int medium=(low+high)/2;
            if(key>seq[medium])
                low=medium+1;
            else if(key<seq[medium])
                high=medium-1;
            else
                return medium;
        }
        return -1;
    }

    /**
     * 犯了几个典型错误的递归二分查找
     * @param  seq  [description]
     * @param  key  [description]
     * @param  low  [description]
     * @param  high [description]
     * @return      [description]
     */
    @deprecated [deprecated-text]
    private static int binary_search_error2(int[] seq, int key, int low, int high)
    {
        if(seq == null)
            return -1;
        //检测边界参数
        if(low>high)
            return -1;
        //查找key,若比最小值小，或者最大值大，则查找失败，返回－1
        if(key<seq[low]/*典型错误,应该是0*/ || key>seq[high])
            return -1;
        //折半递归查找
        int medium=(low+high)/2;
        if(key>seq[medium])
            return binary_search_error2(seq, key, low+1, high);//典型错误,应该是medium+1
        else if(key<seq[medium])
            return binary_search_error2(seq, key, low, high-1);//典型错误
        else
            return medium;
    }

    public static void main(String[] args)
    {
        int[] array = {1,2,3,4,5,6,7,8,9};
        System.out.println(binary_search(array, 6));
        System.out.println(binary_search(array, 10, 0, array.length-1));
    }
}