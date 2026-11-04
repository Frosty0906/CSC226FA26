package week10;

/** Provided. Your hash functions report their progress through this so the visualizer can draw it live. */
public interface ProgressListener {
    /**
     * @param inserted   how many keys have been stored so far
     * @param collisions how many collisions have happened so far (see HashRace for what counts)
     */
    void update(long inserted, long collisions);
}
