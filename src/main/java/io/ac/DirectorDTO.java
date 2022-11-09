package io.ac;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDTO {
    private Long id;
    private String firstname;
    private String lastname;

}
