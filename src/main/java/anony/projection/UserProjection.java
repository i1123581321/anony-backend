package anony.projection;

import java.time.Instant;

public record UserProjection(
        String username,
        Instant createOn,
        Instant updateOn
) {
}
