# Discussion

## PART I: MEASURED IndexedList

**Discuss from a design perspective whether iterating over a `MeasuredIndexedList`should 
affect the accesses and mutation counts. Note that for the purposes of this assignment we are NOT 
asking you to rewrite the `ArrayIndexedListIterator` to do so. However, if you wanted to include 
the `next()` and/or `hasNext()` methods in the statistics measured, can you inherit 
`ArrayIndexedListIterator` from `ArrayIndexedList` and override the relevant methods, or not? 
Explain.**

Iterating over a `MeasuredIndexedList` should affect access counts but not mutation accounts because iterating
involves getting the values for some given amount of indices, but it does not involve changing them.

`ArrayIndexedListIterator` cannot be inherited by `MeasuredIndexedList` because it has private access and thus
cannot be overridden by the subclass.

## PART II: EXPERIMENTS

**Explain the mistake in the setup/implementation of the experiment which resulted in a discrepancy 
between the results and what is expected from each sorting algorithm.**

One issue is that the descending data is not sorted in descending order. For example, one snippet from the list of 
data is 9991, 9990, 999, 9989, thus affecting the expected times for this data set.

I noticed when I ran the sortingAlgorithmDrive for sizes of 1 through 10 the number of accesses and mutations were
as expected. I looked at the number of mutations on the ascending data for the insertion sort in particular because it
should always be the size - 1. I noticed as soon as size was 11 the behavior was not as expected. I realized that this
is when double-digit numbers come into play. I stepped into the program and 

The primary issue with the implementation of the experiment is that the numerical data is read and stored to the array 
as String type. Comparing strings does not work like comparing integers even though they are "numbers." For example, 
string "12" is considered less than string "2" because "12" begins with the digit "1", which is less than "2".



## PART III: ANALYSIS OF SELECTION SORT

**Determine exactly how many comparisons C(n) and assignments A(n) are performed by this 
implementation of selection sort in the worst case. Both of those should be polynomials of degree 2 
since you know that the asymptotic complexity of selection sort is O(n^2).**

In this analysis n represents the length of the array input into the function

Comparisons:
- In line 3 the for loop executes starting at i = 0 until i is equal to n - 1, thus n comparisons are made
- In line 5 the nested for loop executes starting at j = i + 1 until j is equal to n. Our values of j are based on our
  values of i, thus the domain of j is from 1 to n - 1. When j is 1 n comparisons are made. When j is n - 1 2
  comparisons are made. Thus, the total number of comparisons is the sum from 2 to n - 1. Using Gauss's formula the 
  number of comparisons is (n-1)(n+2)/2
- In line 6 the comparison is made while j is less than n, thus 1 time when j is n - 1 and n - 1 times when j is 1.
  Using Gauss's formula again the number of comparisons is (n-1)(n)/2
- Thus, the total C(n) = n + (n-1)(n+2)/2 + (n-1)(n)/2 = n^2 + n + 1.

Assignments:
- In line 3 i is assigned, thus 1 assignment is made
- In line 4 max is assigned for each pass of the outer for loop, thus n - 1 assignments are made
- In line 5 j is assigned for each pass of the out for loop, thus n - 1 assignments are made
- In line 7, assuming the worst case, as made assignments are made as comparisons in 6, thus (n-1)(n)/2 assignments
- In line 10 an assignment is made for each pass of the for loop, thus n - 1 assignments are made 
- In line 11 an assignment is made for each pass of the for loop, thus n - 1 assignments are made
- In line 12 an assignment is made for each pass of the for loop, thus n - 1 assignments are made
- Thus, the total A(n) = 1 + (n-1) + (n-1) + (n-1)(n)/2 + (n-1) + (n-1) + (n-1) = (1/2)n^2 + (9/2)n - 4.


