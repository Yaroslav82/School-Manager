package com.hillel.multi.configuration.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        double freeMemoryPercent = ((double) freeMemory / totalMemory) * 100;
        String freeMemoryMessage = String.format("%.2f%%", freeMemoryPercent);

        if (freeMemoryPercent > 25) {
            return Health.up()
                    .withDetail("free_memory", freeMemoryMessage)
                    .build();
        } else {
            return Health.down()
                    .withDetail("free_memory", freeMemoryMessage)
                    .build();
        }
    }
}
