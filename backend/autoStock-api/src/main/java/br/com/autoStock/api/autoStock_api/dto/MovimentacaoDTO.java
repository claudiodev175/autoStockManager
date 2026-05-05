package br.com.autoStock.api.autoStock_api.dto;

import br.com.autoStock.api.autoStock_api.model.Movimentacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimentacaoDTO {

private Long id;
    private String produtoNome;
    private String tipo;
    private Integer quantidade;
    private String data;

    public static MovimentacaoDTO fromEntity(Movimentacao mov) {
        return MovimentacaoDTO.builder()
                .id(mov.getId())
                .produtoNome(mov.getProduto().getNome())
                .tipo(mov.getTipo().name())
                .quantidade(mov.getQuantidade())
                .data(mov.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .build();
    }
}