# Visualizing `ArrayBoundedStack`

Open this file with **Ctrl+Shift+V** (Markdown: Open Preview). VS Code's built-in
preview renders Mermaid diagrams natively.

## 1. What the array + `topIndex` actually look like

A stack backed by an array only needs one piece of state: `topIndex`, the index
of the most recently pushed element. Everything (`isEmpty`, `isFull`, `push`,
`pop`, `top`) is answered by comparing `topIndex` to the array bounds.

| index      | 0   | 1   | 2   | 3     | ... | N-1   |
|------------|-----|-----|-----|-------|-----|-------|
| `elements` | 'A' | 'B' | 'C' | empty | ... | empty |
|            |     |     | ^ `topIndex` |     |       |

After pushing `'A'`, `'B'`, `'C'` (in that order) onto a stack of capacity `N`,
`topIndex == 2` it points at `'C'`, the last thing pushed.

```mermaid
graph LR
    A["elements[0] = 'A'"] --> B["elements[1] = 'B'"] --> C["elements[2] = 'C'"]
    C -.->|topIndex points here| C
```

## 2. Push / Pop as index movement

```mermaid
sequenceDiagram
    participant Client
    participant Stack as ArrayBoundedStack
    Note over Stack: topIndex = -1 (empty)
    Client->>Stack: push('A')
    Note over Stack: topIndex = 0, elements[0] = 'A'
    Client->>Stack: push('B')
    Note over Stack: topIndex = 1, elements[1] = 'B'
    Client->>Stack: top()
    Stack-->>Client: 'B'
    Client->>Stack: pop()
    Note over Stack: topIndex = 0 (elements[1] still holds 'B', but it's "gone" logically)
    Client->>Stack: top()
    Stack-->>Client: 'A'
```

**Key insight:** `pop()` doesn't need to erase the value, decrementing
`topIndex` is enough to logically remove it. The next `push` will just
overwrite that slot.

## 3. `isEmpty` / `isFull` as boundary conditions

```mermaid
stateDiagram-v2
    [*] --> Empty: topIndex = -1
    Empty --> HasElements: push()
    HasElements --> HasElements: push() / pop()
    HasElements --> Empty: pop() (topIndex back to -1)
    HasElements --> Full: push() when topIndex = elements.length - 1
    Full --> HasElements: pop()

    note right of Empty
        isEmpty() true when topIndex == -1
    end note
    note right of Full
        isFull() true when topIndex == elements.length - 1
    end note
```

## 4. Decision logic for each method

```mermaid
flowchart TD
    subgraph push["push(element)"]
        P1{isFull?} -- yes --> P2[reject or throw / do nothing]
        P1 -- no --> P3[topIndex++]
        P3 --> P4["elements[topIndex] = element"]
    end
```

```mermaid
flowchart TD
    subgraph pop["pop()"]
        Q1{isEmpty?} -- yes --> Q2[nothing to remove]
        Q1 -- no --> Q3[topIndex--]
    end
```

```mermaid
flowchart TD
    subgraph top["top()"]
        T1{isEmpty?} -- yes --> T2[return null / no valid top]
        T1 -- no --> T3["return elements[topIndex]"]
    end
```

## 5. Questions to check your understanding

1. What is `topIndex` when the stack is brand new?
2. If `elements.length == 5`, what value of `topIndex` means the stack is full?
3. Why can `pop()` be implemented as "just move the index" instead of clearing
   the array slot?
4. What should `isEmpty()` return right after construction, and right after a
   single `push` followed by a single `pop`?
