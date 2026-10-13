# BIG-O ANALYSIS

    Operation                     Big-O   Why
    ------------------------------------------------------------------------------------------------------------------
    queue enqueue/dequeue         O(1)    enqueue adds at rear and dequeue removes at front, so it never walks the list
    nextPatient()                 O(1)    checks level1 -> level2 -> level3 -> level4 and dequeues the first non-empty one, always at most 4 checks no matter how many patients are waiting
    stack push/pop/peek           O(1)    push, pop, and peek all just use the top node, nothing below it gets touched
    recursive queue toString()    O(n)    one recursive call per node from front to rear until it hits null
