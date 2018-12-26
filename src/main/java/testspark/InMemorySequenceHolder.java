package testspark;

public class InMemorySequenceHolder implements SequenceHolder {
    private int last = 0;
    @Override
    public int getLast() {
        return last;
    }

    @Override
    public int updateLast(int last) {
        this.last = last;

        return this.last;
    }
}
