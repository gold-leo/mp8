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
   * Recursive function for sort(). Sorts array v within the bounds given.
   * 
   * @param v
   *    The array of values.
   * @param start
   *    The starting bound (inclusive).
   * @param end
   *    The ending bound (inclusive).
   */
  private void sortHelper(T[] v, int start, int end) {
    // Find the length - 1 of the bounds given.
    int len = end - start;

    // Depending on the size, sort with a particular method.
    // If len = 0 (size = 1), do nothing (already sorted).
    // If len = 2 (size = 2), sort by comparing and flipping.
    // Otherwise, split the area between the bounds and sort them, then merge.
    if (len <= 0) {
      return;
    } else if (len == 1) {
      if (order.compare(v[start], v[end]) > 0) {
        ArrayUtils.swap(v, start, end);
      } // if
    } else {
      // halfLen is the separating space between the two new areas.
      int halfLen = (len / 2) + start;
      sortHelper(v, start, halfLen);
      sortHelper(v, halfLen + 1, end);
      merge(v, start, end, halfLen);
    } // if/if/else
  } // sortHelper(T[], int, int)

  private void merge(T[] v, int start, int end, int halfLen) {
    // Create a new empty array with the size of the whole given area.
    T[] arr = (T[]) Array.newInstance(v[0].getClass(), end - start + 1);

    // Merge the two areas based off of the sorted rules.
    for (int i = 0, x = start, y = halfLen + 1; i < arr.length; i++) {
      if (x > halfLen) {
        arr[i] = v[y++];
      } else if (y == arr.length + start) {
        arr[i] = v[x++];
      } else if (order.compare(v[x], v[y]) < 0) {
        arr[i] = v[x++];
      } else {
        arr[i] = v[y++];
      } // if/if/if/else
    } // for

    // Now replace the given area in the original array with the new array's order.
    for (int i = 0; i < arr.length; i++) {
      v[i + start] = arr[i];
    } // for
  } // merge(T[], int, int, int)
} // class MergeSorter
