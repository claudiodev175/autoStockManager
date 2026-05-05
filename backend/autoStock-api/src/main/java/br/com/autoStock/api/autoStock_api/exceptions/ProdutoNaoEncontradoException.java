package br.com.autoStock.api.autoStock_api.exceptions;



public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }

}
