package systemdesign;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
    private long now = System.nanoTime();
    private final int size = 100, rate = 100;
    private int curentSize = 0;
    // Token Bucket
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        curentSize = (int) Math.min(size, curentSize + (currentTime - now) * rate);
        if (curentSize > 0) {
            now = currentTime;
            curentSize--;
            return true;
        } else {
            return false;
        }
    }

    // Sliding Window Algoritham
    static class SlideWindowRateLimiter {
        private AtomicInteger[] buckets;
        private int index = 0;
        private int maxVisitPerSecond;
        private AtomicInteger visit = new AtomicInteger(0);

        public SlideWindowRateLimiter(int bucket, int maxVisitPerSecond) {
            this.buckets = new AtomicInteger[bucket];
            for (int i = 0; i < bucket; i++) {
                buckets[i] = new AtomicInteger(0);
            }
            this.maxVisitPerSecond = maxVisitPerSecond;
            startThread();
        }

        private void startThread() {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    index = (index + 1) % SlideWindowRateLimiter.this.buckets.length;
                    int val = SlideWindowRateLimiter.this.buckets[index].getAndSet(0);
                    SlideWindowRateLimiter.this.visit.addAndGet(-val);
                }
            }, 100, 100, TimeUnit.MILLISECONDS);
        }

        public synchronized boolean allowRequest() {
            buckets[index].addAndGet(1);
            visit.addAndGet(1);
            return visit.get() > maxVisitPerSecond ? false : true;
        }

    }

    // Bucket Alogritham
    static class Bucket {
        final long maxBucketSize;
        final long refillRate;
        double currentBucketSize;
        long lastRefillTimeStamp;

        public Bucket(long maxBucketSize, long refillRate) {
            this.maxBucketSize = maxBucketSize;
            this.refillRate = refillRate;
            currentBucketSize = maxBucketSize;
            lastRefillTimeStamp = System.nanoTime();
        }

        public synchronized boolean allowRequest(int tokens) {
            refill();
            if (currentBucketSize > tokens) {
                currentBucketSize -= tokens;
                return true;
            } else {
                return false;
            }
        }

        void refill() {
            long now = System.nanoTime();
            double tokensAdd = (now - lastRefillTimeStamp) * refillRate / 1e9;
            currentBucketSize = Math.min(currentBucketSize + tokensAdd, maxBucketSize);
            lastRefillTimeStamp = now;
        }
    }

}
