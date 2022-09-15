import java.util.Objects;
import java.util.Vector;
import java.util.Date;

public class Filme {

    private int codigo;
    private String titulo;
    private String diretor;
    private String sinopse;
    private float precoLocacao;
    private int numeroCopias;
    private Date dataLancamento;
    private Vector<String> genero;
    private Vector<String> atores;
    private Vector<String> produtores;

    public Filme(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        genero = new Vector<String>();
        atores = new Vector<String>();
        produtores = new Vector<String>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Vector<String> getGenero() {
        return genero;
    }

    public void setGenero(Vector<String> genero) {
        this.genero = genero;
    }

    public void addGenero(String genero){
        if (genero == null){
            this.genero = new Vector<String>();
        }
        this.genero.add(genero);
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Vector<String> getAtores() {
        return atores;
    }

    public void setAtores(Vector<String> atores) {
        this.atores = atores;
    }

    public void addAtor(String ator){
        this.atores.add(ator);
    }
    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Vector<String> getProdutores() {
        return produtores;
    }

    public void setProdutores(Vector<String> produtores) {
        this.produtores = produtores;
    }

    public void addProdutor(String produtor){
        this.produtores.add(produtor);
    }

    public float getPrecoLocacao() {
        return precoLocacao;
    }

    public void setPrecoLocacao(float precoLocacao) {
        this.precoLocacao = precoLocacao;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        this.numeroCopias = numeroCopias;
    }

    public void imprimir(){
        System.out.println("Código do filme: " + this.getCodigo());
        System.out.println("Título do filme: " + this.getTitulo());
        System.out.println("Data de lançamento do filme:" + this.getDataLancamento());
        System.out.println("Sinopse do filme:" + this.getSinopse());
        System.out.println("Gêneros do filme:" + this.getGenero());
        System.out.println("Atores que trabalharam no filme:" + this.getAtores());
        System.out.println("Diretor do filme:" + this.getDiretor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return codigo == filme.codigo;
    }
}
