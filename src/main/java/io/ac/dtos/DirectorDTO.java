package io.ac.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class DirectorDTO {
    private Long id;
    private String firstname;
    private String lastname;
}
