package systemdesign;

public class CountMinDataStructure {
    // Compressed Sensing
    //Networking
    //NLP
    //Stream Processing
    //Frequency tracking
    //Extension: Heavy-hitters
    //Extension: Range-query

    // Issues with Count Min is there data is propablity instead of accurte due to collision or using both hash keys

   /*
    public void multiSetUsage() {
        Multiset<String> blackListedIPs = HashMultiset.create();
        blackListedIPs.add("192.170.0.1");
        blackListedIPs.add("75.245.10.1");
        blackListedIPs.add("10.125.22.20");
        blackListedIPs.add("192.170.0.1");

        System.out.println(blackListedIPs.count("192.170.0.1"));
        System.out.println(blackListedIPs.count("10.125.22.20"))
    }

    public void countMinUsage() {
        CountMinSketch countMinSketch = new CountMinSketch(0.001,0.99,1); // epsilon, delta, seed.

        countMinSketch.add("75.245.10.1", 1);
        countMinSketch.add("10.125.22.20", 1);
        countMinSketch.add("192.170.0.1", 2);

        System.out.println(countMinSketch.estimateCount("192.170.0.1"));
        System.out.println(countMinSketch.estimateCount("999.999.99.99"));
    }
    */
}
