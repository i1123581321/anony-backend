package anony.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "username cannot be blank")
        @Size(min = 1, max = 20, message = "username must between 1 and 20 characters")
        String username,
        @NotBlank(message = "password cannot be blank")
        String password
) {
}