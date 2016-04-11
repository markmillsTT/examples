package Helpers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 
public class QuickSort {
 
      public static int Partition(int[] numbers, int left, int right)
    {
        int pivot = numbers[left];
        while (true)
        {
            while (numbers[left] < pivot)
                left++;
 
            while (numbers[right] > pivot)
                right--;
 
            if (left < right)
              {
                int temp = numbers[right];
                numbers[right] = numbers[left];
                numbers[left] = temp;
              }
              else
              {
                    return right;
              }
        }
    }
 
      private static int Partition(float[] numbers, int left, int right) {
  		
  		int pivot = Float.floatToIntBits(numbers[left]);
          while (true)
          {
              while (numbers[left] < pivot)
                  left++;
   
              while (numbers[right] > pivot)
                  right--;
   
              if (left < right)
                {
                  int temp = Float.floatToIntBits(numbers[right]);
                  numbers[right] = numbers[left];
                  numbers[left] = temp;
                }
                else
                {
                      return right;
                }
          }
  	}
      
    public static class QuickPosInfo
    {
        public int left;
        public int right;
    };
     
      public static QuickPosInfo info = new QuickPosInfo();
 
      public static void QuickSort_Iterative(int[] numbers, int left, int right)
    {
       
        if(left >= right)
            return; // Invalid index range
 
            LinkedList<QuickPosInfo> list = new LinkedList< QuickPosInfo>();
 
        info.left = left;
        info.right = right;
            list.add(info);
 
        while(true)
        {
            if(list.size() == 0)
                break;
 
                  left = list.get(0).left;
                  right = list.get(0).right;
                  list.remove(0);
 
            int pivot = Partition(numbers, left, right);   
            
            if(pivot > 1)
            {
                info.left = left;
                info.right = pivot - 1;
                list.add(info);
            }
 
            if(pivot + 1 < right)
            {
                info.left = pivot + 1;
                info.right = right;
                list.add(info);
            }
        }
    }
 
      public static List<Float> QuickSort_Iterative_Mark_Mills_Return(List<Float> unSortedXList, int left, int right)
      {
         float[] numbers = new float[unSortedXList.size()];
         
         for(int i = 0; i < unSortedXList.size() ; i++){
        	 numbers[i] = unSortedXList.get(i);
         }
         
          if(left >= right)
              return new ArrayList<Float>(); // Invalid index range
   
              LinkedList<QuickPosInfo> list = new LinkedList<QuickPosInfo>();
   
          info.left = left;
          info.right = right;
              list.add(info);
   
          while(true)
          {
              if(list.size() == 0)
                  break;
   
                    left = list.get(0).left;
                    right = list.get(0).right;
                    list.remove(0);
   
              int pivot = Partition(numbers, left, right);   
              
              if(pivot > 1)
              {
                  info.left = left;
                  info.right = pivot - 1;
                  list.add(info);
              }
   
              if(pivot + 1 < right)
              {
                  info.left = pivot + 1;
                  info.right = right;
                  list.add(info);
              }
          }
          
          
	      Float[] array = (Float[]) list.toArray();
	      List<Float> intList = new ArrayList<Float> ();
	      
	      for(int i = 0; i < array.length ; i++){
	    	  
	    	    intList.add(array[i]);
	    	  
	        }
	      
	          return intList;
      
      }

}