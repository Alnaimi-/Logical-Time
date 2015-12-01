import java.util.ArrayList;
import java.util.Random;

public class Main {

    static ArrayList<ArrayList<Event>> events;

    public static void main(String[] args) throws HasPairException {
        run(3, 300, 100);
    }

    public static void run(int pCount, int eCount, int cCount) throws HasPairException {
        int communications = 0;
        Random rand = new Random();
        events = new ArrayList<ArrayList<Event>>(pCount);

        for(int i = 0; i < pCount; i++) {
            events.add(new ArrayList<Event>());
        }

        for(int j = 0; j < eCount; j++) {
            int random = rand.nextInt(pCount);

            boolean isCommunication = rand.nextBoolean();
            if((j+1 != eCount) && isCommunication && cCount != communications) {
                int random2 = rand.nextInt(pCount);
                while(random == random2) {
                    random2 = rand.nextInt(pCount);
                }

                Event temp = new Event(random, events.get(random).size());
                Event temp2 = new Event(random2, events.get(random2).size());

                //System.out.println("Created a pair: " + temp.getBelongsTo() + " with " + temp2.getBelongsTo());
                temp.setPair(temp2.getBelongsTo());

                events.get(random).add(temp);
                events.get(random2).add(temp2);
                communications++;
                j++;
            } else {
                events.get(random).add(new Event(random, events.get(random).size()));
            }
        }

        System.out.println(eCount + " events have been created, of which " + communications + " is a communication.");

        ArrayList<Integer> pairs = new ArrayList<Integer>(pCount);

        for(int i = 0; i < pCount; i++) {
            pairs.add((events.get(i).size() * (events.get(i).size() - 1)) / 2);
        }

        for(int i = 0; i < pCount; i++) {
            System.out.print(events.get(i).size() + " ");
        }
        System.out.println();

        for(int j = 0; j < pCount; j++) {
            ArrayList<Event> temp = events.get(j);
            for (Event event : temp) {
                if (event.isPair) {
                    int startLink, endLink;
                    // going down
                    startLink = event.getBelongsTo().getValue() + 1;
                    endLink = events.get(event.getPairWith().getKey()).size() - event.getPairWith().getValue();

                    int linkSize = startLink + endLink;
                    int linkedPairs = linkSize-1;
                    pairs.set(j, pairs.get(j) + linkedPairs);

                    System.out.println(event.getBelongsTo() + " Has linkSize: " + linkSize + " with " + event.getPairWith());


                    System.out.println("Found a pair at process: " + event.belongsTo.getKey() + " and position " + event.belongsTo.getValue()
                     + " it is a pair with process: " + event.pairWith.getKey() + " at position " + event.pairWith.getValue());
                }
            }
        }

        double totalPairs = 0;
        for(int i = 0; i < pCount; i++) {
            totalPairs += pairs.get(i);
        }

        int nonPairs = (eCount * (eCount - 1)) / 2;
        nonPairs -= totalPairs;

        System.out.println("There are " + totalPairs + " total pairs.");
        System.out.println("Where the ratio is: " + totalPairs/(totalPairs + nonPairs) + ".");
    }
}