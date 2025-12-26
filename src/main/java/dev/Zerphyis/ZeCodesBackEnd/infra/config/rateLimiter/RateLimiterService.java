package dev.Zerphyis.ZeCodesBackEnd.infra.config.rateLimiter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiterService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String key, int limit, Duration duration) {
        return cache.computeIfAbsent(key, k ->
                Bucket.builder()
                        .addLimit(
                                Bandwidth.classic(
                                        limit,
                                        Refill.greedy(limit, duration)
                                )
                        )
                        .build()
        );
    }

}
