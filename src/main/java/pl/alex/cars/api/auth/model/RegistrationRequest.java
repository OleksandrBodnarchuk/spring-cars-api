package pl.alex.cars.api.auth.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
    @NotNull(message = "Name field should not be empty")
    @Size(min = 3, message = "Name should contain minimum 3 characters")
    String name,

    @NotNull(message = "Surname field should not be empty")
    @Size(min = 3, message = "Surname should contain minimum 3 characters")
    String surname,

    @NotNull(message = "Email field should not be empty")
    @Email(message = "User email is not valid")
    String email,

    @NotNull(message = "Password field should not be empty")
    @Size(min = 5, max = 15, message = "Password size should be within 5 to 15 characters range")
    String password
) {

}
