# Discussion

## Unit testing TreapMap

    The main difficulty with testing rotations for TreapMap was setting up different scenarios. Because of the random
    nature of the Treap I created an additional constructor with a set seed and then recorded the first 10 priorities
    this seed would generate. The numbers were very large and thus keeping track of the relative priorities was
    cumbersome when I was setting up the test cases. I had to make sure that I was adding / removing nodes in an order
    with respect to their values that would cause rotations with the given priority. Here are 3 examples of test cases:

    1. insertCausesLeftRotation()

          2                 2
         / \   insert(4)   / \
        1   3     >>>     1   4
                             /
                            3

    Using a set seed of 1 I knew that the 1st node added would have a higher priority than the 2nd, and 2nd > 3rd, but
    4th > 3rd, so I inserted 2 then 1 then 3 then 4 to cause a left rotation.

    2. insertCausesRightRotation()

          3                 3
         / \   insert(1)   / \
        2   4     >>>     1   4
                           \
                            2

    Using the same logic as the above case I inserted nodes in the same pattern but I inserted 2 3rd so that adding 1 
    as the 4th value would cause a right rotation.

    3. removeCausesRotationAndInsertCausesRotation()

          3                 4                 4
         / \   remove(3)   /     insert(1)   /
        2   4     >>>     2         >>>     1
                                             \
                                              2  

    Any removal of two nodes will cause a rotation due to Treap implementation. However, to test whether removal would
    cause a right vs a left rotation meant making sure the node to be removed had a left child with higher priority
    than the right or vice-versa. In this test case I inserted 3 then 3 then 2 so that removing 3 would cause a left
    rotation. However, to test a more complicated case, I arranged it so inserting a 1 would then cause a right
    rotation afterwords.




## Benchmarking

    "federalist01.txt"

    Benchmark                  Mode  Cnt  Score   Error  Units
    JmhRuntimeTest.arrayMap    avgt    2  1.513          ms/op
    JmhRuntimeTest.avlTreeMap  avgt    2  0.648          ms/op
    JmhRuntimeTest.bstMap      avgt    2  0.661          ms/op
    JmhRuntimeTest.treapMap    avgt    2  0.800          ms/op

    "hotel_california.txt"

    Benchmark                  Mode  Cnt  Score   Error  Units
    JmhRuntimeTest.arrayMap    avgt    2  0.162          ms/op
    JmhRuntimeTest.avlTreeMap  avgt    2  0.120          ms/op
    JmhRuntimeTest.bstMap      avgt    2  0.124          ms/op
    JmhRuntimeTest.treapMap    avgt    2  0.146          ms/op

    "moby_dick.txt"

    Benchmark                  Mode  Cnt     Score   Error  Units
    JmhRuntimeTest.arrayMap    avgt    2  2401.822          ms/op
    JmhRuntimeTest.avlTreeMap  avgt    2    96.803          ms/op
    JmhRuntimeTest.bstMap      avgt    2   100.474          ms/op
    JmhRuntimeTest.treapMap    avgt    2   108.876          ms/op

    "pride_and_prejudice.txt"

    