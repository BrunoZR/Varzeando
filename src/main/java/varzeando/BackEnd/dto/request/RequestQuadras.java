package varzeando.BackEnd.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestQuadras {

    @Size(max = 50)
    private String name;

    @Size(max = 300)
    private String descricao;

    @NotEmpty
    private Double latitude;

    @NotEmpty
    private Double longitude;

    private String endereco;

    private String url;
}