package pl.alex.cars.api.auth.model;


import lombok.Builder;

@Builder
public record RegistrationResponse(String userId, String name, String surname, String email) {

  public static RegistrationResponse from(UserDto userDto) {
    return RegistrationResponse.builder()
        .userId(userDto.userId())
        .name(userDto.name())
        .surname(userDto.surname())
        .email(userDto.email())
        .build();
  }
}
