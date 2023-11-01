package grokkingpattern.combine;

public class CpuJob {
  public int start;
  public int end;
  public int cpuLoad;
  public CpuJob(int start, int end, int cpuLoad) {
    this.start = start;
    this.end = end;
    this.cpuLoad = cpuLoad;
  }

  public CpuJob() {
    this.cpuLoad=0;
    this.end=0;
    this.start=0;
  }

  public String getInterval() {
    return "[" + this.start + ", " + this.end + "]";
  }
}
