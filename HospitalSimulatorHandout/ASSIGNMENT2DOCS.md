# TIMING RESULTS

## Linear Search
### 100 - 10510 ns
### 1,000 - 41357 ns
### 10,000 - 612632 ns
### 100,000 - 17227504 ns

## Binary Search
### 100 - 7253 ns
### 1,000 - 3938 ns
### 10,000 - 5350 ns
### 100,000 - 10610 ns

## Alt O(log n) Search
### 100 - 8135 ns
### 1,000 - 4078 ns
### 10,000 - 6563 ns
### 100,000 - 13576 ns

# QUESTIONS

## As the dataset grows by 10x each time, how does each algorithm's time change on average?
Linear search grew a lot each time the dataset got bigger, going from around 10500ns at 100 to about 17.2mil at 100000. It didn't jump exactly 10x each step but it clearly scales with the size of the array, which lines up with it being O(n). Binary and exponential barely changed compared to linear. Binary went from 7253ns to 10610ns across that whole range even though the dataset got a thousand times bigger. At 100000 patients linear was over a thousand times slower than binary. 

## Carroll Memorial Hospital sees about 50,000 patients per year. Which search algorithm would you recommend, and why?
I would probably go with a binary search. With 50,000 patients, linear search would have to scan about 25k records on average to find a match because on average the target sits halfway through. Binary search only needs about 16 comparisons because log2(50000) is about 16. That gap only gets bigger the more patients you add. Exponential performs about the same but it's more code. I think it might be better fit for cases where you don't know the array size ahead of time because binary needs the start and end index. The only real downside of the binary is that the data needs to be sorted first. I do not think a real ER system would use the sorting I implemented because real ER's get constant patients, and there is no way mine is the most efficient way. 


## Is measuring time in nanoseconds this way actually the best way to assess algorithmic efficiency? Why or why not?
I don't think so. The searches finish so fast at these sizes that I assume because it's a small measurement is mostly noise from my machine/hardware. Big O is the better tool because like we talked about in class it tells you how an algorithm can scale no matter what hardware it's on. Timing is still useful as like a sanity check I think because you can check your implementation and that things behave the way they should. 

## Exponential Algorithm Citations
I learned about it from this helpful github repo https://github.com/MrtitaniumJ/Java-basic-to-advance/tree/main/04-Collections-and-DSA/03-Algorithms/01-Searching/05-Exponential-Search. The array is sorted so once doubling overshoots, the target can only be between the previous bound and the current bound.
