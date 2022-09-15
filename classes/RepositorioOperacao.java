import java.util.List;
import java.util.Vector;
import java.util.Collections;
/**
 * Classe que implemente a interface IRepositorioOperacao e que será utilizada como Repositório para objetos do tipo Operacao.
 * Contém métodos que nos permitem manipular esses objetos e o nosso repositório.
 */
public class RepositorioOperacao implements IRepositorioOperacao {
    /** Esse Vector é onde armazenamos todas as operações feitas, ativas ou inativas.*/
    private Vector<Operacao> operacoes = new Vector<Operacao>();

    /**
     * Esse método recebe um objeto do tipo Operação e retorna verdadeiro se ele é do tipo Locação, e falso caso não seja.
     */
    @Override
    public boolean isLocacao(Operacao operacao) {
        if (operacao instanceof Locacao)
            return true;
        return false;
    }

    /**
     * Esse método recebe um objeto do tipo Operação e retorna verdadeiro se ele é do tipo Reserva, e falso caso não seja.
     */
    @Override
    public boolean isReserva(Operacao operacao) {
        if (operacao instanceof Reserva)
            return true;
        return false;
    }

    /**
     * Esse método recebe um objeto do tipo Operação e altera o seu campo Ativo para true, isto é, cadastra a operação
     * Além disso, ele adiciona a operação no Vector operacoes, que usamos de repositório para todas as operações.
     */
    @Override
    public void cadastrar(Operacao operacao) {
        operacao.setAtivo(true);
        operacoes.add(operacao);
    }

    /**
     * Esse método recebe um cpf de um cliente, e retorna um Vector que contém todas as reservas ativas desse cliente.
     *
     *
     */
    @Override
    public Vector<Reserva> buscarReservas(long cpf) {
        Vector<Reserva> buscaReservasAtivas = new Vector<>();
        for (Operacao operacao : operacoes) {
            if (isReserva(operacao) && operacao.getCpf() == cpf && operacao.isAtivo()) {
                buscaReservasAtivas.add((Reserva) operacao);
            }
        }
        return buscaReservasAtivas;
    }

    /**
     * Esse método recebe um cpf de um cliente, e retorna um Vector que contém todas as locações ativas desse cliente.
     *
     *
     */
    @Override
    public Vector<Locacao> buscarLocacoes(long cpf) {
        Vector<Locacao> buscaLocacoesAtivas = new Vector<>();
        for (Operacao operacao : operacoes) {
            if (isLocacao(operacao) && operacao.getCpf() == cpf && operacao.isAtivo()) {
                        buscaLocacoesAtivas.add((Locacao) operacao);
                }
        }
        return buscaLocacoesAtivas;
    }

    /**
     * Esse método recebe um cpf de um cliente e um código de um filme, e caso o cliente tenha uma reserva ativa para este filme,
     * ele altera o campo Ativo dessa reserva para falso, ou seja, encerra aquela reserva.
     *
     */
    @Override
    public void deletarReserva(long cpf, int codigo) {
        for (Operacao operacao : operacoes) {
            if (isReserva(operacao) && operacao.getCpf() == cpf && operacao.getCodigo() == codigo) {
                atualizaReservas(cpf,codigo);
                operacao.setAtivo(false);
            }
        }
    }

    /**
     * Esse método recebe um cpf de um cliente e um código de um filme, e caso o cliente tenha uma locação ativa para este filme,
     * ele altera o campo Ativo dessa locação para falso, ou seja, encerra aquela reserva.
     *
     */
    @Override
    public void deletarLocacao(long cpf, int codigo) {
        for (Operacao operacao : operacoes) {
            if (isLocacao(operacao) && operacao.getCpf() == cpf && operacao.getCodigo() == codigo) {
                operacao.setAtivo(false);
            }
        }
    }

    /**
     * Esse método recebe como parâmetro o cpf de um cliente e retorna
     * um Vector com a lista de todos as locações feitas pelo mesmo, inativas ou ativas.
     *
     */
    @Override
    public Vector<Locacao> listarLocacao(long cpf) {
        Vector<Locacao> listaLocacoes = new Vector<>();
        for (Operacao operacao : operacoes) {
            if (isLocacao(operacao) && operacao.getCpf() == cpf) {
                    listaLocacoes.add((Locacao) operacao);
            }
        }
        return listaLocacoes;
    }

    /**
     * Esse método recebe como parâmetro o cpf de um cliente e retorna
     * quantas locações o mesmo fez até então.
     *
     */
    @Override
    public int numeroLocacoes(long cpf) {
        return listarLocacao(cpf).size();
    }

    /**
     * Esse método recebe como parâmetro o código de um filme e retorna
     * quantas vezes esse filme foi locado.
     *
     */
    @Override
    public int numeroLocacoes(int codigo) {
        int cont = 0;
        for (Operacao operacao : operacoes) {
            if (isLocacao(operacao) && operacao.getCodigo() == codigo) {
                cont++;
            }
        }
        return cont;
    }

