# Discussion

The `Roster` class uses `IndexedList` to store a list of students. The
`Roster.find` implements the binary search algorithm. Which
implementation of the `IndexedList` should be used to implement the
`Roster` class? (It could be one or more of `ArrayIndexedList`,
`LinkedIndexList`, `SparseIndexedList`). And why?
   
--------------- Write your answers below this line ----------------

A `SparseIndexedList` would not work because each student most likely 
has a unique name and email; there is no default value. Assuming both 
an `ArrayIndexedList` and a `LinkedIndexedList` are sorted, executing
a binary search algorithm is more efficient on an `ArrayIndexedList`
because one can simply access a given index in the array whereas with
a `LinkedIndexedList` one must traverse the whole list to find the 
node with a given index.