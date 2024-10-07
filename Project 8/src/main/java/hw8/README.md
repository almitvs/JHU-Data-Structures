# Homework 8

## Discussion 


Data summary:

JHU to Druid Lake used on average 11450 KB instantiating the graph, 29600 KB finding the shortest path, and took
about a 100 milliseconds to load the network and 10 milliseconds to find the shortest path.

7-11 to Druid Lake used on average 11475 KB instantiating the graph, 29500 KB finding the shortest path, and took
about a 105 milliseconds to load the network and 6 milliseconds to find the shortest path.

Inner Harbor to JHU used on average 11470 KB instantiating the graph, 29900 KB finding the shortest path, and took
about a 105 milliseconds to load the network and 23 milliseconds to find the shortest path.



Discussion:

The variation between the memory used to instantiate the graphs among the different journeys is small as expected 
because they all draw from the same datasets. JHU to Druid Lake and 7-11 to Druid Lake had very similar memory
usages for finding the shortest path whereas Inner Harbor to JHU's was slightly more. This is to be expected as the
latter was a longer search. This is reflected by Inner Harbor to JHU's longer time taken to find the shortest path
compared to 7-11 to Druid Lake and JHU to Druid Lake. JHU to Druid Lake was longer than I expected than 7-11 to Druid
Lake, thinking about the distances between those places in real life. However, I realized that the distance does not
really matter; what caused the difference in times was the surplus of vertices involved with starting at JHU compared
to 7-11. When I ran the JmhRuntimeTest on these three journeys I found similar differences between their performances.
Using hash tables to implement the sparse graph by storing its edges, nodes, and incidence lists contributed to the
efficiency of my implementation because the amortized constant runtime provided fast searches. Using an array list as
I initially did was less efficient due to the reliance on linear search. Similarly, hash tables were useful in
implementing Dijkstra's algorithm. Using a priority queue in the latter to store the vertices to be examined was also
beneficial because I made it self-sort based on distances, so finding the node with the highest priority was constant.



Data:

JHU to Druid Lake
Starting location: -76.6175,39.3296
Ending location: -76.6383,39.3206

Driver output:

Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Network Loaded: 27598 roads, 9024 endpoints
Total Distance: 8818.5187
121.60 	45662:
137.15 	40816:
318.90 	40867:N_CHARLES_ST
60.49 	42002:E_33RD_ST
293.14 	8344:3200_BLK_N_CHARLES_ST
318.96 	11147:3200_BLK_N_CHARLES_ST
151.62 	39907:ART_MUSEUM_DR
665.00 	48094:UNIT__BLK_ART_MUSEUM_DR
129.55 	43910:ART_MUSEUM_DR
213.64 	46364:WYMAN_PARK_DR
255.02 	26692:2900_BLK_WYMAN_PARK_DR
42.03 	39554:N_HOWARD_ST
136.52 	26872:200_BLK_W_29TH_ST
146.67 	26712:200_BLK_W_29TH_ST
167.43 	15177:200_BLK_W_29TH_ST
230.86 	11871:200_BLK_W_29TH_ST
196.68 	14691:300_BLK_W_29TH_ST
224.61 	30101:300_BLK_W_29TH_ST
123.84 	5917:300_BLK_W_29TH_ST
79.80 	21125:300_BLK_W_29TH_ST
78.16 	21194:400_BLK_W_29TH_ST
115.90 	17656:400_BLK_W_29TH_ST
600.37 	26121:500_BLK_W_29TH_ST
480.41 	14609:2900_BLK_SISSON_ST
284.62 	23569:700_BLK_WYMAN_PARK_DR
394.12 	18109:800_BLK_WYMAN_PARK_DR
281.96 	31600:900_BLK_WYMAN_PARK_DR
39.03 	33121:900_BLK_WYMAN_PARK_DR
71.18 	34391:1000_BLK_WYMAN_PARK
1160.95 	41471:EAST_DR
190.65 	43386:UNNAMED_ST
1107.65 	41640:

MemoryMonitorTest 1:

Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Used memory: 11469.45 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11704.02 KB (Δ = 234.578)
Loading the network
Used memory: 29359.34 KB (Δ = 17655.313)
Finding the shortest path
Used memory: 29600.93 KB (Δ = 241.594)
Setting objects to null (so GC does its thing!)
Used memory: 13643.95 KB (Δ = -15956.977)

MemoryMonitorTest 2:

Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Used memory: 11450.05 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11673.90 KB (Δ = 223.844)
Loading the network
Used memory: 29367.93 KB (Δ = 17694.031)
Finding the shortest path
Used memory: 29610.90 KB (Δ = 242.969)
Setting objects to null (so GC does its thing!)
Used memory: 13635.37 KB (Δ = -15975.531)

