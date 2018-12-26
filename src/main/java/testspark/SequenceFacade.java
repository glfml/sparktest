package testspark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequenceFacade {

    List<SequenceGenerator> l = new ArrayList();

    public int create() {
        SequenceGenerator g = new SequenceGenerator() {
            SequenceHolder holder = new InMemorySequenceHolder();
            @Override
            public int generateNext() {
                int last = holder.getLast();
                holder.updateLast(last + 1);
                return last + 1;
            }
        };

        l.add(g);

        return l.size() - 1;
    }

    public int getNext(String name) throws InvalidSequenceException {
        int index  = Integer.valueOf(name);
        if (l.size() - 1 < index) {
            throw new InvalidSequenceException("Invalid sequence");
        }
        return l.get(index).generateNext();
    }
}
