package pl.alex.cars.api.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull(message = "Email field should not be empty")
    @Email(message = "User email is not valid")
    String email,
    @NotNull(message = "Password field should not be empty")
    String password) {

}
