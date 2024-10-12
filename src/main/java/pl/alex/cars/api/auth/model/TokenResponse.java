package pl.alex.cars.api.auth.model;

public record TokenResponse(String token, long expiration) {

}