MemoryMonitorTest 3:

Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Used memory: 11454.98 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11674.69 KB (Δ = 219.711)
Loading the network
Used memory: 29365.23 KB (Δ = 17690.539)
Finding the shortest path
Used memory: 29605.23 KB (Δ = 240.000)
Setting objects to null (so GC does its thing!)
Used memory: 13622.34 KB (Δ = -15982.883)

SystemRuntimeTest 1:

Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Loading network took 106 milliseconds.
Finding shortest path took 10 milliseconds.

SystemRuntimeTest 2:

Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Loading network took 106 milliseconds.
Finding shortest path took 9 milliseconds.

SystemRuntimeTest 3:

Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Loading network took 95 milliseconds.
Finding shortest path took 11 milliseconds.



7-11 to Druid Lake
Starting location: -76.6214,39.3212
Ending location: -76.6383,39.3206

Driver output:

Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Network Loaded: 27598 roads, 9024 endpoints
Total Distance: 5827.3652
397.42 	24509:2800_BLK_REMINGTON_AVE
196.68 	14691:300_BLK_W_29TH_ST
224.61 	30101:300_BLK_W_29TH_ST
123.84 	5917:300_BLK_W_29TH_ST
79.80 	21125:300_BLK_W_29TH_ST
78.16 	21194:400_BLK_W_29TH_ST
115.90 	17656:400_BLK_W_29TH_ST
600.37 	26121:500_BLK_W_29TH_ST
480.41 	14609:2900_BLK_SISSON_ST
284.62 	23569:700_BLK_WYMAN_PARK_DR
394.12 	18109:800_BLK_WYMAN_PARK_DR
281.96 	31600:900_BLK_WYMAN_PARK_DR
39.03 	33121:900_BLK_WYMAN_PARK_DR
71.18 	34391:1000_BLK_WYMAN_PARK
1160.95 	41471:EAST_DR
190.65 	43386:UNNAMED_ST
1107.65 	41640:

MemoryMonitorTest 1:

Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Used memory: 11486.46 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11720.70 KB (Δ = 234.242)
Loading the network
Used memory: 29368.86 KB (Δ = 17648.156)
Finding the shortest path
Used memory: 29536.09 KB (Δ = 167.234)
Setting objects to null (so GC does its thing!)
Used memory: 13632.41 KB (Δ = -15903.688)

MemoryMonitorTest 2:

Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Used memory: 11479.43 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11706.17 KB (Δ = 226.742)
Loading the network
Used memory: 29324.83 KB (Δ = 17618.656)
Finding the shortest path
Used memory: 29491.56 KB (Δ = 166.734)
Setting objects to null (so GC does its thing!)
Used memory: 13619.88 KB (Δ = -15871.680)

MemoryMonitorTest 3:

Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Used memory: 11468.05 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11693.83 KB (Δ = 225.781)
Loading the network
Used memory: 29333.37 KB (Δ = 17639.539)
Finding the shortest path
Used memory: 29497.03 KB (Δ = 163.664)
Setting objects to null (so GC does its thing!)
Used memory: 13621.55 KB (Δ = -15875.477)

SystemRuntimeTest 1:

Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Loading network took 102 milliseconds.
Finding shortest path took 7 milliseconds.

SystemRuntimeTest 2:

Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Loading network took 112 milliseconds.
Finding shortest path took 6 milliseconds.

SystemRuntimeTest 3:

Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Loading network took 111 milliseconds.
Finding shortest path took 6 milliseconds.



Inner Harbor to JHU
Starting location: -76.6107,39.2866
Ending location: -76.6175,39.3296

