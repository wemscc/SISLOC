import java.util.Scanner;
import java.util.Vector;

/**
 * Classe que implemente a interface IRepositorioFilme e que será utilizada como Repositório para objetos do tipo Filme.
 * Contém métodos que nos permitem manipular esses objetos e o nosso repositório.
 */
public class RepositorioFilme implements IRepositorioFilme{
    /** Esse Vector é onde armazenamos todos os filmes.*/
    private Vector<Filme> filmes = new Vector<>();

    /** Esse método é utilizado para cadastrar filmes.
     *  Primeiro, recebemos como parâmetro um objeto do tipo filme, e então:
     *  Olhamos no nosso Vector, usando o método buscar, passando como parâmetro o código do filme que recebemos,
     *  para checar se o filme já está cadastrado.
     *  Caso esteja, o filme não é cadastrado e é retornado uma mensagem de erro.
     *  Caso não esteja, adicionamos esse filme no nosso Vector que usamos de repositório.
     */
    @Override
    public void cadastrar(Filme filme) {
        Scanner scanner = new Scanner(System.in);
        if (buscar(filme.getCodigo()) == null)
            filmes.add(filme);
        else
            System.out.println("Não é possível cadastrar um filme já cadastrado!");

    }

    /** Esse método é utilizado para buscar filmes (cadastrados). Recebemos como parâmetro um código e então:
     *  Andamos por todo o nosso Vector de filmes, comparando o campo código de cada objeto Filme com o código que nos foi passado,
     *  para checar se o filme já foi cadastrado.
     *  Caso esteja, retornamos o objeto filme. Caso não esteja, retornamos null.
     */
    @Override
    public Filme buscar(int codigo) {
        for(Filme filme1 : filmes){
            if (filme1.getCodigo() == codigo ){
                return filme1;
            }
        }
        return null;
    }

    /** Esse método é utilizado para atualizar o cadastro de filmes. Recebemos como parâmetro um filme e então:
     *  Usamos o método buscar(), passando como parâmetro o código desse objeto que recebemos agora, para checar se o filme já está cadastrado;
     *  Caso esteja, atualizamos as informações do objeto cliente, trocando o objeto que já estava no Vector pelo novo que recebemos.
     *  Caso não esteja, retornamos uma mensagem de erro informando que não se pode atualizar um filme não cadastrado.
     */
    @Override
    public void atualizar(Filme filme) {
        Filme filme1 = buscar(filme.getCodigo());
        if (filme1 == null)
            System.out.println("Não é possível atualizar um filme não cadastrado!");
        else{
            filmes.set(filmes.indexOf(filme1),filme); // TESTAR ESSE !!!!!!
            if (filme.getTitulo().equals(null))
                filme.setTitulo(filme1.getTitulo());
            if (filme.getSinopse().equals(null))
                filme.setSinopse(filme1.getSinopse());
            if (filme.getGenero().equals(null))
                filme.setGenero(filme1.getGenero());
            if (filme.getAtores().equals(null))
                filme.setAtores(filme1.getAtores());
            if (filme.getDiretor().equals(null))
                filme.setDiretor(filme1.getDiretor());
            if (filme.getDataLancamento().equals(null))
                filme.setDataLancamento(filme1.getDataLancamento());
            filmes.set(filmes.indexOf(filme1),filme);

        }
    }

    /** Esse método é utilizado para apagar o cadastro de filmes. Recebemos como parâmetro um filme e então:
     *  Usamos o método buscar(), passando como parâmetro o código que recebemos, para checar se o filme já está cadastrado;
     *  Caso esteja, removemos o filme do nosso Vector de Filmes.
     *  Caso não esteja, retornamos uma mensagem de erro informando que não foi possível realizar a operação, pois o filme não está cadastrado.
     */
    @Override
    public void deletar(int codigo) {
        if (buscar(codigo) != null){
            Filme filme1 = buscar(codigo);
            filmes.remove(filme1);
        }
    }

    /** Esse método é utilizado para retornar o cadastro de todos os filmes.
     * Ele simplesmente retorna o nosso Vector que usamos para armazenar todos os objetos.
     */
    @Override
    public Vector<Filme> listar() {
        return this.filmes;
    }

}
