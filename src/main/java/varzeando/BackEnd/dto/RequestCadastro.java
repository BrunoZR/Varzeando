package varzeando.BackEnd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import varzeando.BackEnd.models.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestCadastro {
    private String name;
    @Email
    private String email;
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
}
