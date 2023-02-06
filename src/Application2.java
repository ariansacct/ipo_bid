import java.util.*;

public class Application2 {

    List<Integer> getUnallocatedUsers(List<Bid> bids, int totalShares) {
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

//        for (double nextHighPrice : reverseSortedMap.keySet()) {
//            List<Bid> l = reverseSortedMap.get(nextHighPrice);
//            for (Bid nextBidInTime : l) {
//                int sharesRequested = nextBidInTime.numShares;
//                if (sharesRequested <= totalAvailable) {
//
//                }
//            }
//        }

        Map<Double, Integer> countMap = new HashMap<>();
        for (Bid bid : bids) {
            double bidPrice = bid.bidPrice;
            countMap.put(bidPrice, countMap.getOrDefault(bidPrice, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();

        

        return result;
    }

    public static void main(String[] args) {
        // Bid(int uid, int numShares, double bidPrice, int timestamp)
        Bid b1 = new Bid(1, 100, 500, 1);
        Bid b2 = new Bid(2, 90, 500, 2);
        Bid b3 = new Bid(5, 120, 500, 3);
        Bid b4 = new Bid(4, 10, 450, 1);
        Bid b5 = new Bid(3, 15, 450, 3);

        List<Bid> bids = Arrays.asList(b1, b4, b2, b3, b5);
        Application2 application2 = new Application2();
        List<Integer> kir = application2.getUnallocatedUsers(bids, 1000);
        System.out.println("siktir");
    }
}
