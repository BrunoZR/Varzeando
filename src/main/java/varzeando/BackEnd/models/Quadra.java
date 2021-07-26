package varzeando.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "quadra")
@Builder
@Getter
@Setter
@Data
public class Quadra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotEmpty
    @Size(max = 300)
    private String descricao;

    private Double latitude;

    private Double longitude;

    @NotEmpty
    @Size(max = 80)
    private String endereco;

    @NotEmpty
    private String url;
}