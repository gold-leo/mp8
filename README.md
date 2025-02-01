# mp-sorting-maven

An exploration of sorting in Java.
- Quicksort
- Merge Sort
- Custom Sorting Algorithm (GLSort)

Acknowledgements

* Samuel A. Rebelsky (starter code)

This code may be found at <https://github.com/gold-leo/mp8>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
---------------------------------------

_GLSort_ is a Quicksort algorithm which uses two methods to achieve a quicker sorting algorithm.

- Median of three: _GLSort_ uses a median of three pivot algorithm to lower the chances of poor pivots. The pivots are chosen in the same location in the algorithm each time: one-fourth, one-half, three-fourths. Through testing, this method proved to be quicker than an insertion sort even in arrays with size ~< 30 for most types of arrays.

- Small-size algorithms: For arrays size 4 or smaller, specific swap algorithms are used rather than quicksort. This cuts down unessecary computation by a significant amount.

Notes on using Copilot (or other AI)
------------------------------------

Did not use AI or Copilot.  
