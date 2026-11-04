# Week 10 - Hash Collision Race

## What you do
Implement `linear`, `quadratic`, and `bucket` in **`HashRace.java`**. It is the only file you edit. The header
comments describe the parameters, the hash function (`key % tableSize`), what counts as a collision, and the
return value `long[]{inserted, collisions}`.

Remember to call `progress.update(inserted, collisions)` every `PROGRESS_EVERY` inserts (and once at the end),
or your line won't move.

Provided (do not edit): `Datasets.java`, `ProgressListener.java`, `HashRaceVisualizer.java`.

## How to run
**VS Code:** open `HashRaceVisualizer.java` and click **Run** above `main` (or press F5).

**Command line** (from the `InClassNotes` folder; needs a JDK on your PATH):

    javac -d target\classes src\main\java\week10\*.java
    java -cp target\classes week10.HashRaceVisualizer

Pick the number of keys in the dropdown and press **Start race**. Big sizes with collisions can take a minute
or more, so start with 10,000 or 30,000.

## What you will see
Three datasets run one after another; the three strategies race at the same time inside each one:

1. A collision on every insert (all keys hash to index 0)
2. As few collisions as possible (every key gets its own index)
3. Half collide, half don't

For each dataset:
- **Top chart** - keys inserted vs. time.
- **Bottom chart** - collisions per insert as the table fills. Which lines stay flat and which climb?
- **Estimated memory** - bars for each strategy. Open addressing allocates the whole table up front;
  buckets grow by one node per key.

All charts share the same scales so the datasets can be compared directly.

## Think about
- Which strategy wins on each dataset, and why?
- Why is bucket insertion so cheap when everything collides?
- Buckets are faster here, so what do they cost you?
- Why does quadratic beat linear on dataset 3 but not on dataset 1?
