package anony.payload.response;

import java.time.Instant;

public interface UserProjection {
    String getUsername();

    Instant getCreateOn();

    Instant getUpdateOn();
}
