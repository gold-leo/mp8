package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import  java.lang.reflect.Array;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sortHelper(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * 
   * @param v
   * @param start
   * @param end
   */
  private void sortHelper(T[] v, int start, int end) {
    int len = end - start;
    if (len == 0) {
      return;
    } else if (len == 1) {
      if (order.compare(v[start], v[end]) > 0) {
        ArrayUtils.swap(v, start, end);
      }
    } else {
      int halfLen = (len / 2) + start;
      sortHelper(v, start, halfLen);
      sortHelper(v, halfLen + 1, end);

      T[] arr = (T[]) Array.newInstance(v[0].getClass(), len + 1);
      System.err.println("merge sort begin. size: " + arr.length);
      System.err.println("start: " + start + " halfLen: " + halfLen + " end: " + end);
      for (int i = 0, x = start, y = halfLen + 1; i < arr.length; i++) {
        System.err.println("index: " + i);
        System.err.println("x: " + x + " y: " + y);
        if (x > halfLen) {
          arr[i] = v[y++];
        } else if (y == arr.length + start) {
          arr[i] = v[x++];
        } else if (order.compare(v[x], v[y]) < 0) {
          arr[i] = v[x++];
        } else {
          arr[i] = v[y++];
        }
      }
      for (int i = 0; i < arr.length; i++) {
        v[i + start] = arr[i];
      }
    } // if/if/else
  }
} // class MergeSorter
