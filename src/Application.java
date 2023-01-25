import java.util.*;

public class Application {

    List<Integer> getUnallocatedUsers(List<Bid> bids, int totalShares) {
        // first sort by price desc
        // within the same price, sort by timestamp asc

        Map<Double, Bid> bidMap = new HashMap<>();
        for (Bid bid : bids) {
            bidMap.put(bid.bidPrice, bid);
        }

        Comparator<Double> customComparator = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o2, o1);
            }
        };
        SortedMap<Double, List<Bid>> sortedMap = new TreeMap<>(customComparator);
        for (Bid b : bids) {
            sortedMap.put(b.bidPrice, b);
        }

        //Use Comparator.reverseOrder() for reverse ordering
        bidMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));


        // read the input
        Map<Double, List<Bid>> map = new HashMap<>();
        for (Bid b : bids) {
            double bidPrice = b.bidPrice;
            if (map.containsKey(bidPrice)) {
                map.get(bidPrice).add(b);
            }
            else {
                List<Bid> l = new ArrayList<>();
                l.add(b);
                map.put(bidPrice, l);
            }
        }

        // sort by price desc
        LinkedHashMap<Double, List<Bid>> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        // sort each price level by asc timestamp
        for (double price : reverseSortedMap.keySet()) {
            Collections.sort(reverseSortedMap.get(price), new Comparator<Bid>() {
                @Override
                public int compare(Bid o1, Bid o2) {
                    return Integer.compare(o1.timestamp, o2.timestamp);
                }
            });
        }

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

        List<Integer> result = new ArrayList<>();
        return result;
    }
}