Driver output:

Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Network Loaded: 27598 roads, 9024 endpoints
Total Distance: 16570.4909
462.64 	20226:100_BLK_SOUTH_ST
163.04 	48137:UNIT__BLK_SOUTH_ST
71.81 	47386:UNIT__BLK_SOUTH_ST
158.76 	47419:UNIT__BLK_SOUTH_ST
271.10 	47548:UNIT__BLK_SOUTH_ST
268.59 	47459:UNIT__BLK_GUILFORD_AVE
343.86 	28621:200_BLK_E_FAYETTE_ST
296.82 	33813:100_BLK_N_CALVERT_ST
134.19 	28959:200_BLK_N_CALVERT_ST
299.37 	24432:200_BLK_N_CALVERT_ST
454.21 	23235:300_BLK_N_CALVERT_ST
147.99 	9313:300_BLK_N_CALVERT_ST
185.36 	5947:400_BLK_N_CALVERT_ST
151.15 	30373:100_BLK_ORLEANS_ST
156.75 	31819:100_BLK_ORLEANS_ST
165.18 	36462:400_BLK_SAINT_PAUL_PL
199.96 	33237:500_BLK_SAINT_PAUL_PL
50.14 	42343:SAINT_PAUL_PL
204.02 	35069:500_BLK_SAINT_PAUL_PL
369.08 	27667:600_BLK_SAINT_PAUL_ST
67.58 	23282:600_BLK_SAINT_PAUL_ST
63.19 	8359:700_BLK_SAINT_PAUL_ST
201.99 	31442:700_BLK_SAINT_PAUL_ST
120.04 	22312:700_BLK_SAINT_PAUL_ST
164.64 	24117:800_BLK_SAINT_PAUL_ST
58.65 	2462:800_BLK_SAINT_PAUL_ST
163.17 	14582:800_BLK_SAINT_PAUL_ST
347.58 	16051:900_BLK_SAINT_PAUL_ST
147.31 	17769:900_BLK_SAINT_PAUL_ST
159.48 	34028:1000_BLK_SAINT_PAUL_ST
341.45 	8766:1000_BLK_SAINT_PAUL_ST
383.17 	1691:1100_BLK_SAINT_PAUL_ST
386.86 	4356:1200_BLK_SAINT_PAUL_ST
338.91 	19656:1300_BLK_SAINT_PAUL_ST
42.19 	40957:SAINT_PAUL_ST
46.30 	44623:SAINT_PAUL_ST
216.46 	39136:RAMP
338.93 	43462:I_83___S
92.13 	45367:I_83___S
107.28 	40942:N_CHARLES_ST
123.62 	40898:N_CHARLES_ST
468.44 	13325:1500_BLK_N_CHARLES_ST
386.86 	4876:1700_BLK_N_CHARLES_ST
212.37 	22851:1800_BLK_N_CHARLES_ST
244.03 	8607:1800_BLK_N_CHARLES_ST
46.58 	20118:1800_BLK_N_CHARLES_ST
382.67 	19448:1900_BLK_N_CHARLES_ST
366.72 	11838:2000_BLK_N_CHARLES_ST
367.72 	1734:2100_BLK_N_CHARLES_ST
376.14 	26449:2200_BLK_N_CHARLES_ST
393.44 	5511:2300_BLK_N_CHARLES_ST
239.67 	18280:2400_BLK_N_CHARLES_ST
99.97 	2083:2400_BLK_N_CHARLES_ST
204.48 	30800:2400_BLK_N_CHARLES_ST
209.45 	28376:2500_BLK_N_CHARLES_ST
75.71 	10673:2500_BLK_N_CHARLES_ST
102.26 	18381:2500_BLK_N_CHARLES_ST
165.69 	25136:2500_BLK_N_CHARLES_ST
467.97 	8403:2600_BLK_N_CHARLES_ST
467.02 	21531:2700_BLK_N_CHARLES_ST
465.66 	21565:2800_BLK_N_CHARLES_ST
231.86 	42406:N_CHARLES_ST
166.74 	41710:N_CHARLES_ST
64.04 	39032:N_CHARLES_ST
240.14 	41330:N_CHARLES_ST
233.97 	44412:N_CHARLES_ST
195.68 	44001:N_CHARLES_ST
205.02 	44656:N_CHARLES_ST
202.15 	42144:N_CHARLES_ST
245.47 	42334:N_CHARLES_ST
318.90 	40867:N_CHARLES_ST
137.15 	40816:
121.60 	45662:

MemoryMonitorTest 1:

Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Used memory: 11469.07 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11706.49 KB (Δ = 237.422)
Loading the network
Used memory: 29374.40 KB (Δ = 17667.906)
Finding the shortest path
Used memory: 29906.73 KB (Δ = 532.328)
Setting objects to null (so GC does its thing!)
Used memory: 13675.70 KB (Δ = -16231.023)

MemoryMonitorTest 2:

Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Used memory: 11469.98 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11703.55 KB (Δ = 233.570)
Loading the network
Used memory: 29360.90 KB (Δ = 17657.352)
Finding the shortest path
Used memory: 29891.46 KB (Δ = 530.563)
Setting objects to null (so GC does its thing!)
Used memory: 13669.40 KB (Δ = -16222.063)

MemoryMonitorTest 3:

Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Used memory: 11470.42 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
Used memory: 11694.55 KB (Δ = 224.125)
Loading the network
Used memory: 29374.63 KB (Δ = 17680.078)
Finding the shortest path
Used memory: 29909.93 KB (Δ = 535.305)
Setting objects to null (so GC does its thing!)
Used memory: 13652.59 KB (Δ = -16257.344)

SystemRuntimeTest 1:

Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Loading network took 104 milliseconds.
Finding shortest path took 23 milliseconds.

SystemRuntimeTest 2:

Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Loading network took 113 milliseconds.
Finding shortest path took 24 milliseconds.

SystemRuntimeTest 3:

Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Loading network took 105 milliseconds.
Finding shortest path took 23 milliseconds.