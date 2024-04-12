/*
 * Class:  ArrayGen
 * Author:  Mr. Donald
 * Date: Nov 13, 2014
 * 
 * Description:
 *   -A class that randomly creates integer arrays of a given size with the desired distribution.
 *   -Each method is a class level method.
 * 
 *   int[] reversedArray(int size)  
 *         ->  Creates an array of size length of all unique values, and returns that array in reversed order
 *   int[] randomizedArray(int size)  
 *         ->  Creates an array of size length of all unique values, and shuffles the array so that the elements are
 *             in a random order.  Returns the shuffled array.
 *   int[] fewUniqueArray(int size)
 *         ->  Creates an array of size length, and populates it so that each element can only
 *             be 4 possible values. Returns the array.
 *   int[] nearlySortedArray(int size)
 *         ->  Creates an array of size length of all unique values, and shuffles small sections of the array, 
 *             so that elements are not in order, but still relatively close to their end position.
 * 
 */


import java.util.*;

public class ArrayGen
{
  //used with to shuffle elements.
  public static final Random gen = new Random();
  
  public static int[] reversedArray(int size) {
    int[] a = new int[size];
    
    for (int i = 0; i< size; i++) {
      a[i] = (size-i);
    }
    
    return a;
    
  }
  
  public static int[] randomizedArray(int size) {
    int[] a = new int[size];
    
    
    //Populate array
    for (int i = 0; i< size; i++)  {
      a[i] = i+1;
    }
    
    //Shuffle array using Knuth Shuffle
    while (size > 1)  {
      int k = gen.nextInt(size--); //decrements after using
      int temp = a[size];
      a[size] = a[k];
      a[k] = temp;
    }
    
    return a;
    
  }
  
  public static int[] fewUniqueArray(int size) {
    int[] a = new int[size];
    
    for (int i = 0; i<size; i++) {
      a[i] = (int)(4*Math.random())+1;
    }
    
    return a;
    
  }
  
  public static int[] nearlySortedArray(int size)  {
    int[] a = new int[size];
    
    //create the array
    for (int i = 0; i<size; i++) {
      a[i]=i+1;
    }
    
    
    //loosly shuffle the data by selecting groups of 3 elements 
    //and randomly chosing which pairs to switch
    for (int i = 0; i<size; i+=3) {
      int ran = (int)(4*Math.random());
      
      if ((ran == 0)&&((i+1)<size)) {
        int temp = a[i];
        a[i] = a[i+1];
        a[i+1] = temp;
      } else if ((ran == 1)&&((i+2)<size)) {
        int temp = a[i+1];
        a[i+1] = a[i+2];
        a[i+2] = temp;
      } else if ((ran ==2)&&((i+2)<size)) {
        int temp = a[i];
        a[i] = a[i+2];
        a[i+2] = temp;
      }
    }
    
    return a; 
  }
  
}