package io.ac.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
/**
 * I also thought it was a bug, but they explain it better here: https://quarkus.io/guides/writing-native-applications-tips#registering-for-reflection
 *
 * When building a native executable, GraalVM operates with a closed world
 * assumption. It analyzes the call tree and removes all the classes/methods/fields
 * that are not used directly.
 *
 * The elements used via reflection are not part of the call tree so they are dead
 * code eliminated (if not called directly in other cases). To include these
 * elements in your native executable, you need to register them for reflection
 * explicitly.
 */
public class LoginResponseDTO {
    private String accessToken;
    private Integer expiredIn;
}
