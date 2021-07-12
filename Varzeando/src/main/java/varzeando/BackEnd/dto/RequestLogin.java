package varzeando.BackEnd.dto;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestLogin {
    private String email;
    private String password;
}
