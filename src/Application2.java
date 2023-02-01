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

        for (double nextHighPrice : reverseSortedMap.keySet()) {
            List<Bid> l = reverseSortedMap.get(nextHighPrice);
            for (Bid nextBidInTime : l) {
                int sharesRequested = nextBidInTime.numShares;
                if (sharesRequested <= totalAvailable) {

                }
            }
        }

        for (Bid bid : bids) {

        }

        List<Integer> result = new ArrayList<>();
        return result;
    }
}
