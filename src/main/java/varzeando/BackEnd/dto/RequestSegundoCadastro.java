package varzeando.BackEnd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import varzeando.BackEnd.models.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestSegundoCadastro {

    @Email
    @Size(max = 50)
    private String email;

    private String posicao;

    @Size(max = 50)
    private String endereco;
}