    /**
     * Esse método recebe como parâmetro o cpf de um cliente e retorna
     * quantas locações ativas ele tem.
     */
    @Override
    public int numeroLocacoesAtivas(long cpf) {
        return buscarLocacoes(cpf).size();
    }

    /**
     * Esse método recebe como parâmetro o código de um filme e retorna
     * quantas pessoas tem o filme locado (isto é, as locações ativas).
     */
    @Override
    public int numeroLocacoesAtivas(int codigo) {
        int cont = 0;
        for (Operacao operacao : operacoes) {
            if (isLocacao(operacao) && operacao.getCodigo() == codigo && operacao.isAtivo()) {
                        cont++;
            }
        }
        return cont;
    }

    /**
     * Esse método recebe como parâmetro o código de um filme e retorna
     * quantas pessoas estão esperando para poder aluga-lo (isto é, as reservas ativas).
     */
    @Override
    public int numeroReservas(int codigo) {
        int cont = 0;
        for (Operacao operacao : operacoes) {
            if (isReserva(operacao) && operacao.getCodigo() == codigo && operacao.isAtivo()) {
                        cont++;
            }
        }
        return cont;
    }

    /**
     * Esse método recebe como parâmetro o código de um filme e retorna o cpf do cliente que tem maior prioridade na reserva,
     * isto é, o cpf da pessoa com a reserva mais antiga.
     */
    @Override
    public long topPrioridade(int codigo) {
        for (Operacao operacao: buscarReservas(codigo)){
                Reserva reserva = (Reserva) operacao;
                if (reserva.getPrioridade() == 1){
                    atualizaReservas(reserva.getCpf(),codigo);
                    return reserva.getCpf();
                }
            }
        return 0;
    }

    /**
     * Esse método recebe como parâmetro o código de um filme e retorna um Vector com todas as reservas ativas desse filme.
     */
    public Vector<Reserva> buscarReservas(int codigo) {
        Vector<Reserva> buscaReservasAtivas = new Vector<>();
        for (Operacao operacao : operacoes) {
            if (isReserva(operacao) && operacao.getCodigo() == codigo && operacao.isAtivo()) {
                buscaReservasAtivas.add((Reserva) operacao);
            }
        }
        return buscaReservasAtivas;
    }
    /**
     * Esse método foi criado para ser possível manter a lista de prioridade. Esse método só é chamado quando:
     * i) a primeira pessoa da fila consegue locar o filme que queria
     * ii) quando uma das pessoas na fila do filme desiste de sua reserva
     * Aqui recebemos como parâmetro o cpf de um cliente e o código de um filme, e, pegamos a prioridade do cliente em relação aquele filme e então:
     * Em ambos os casos, andamos por todo o Vector de reservas ativas, e subtraímos 1 da prioridade das pessoas que têm a prioridade maior
     * que a que está "saindo da fila", ou seja, todos a partir da pessoa que está saindo "andam um passo na fila".
     */
    public void atualizaReservas(long cpf, int codigo){
        int atualizar = 0;
        for (Reserva reserva : buscarReservas(codigo)){
            if (reserva.getCpf() == cpf)
                atualizar = reserva.getPrioridade();
        }
        for (Reserva reserva: buscarReservas(codigo)) {
            if (reserva.getPrioridade() > atualizar)
                reserva.setPrioridade(reserva.getPrioridade() - 1);
        }
    }


    /**
     * Esse método é utilizado para gerar a lista que usamos para mostrar os filmes mais locados. Usamos nesse metódo algumas coisas da classe Collections, nativa do Java
     * Para fazer isso, começamos criando um vetor auxiliar para manipularmos as nossas operações: não queremos mexer no conteúdo original; Usamos do método addAll para criarmos nosso Vector cópia.
     * Com essa lista em mãos, utilizamos do algoritmo de ordenamento Bubble Sort, passando como parâmetro de ordenação o numero de locações de cada filme, para ordenarmos de forma decrescente.
     * Encontramos o número de locações usando o método numeroLocações:
     * @see RepositorioOperacao#numeroLocacoes(int codigo);
     * Usamos o método swap() da classe collections para trocarmos os elementos de posição/ordenarmos a lista.
     * @see Collections#swap(List, int, int)
     * Por fim, devolvemos a lista ordenada.
     * */
    @Override
    public Vector<Operacao> listaMaisLocado() {
        Vector<Operacao> lista = new Vector<>();
        lista.addAll(operacoes);
        for (int i = 0; i < lista.size(); i++){
            for (int j = 0; j < i; j++) {
                if (numeroLocacoes(lista.get(i).getCodigo()) > numeroLocacoes(lista.get(j).getCodigo())){
                    Collections.swap(lista,i,j);
                }
            }
        }
        return lista;
    }

}
