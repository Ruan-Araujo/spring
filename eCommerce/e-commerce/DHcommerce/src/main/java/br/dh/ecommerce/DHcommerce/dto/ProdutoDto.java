package br.dh.ecommerce.DHcommerce.dto;

public class ProdutoDto {

    private String titulo;
    private float preco;
    private String descricao;
    private String imagem;
    private Integer categoria;

    public ProdutoDto() {
    }

    public ProdutoDto(String titulo, float preco, String descricao, String imagem, Integer categoria) {
        this.titulo = titulo;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
}
