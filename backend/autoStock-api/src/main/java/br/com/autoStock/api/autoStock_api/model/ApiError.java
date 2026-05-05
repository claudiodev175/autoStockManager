package br.com.autoStock.api.autoStock_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private int status;
    private String erro;
    private String caminho;
    private LocalDateTime data;

}
