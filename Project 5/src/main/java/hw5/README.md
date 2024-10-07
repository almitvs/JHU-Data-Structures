# Discussion

## Flawed Deque

1. InsertBack does not always insert into the list
   A few of my tests, such as backReturnsCorrectlyAfterInsertBackTwice, 
   backReturnsCorrectlyAfterInsertFrontThenInsertBack, and testLengthAfterInsertBackOneHundredTimes found this flaw.
   If the FlawedDeque is treated like a Queue and only inserted at the back, then every n insert where n = 2^x + x + 1
   and x is any integer greater than or equal to zero will not insert. However, when the FlawedDeque is treated like
   a stack and only inserted at the front then every insert is successful. When FlawDeque is treated like both, then
   insertBack still does not work all the time. Perhaps the FlawedDeque implementation fails to shift the data over 
   correctly and / or update the tail index / pointer.

2. Back throws LengthException instead of EmptyException
   A few of my test, such as backThrowsEmptyExceptionAfterBackFrontRemoveBack and
   backReturnsCorrectlyAfterRemoveFrontTwice found this flaw. Based on the Deque interface this exception should be
   thrown when the Deque is empty. Perhaps the FlawedDeque implementation simply throws the wrong exception.

3. RemoveBack does not throw EmptyException
   My test removeBackThrowsEmptyException found this flaw. Based on the Deque interface this exception should be
   thrown when the Deque is empty. Perhaps the FlawedDeque implementation handles this case without throwing the
   exception, simply not letting the removeBack operation to happen.

4. RemoveFront does not throw EmptyException
   My test removeFrontThrowsEmptyException found this flaw. Based on the Deque interface this exception should be
   thrown when the Deque is empty. Perhaps the FlawedDeque implementation handles this case without throwing the
   exception, simply not letting the removeFront operation to happen.


## Hacking Linear Search

   The objective of the heuristics is to make objects that are searched for easier to find when they are searched for 
   again by bringing them closer to the front, thus decreasing the time of a linear search. However, with the current
   implementation these heuristics are performed any time find is used, which includes the remove method. This conflicts
   with the logic of remove because it is not possible to search for a removed object (unless it is added again). In
   other words, remove moves objects to the front or swaps them (due to find)then removes them, which is not necessary 
   because they are going to be removed, hence remove is slightly less efficient with the heuristics.

## Profiling

   For this experiment I instantiated each list with 1,000 elements (the integers 0 through 999 in order) then attempted 
   to insert the number 999 1,000 times. With each attempted insert the find method is invoked but the number is not
   actually inserted because 999 is already an element in the sets. However, each use of find should bring 999 closer
   to the front in the case of the TransposeArraySet or to the very front as is the case of the MoveToFrontLinkedSet,
   hence making these faster. My hypothesis was proven correct: arraySet returned a score of 0.639 whereas
   transPoseSequence got 0.451. linkedSet returned a score of 2.856, compared to moveToFront's 1.001. Each performed as
   expected. Based on my experiment, with a large enough list and enough trials or invocations of methods, the 
   heuristics result in faster performance when someone repeatedly searches for the same element.