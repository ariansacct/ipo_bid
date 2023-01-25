public class Bid {

    // bids = list of lists of ints representing [userid, # shares, $bid, timestamp]
    // bids = [[0,2,10,0], [1,2,10,0]] and totalShares = 2
    int uid;
    int numShares;
    double bidPrice;
    int timestamp;

    public Bid(int uid, int numShares, double bidPrice, int timestamp) {
        this.uid = uid;
        this.numShares = numShares;
        this.bidPrice = bidPrice;
        this.timestamp = timestamp;
    }
}
