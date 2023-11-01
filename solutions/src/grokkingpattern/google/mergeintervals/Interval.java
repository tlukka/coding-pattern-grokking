package grokkingpattern.google.mergeintervals;

public class Interval {
  public int start;
  public int end;
  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public String getInterval() {
    return "[" + this.start + ", " + this.end + "]";
  }
}
