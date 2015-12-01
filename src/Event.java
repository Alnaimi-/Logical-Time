import java.util.AbstractMap;

public class Event {
    boolean isPair;
    AbstractMap.SimpleEntry<Integer, Integer> belongsTo;
    AbstractMap.SimpleEntry<Integer, Integer> pairWith;

    public Event(int process, int eventNo) {
        belongsTo = new AbstractMap.SimpleEntry<Integer, Integer>(process, eventNo);
        isPair = false;
        pairWith = null;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> getBelongsTo() {
        return this.belongsTo;
    }

    public void setPair(AbstractMap.SimpleEntry pairWith) throws HasPairException {
        if(this.hasPair()) {
            throw new HasPairException("Process: " + belongsTo.getValue() +
                    " Event: " + belongsTo.getKey() + ", already has a pair!");
        }
        this.isPair = true;
        this.pairWith = pairWith;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> getPairWith() {
        return this.pairWith;
    }

    public boolean hasPair() {
        return this.isPair;
    }
}
