# Discussion

After testing, I have decided that the OpenAddressingHashMap with a load factor threshold of 0.7 is the better of my
implementations to use for the search engine. I started with thresholds of 0.75 on both maps because this is the stand
value backed up by empirical research. However, I experimented with one of 0.5 to see the effect, and it made my maps
slower and use much more space. Thus, I went for only a slightly lower value, 0.7 and found that this used, on average,
less space and less time than 0.75 for open addressing but not for chaining. Moreover, across all these values the 
OpenAddressing map performed better than the Chaining map. I believe this is because I used a linked list as the 
auxiliary data structures, leading to a slower search within each bucket. I thought about using a binary search tree but 
the keys are not necessarily comparable. As for the OpenAddressingHashMap I employed a linear probing strategy because 
it was easier to use with a wraparound array than quadratic; the latter was a failed attempt on my part. Hence, I choose
the open addressing map with a load factor of 0.7. All of the above is backed up by the data below. I was surprised at 
first that open addressing worked better than chaining because I thought the former would have more collisions, but I
suppose because of the rehashing the operations can stay closers to the constant time operations while storing data in
the same buckets leads to slower searches.

Data:

OpenAddressing 0.75

JmhRuntimeTest.buildSearchEngine                                          apache.txt  avgt    2         116.168           ms/op
JmhRuntimeTest.buildSearchEngine                                             jhu.txt  avgt    2           0.143           ms/op
JmhRuntimeTest.buildSearchEngine                                          joanne.txt  avgt    2           0.050           ms/op
JmhRuntimeTest.buildSearchEngine                                          newegg.txt  avgt    2          64.759           ms/op
JmhRuntimeTest.buildSearchEngine                                       random164.txt  avgt    2         440.440           ms/op
JmhRuntimeTest.buildSearchEngine                                            urls.txt  avgt    2           0.016           ms/op

JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   133894632.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    26585080.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    27501716.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    94589916.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  1571748628.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    25201044.000           bytes

Chaining 0.75

JmhRuntimeTest.buildSearchEngine                                          apache.txt  avgt    2         128.811           ms/op
JmhRuntimeTest.buildSearchEngine                                             jhu.txt  avgt    2           0.154           ms/op
JmhRuntimeTest.buildSearchEngine                                          joanne.txt  avgt    2           0.059           ms/op
JmhRuntimeTest.buildSearchEngine                                          newegg.txt  avgt    2          70.858           ms/op
JmhRuntimeTest.buildSearchEngine                                       random164.txt  avgt    2         556.623           ms/op
JmhRuntimeTest.buildSearchEngine                                            urls.txt  avgt    2           0.016           ms/op

JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   176031880.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    27436084.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    27611352.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    90016340.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  1402293992.000           bytes
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    25012568.000           bytes

OpeAddressing 0.5

JmhRuntimeTest.buildSearchEngine                                          apache.txt  avgt    2         119.148           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   135017292.000           bytes
JmhRuntimeTest.buildSearchEngine                                             jhu.txt  avgt    2           0.140           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    27364036.000           bytes
JmhRuntimeTest.buildSearchEngine                                          joanne.txt  avgt    2           0.054           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    27317788.000           bytes
JmhRuntimeTest.buildSearchEngine                                          newegg.txt  avgt    2          61.055           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    93957796.000           bytes
JmhRuntimeTest.buildSearchEngine                                       random164.txt  avgt    2         389.657           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2   943358392.000           bytes
JmhRuntimeTest.buildSearchEngine                                            urls.txt  avgt    2           0.016           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    25132828.000           bytes

Chaining 0.5

JmhRuntimeTest.buildSearchEngine                                          apache.txt  avgt    2         137.260           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   175728032.000           bytes
JmhRuntimeTest.buildSearchEngine                                             jhu.txt  avgt    2           0.139           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    27794524.000           bytes
JmhRuntimeTest.buildSearchEngine                                          joanne.txt  avgt    2           0.058           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    27608664.000           bytes
JmhRuntimeTest.buildSearchEngine                                          newegg.txt  avgt    2          68.865           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    93104184.000           bytes
JmhRuntimeTest.buildSearchEngine                                       random164.txt  avgt    2         526.824           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  1613358392.000           bytes
JmhRuntimeTest.buildSearchEngine                                            urls.txt  avgt    2           0.016           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    25035140.000           bytes

OpenAddressing 0.7

JmhRuntimeTest.buildSearchEngine                                          apache.txt  avgt    2         113.959           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   132064816.000           bytes
JmhRuntimeTest.buildSearchEngine                                             jhu.txt  avgt    2           0.129           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    27513020.000           bytes
JmhRuntimeTest.buildSearchEngine                                          joanne.txt  avgt    2           0.054           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    27279724.000           bytes
JmhRuntimeTest.buildSearchEngine                                          newegg.txt  avgt    2          60.460           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    92665672.000           bytes
JmhRuntimeTest.buildSearchEngine                                       random164.txt  avgt    2         398.150           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  1662976432.000           bytes
JmhRuntimeTest.buildSearchEngine                                            urls.txt  avgt    2           0.016           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    25177700.000           bytes

Chaining 0.7

JmhRuntimeTest.buildSearchEngine                                          apache.txt  avgt    2         134.859           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               apache.txt  avgt    2   175365636.000           bytes
JmhRuntimeTest.buildSearchEngine                                             jhu.txt  avgt    2           0.158           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                  jhu.txt  avgt    2    27330060.000           bytes
JmhRuntimeTest.buildSearchEngine                                          joanne.txt  avgt    2           0.062           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               joanne.txt  avgt    2    27477760.000           bytes
JmhRuntimeTest.buildSearchEngine                                          newegg.txt  avgt    2          71.615           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc               newegg.txt  avgt    2    97644484.000           bytes
JmhRuntimeTest.buildSearchEngine                                       random164.txt  avgt    2         525.887           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            random164.txt  avgt    2  1671384224.000           bytes
JmhRuntimeTest.buildSearchEngine                                            urls.txt  avgt    2           0.016           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc                 urls.txt  avgt    2    25000764.000           bytes

