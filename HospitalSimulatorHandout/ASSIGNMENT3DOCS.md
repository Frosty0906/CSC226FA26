# BIG-O ANALYSIS

    Operation                     Big-O   Why
    ------------------------------------------------------------------------------------------------------------------
    queue enqueue/dequeue         O(1)    enqueue adds at rear and dequeue removes at front, so it never walks the list
    nextPatient()                 O(1)    checks level1 -> level2 -> level3 -> level4 and dequeues the first non-empty one, always at most 4 checks no matter how many patients are waiting
    stack push/pop/peek           O(1)    push, pop, and peek all just use the top node, nothing below it gets touched
    recursive queue toString()    O(n)    one recursive call per node from front to rear until it hits null


# EXTENSION: LINKED QUEUE VS CIRCULAR ARRAY QUEUE

    Operation           LinkedQueue   ArrayQueue         Why
    ------------------------------------------------------------------------------------------------------------------
    enqueue             O(1)          O(1)               linked just adds a node at rear, array is O(1) until it's full and has to copy everything into a bigger array (O(n) that one time)
    dequeue             O(1)          O(1)               linked moves front to front.next, array moves the front index forward with % so nothing shifts
    peekFront           O(1)          O(1)               both just look at the front
    toString            O(n)          O(n)               both visit every item once

    Tradeoffs
    - memory: linked uses an extra next reference for every node, array has no per-item overhead but can have empty unused slots
    - growth: linked never has to resize, array has to copy everything when it fills up
    - cache: array items sit next to each other in memory so it's usually faster in practice, linked nodes can be spread out anywhere
    - simplicity: linked is easier to get right, array needs the % wrap-around math and the resize has to unwrap the items
