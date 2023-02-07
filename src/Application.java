import java.util.*;

public class Application {

    private void terminateProgram() {
        System.out.println("Let's assume we terminated. Anything after this point should be ignored.");
    }

    Set<Integer> getUnallocatedUsers(List<Bid> bids, int totalShares) {
        // first sort by price desc
        // within the same price, sort by timestamp asc
        Comparator<Bid> customComparator = new Comparator<Bid>() {
            @Override
            public int compare(Bid b1, Bid b2) {
                if (b1.bidPrice > b2.bidPrice) {
                    return -1;
                }
                if (b1.bidPrice < b2.bidPrice) {
                    return 1;
                }
                return Integer.compare(b1.timestamp, b2.timestamp);
            }
        };
        bids.sort(customComparator);

        // start the processing
        // have a set of all the users - as you process their orders, remove them from the set of all

        // for each one in the highest price, give them what they have asked
        int totalAvailable = totalShares;
        // we need to get the highest highest price; ie, the first row in the map


        Set<Integer> allUserIDs = new HashSet<>();
        Map<Integer, Integer> needs = new HashMap<>();


        Map<Double, Integer> countMap = new HashMap<>();
        for (Bid bid : bids) {
            double bidPrice = bid.bidPrice;
            int uid = bid.uid;
            countMap.put(bidPrice, countMap.getOrDefault(bidPrice, 0) + 1);
            allUserIDs.add(uid);
            needs.put(uid, bid.numShares);
        }

        Set<Integer> answer = new HashSet<>(allUserIDs);
        int i = 0, serviced = 0;
        while (i < bids.size()) {
            Bid b = bids.get(i);
            double bidPrice = b.bidPrice;
            int cnt = countMap.get(bidPrice);
            int j = -1;
            for (j = i; j < i + cnt; j++) {
                if (totalAvailable > 0) {
                    int uid = bids.get(j).uid;
                    int updatedNeed = needs.get(uid) - 1;
                    needs.put(uid, updatedNeed);
                    if (updatedNeed >= 0) {
                        totalAvailable--;
                        answer.remove(uid);
                        if (updatedNeed == 0) {
                            serviced++;
                        }
                    }
                }
                else {
//                    terminateProgram();    // get out of all for loops and return what 'answer' is at this point
                    return answer;
                }

            }

            if (serviced < cnt) {
                j = i;
            }
            else {
                i = i + cnt;
                serviced = 0;
            }


        }





        return answer;
    }

    public static void main(String[] args) {
        // Bid(int uid, int numShares, double bidPrice, int timestamp)
        Bid b1 = new Bid(1, 100, 500, 1);
        Bid b2 = new Bid(2, 90, 500, 2);
        Bid b5 = new Bid(5, 120, 500, 3);
        Bid b4 = new Bid(4, 10, 450, 1);
        Bid b3 = new Bid(3, 15, 450, 3);

//        List<Bid> bids = Arrays.asList(b1, b2, b3, b4, b5);
        List<Bid> bids = Arrays.asList(b1, b4, b3);
        Application application = new Application();
//        Set<Integer> set = application.getUnallocatedUsers(bids, 1000);
//        Set<Integer> set = application.getUnallocatedUsers(bids, 100);
        Set<Integer> set = application.getUnallocatedUsers(bids, 10);
        System.out.println(set);
    }
}