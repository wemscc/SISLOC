import java.util.Vector;
/**
 * Classe que usamos para acessar o SisLoc
 * Contém métodos que nos permitem manipular esses objetos e o nosso repositório.
 */
public class Locadora {
    /**
     * Os atributos dessa classe são do mesmo tipo das interfaces que nossos repositórios implementaram, assim,
     * podemos passar nossos repositórios como parâmetros.
     */
    IRepositorioFilme filmes;
    IRepositorioCliente clientes;
    IRepositorioOperacao operacoes;

    /**
     * Construtor da classe; inicializamos passando os três repositórios.
     */
    public Locadora(IRepositorioCliente clientes, IRepositorioFilme filmes, IRepositorioOperacao operacoes) {
        this.filmes = filmes;
        this.clientes = clientes;
        this.operacoes = operacoes;
    }
    /**
     * Esse método recebe um objeto do tipo Cliente e chama o método cadastrar() do nosso RepositorioCliente:
     * @see RepositorioCliente#cadastrar(Cliente)
     */
    public void cadastrarCliente(Cliente cliente){
       clientes.cadastrar(cliente);
    }

    /** Esse método é utilizado para buscar clientes. Recebemos como parâmetro um cpf e então chamamos o método buscar() do nosso RepositorioCliente:
     @see RepositorioCliente#buscar(long cpf)
     */
    public Cliente buscarCliente(long cpf){
        return clientes.buscar(cpf);
    }

    /** Esse método é utilizado para atualizar o dado de clientes. Recebemos como parâmetro um objeto do tipo cpf e chamamos o método atualizar do nosso RepositorioCliente:
     * @see RepositorioCliente#atualizar(Cliente)
     */
    public void atualizarCadastroCliente(Cliente cliente){
        clientes.atualizar(cliente);
    }

    /** Esse método é utilizado para apagar o registro de algum cliente. Recebemos como parâmetro um cpf e então:
     *  Primeiro checamos se o cpf já está cadastrado; Caso esteja, vemos se ele tem alguma locação ativa.
     *  Caso tenha, retorna uma mensagem informando que o cliente não pode ser excluido até não ter mais locações.
     *  Caso não tenha, chamamos o método deletar() do nosso RepositorioCliente:
     * @see RepositorioCliente#deletar(long cpf)
     */
    public void removerCliente(long cpf){
        if (buscarCliente(cpf) != null){
            if (operacoes.numeroLocacoesAtivas(cpf) == 0)
                clientes.deletar(cpf);
            else
                System.out.println("O cliente ainda tem locações ativas!");
        }
        else
            System.out.println("O cliente não está cadastrado!");
    }

    /** Esse método é utilizado para cadastrar filmes. Recebemos um objeto filme e então chamamos o método cadastrar() do nosso RepositorioFilme:
     * @see RepositorioFilme#cadastrar(Filme)
     */
    public void cadastrarFilme(Filme filme){
        filmes.cadastrar(filme);
    }

    /** Esse método é utilizado para buscar filmes. Recebemos um código e então chamamos o método buscar() do nosso RepositorioFilme:
     * @see RepositorioFilme#buscar(int codigo)
     */
    public Filme buscarFilme(int codigo){
        return filmes.buscar(codigo);
    }

    /** Esse método é utilizado para atualizar o cadastro dos filmes. Recebemos um objeto do tipo filme e então chamamos o método atualizar() do nosso RepositorioFilme:
     * @see RepositorioFilme#atualizar(Filme)
     * */
    public void atualizarCadastroFilme(Filme filme){
        filmes.atualizar(filme);

    }

    /** Esse método é utilizado para apagar o registro de algum filme. Recebemos como parâmetro um código e então:
     *  Primeiro checamos se o filme já está cadastrado;
     *  Caso esteja, vemos se ele tem alguma locação ativa. Se tiver, retorna uma mensagem informando que o filme não pode ser excluido até não ter mais locações.
     *  Caso não tenha, chamamos o método deletar() do nosso RepositorioFilme:
     * @see RepositorioFilme#deletar(int codigo)
     */
    public void removerFilme(int codigo){
        if (filmes.buscar(codigo) != null) {
            if (operacoes.numeroLocacoesAtivas(codigo) == 0)
                filmes.deletar(codigo);
            else
                System.out.println("O filme ainda tem locações ativas!");
        }
        else{
            System.out.println("Esse filme não foi cadastrado!");
        }
    }

    /** Esse método é utilizado para realizar a reserva de um filme. Recebemos como parâmetro um cpf e um código e então:
     *  Primeiro checamos se o filme e o cliente já estão cadastrados;
     *  Caso estejam, vemos se:
     *  i) Se o filme e o cliente passado já estão cadastrados;
     *  ii) O número de locações é igual o número de cópias do filme, isto é, não tem mais exemplares do filme disponíveis para alugar;
     *      Caso ainda tenham cópias, o usuário recebe a mensagem que, na verdade, ele deve locar o filme, e caso ele aceite, chamamos o método locarFilme();*
     * @see RepositorioFilme#deletar(int codigo)
     */
    public void reservarFilme(long cpf, int codigo){
        if (filmes.buscar(codigo) != null && clientes.buscar(cpf) != null && filmes.buscar(codigo).getNumeroCopias() == operacoes.numeroLocacoesAtivas(codigo)){
            Reserva reserva = new Reserva(cpf,codigo);
            reserva.setPrioridade(operacoes.numeroReservas(codigo)+1);
            operacoes.cadastrar(reserva);
            System.out.println("Reserva realizada com sucesso. Você está em " +reserva.getPrioridade() + "o lugar na fila.");
        }
        else if (operacoes.numeroLocacoesAtivas(codigo) < filmes.buscar(codigo).getNumeroCopias())
            System.out.println("Erro! Ainda existem cópias disponíveis para esse filme. Portanto, você deve buscar a opção alugar filme.");

    }

    /**
     * Esse método é utilizado para finalizar a reserva de um filme, isto é, alterar o status da reserva de ativa para inativa;
     * Para isso, usamos os métodos:
     * @see RepositorioOperacao#buscarReservas(long cpf) - para pegar todas as reservas feitas pelo cliente, e podermos checar se ele tem uma reserva ativa para aquele filme
     * @see RepositorioOperacao#atualizaReservas(long cpf, int codigo) - para atualizar a fila de reservas;
     */
    public void finalizarReservaFilme(long cpf, int codigo){
        int controle = 0;
        for (Operacao operacao: operacoes.buscarReservas(cpf)){
            if (operacao.getCodigo() == codigo && operacao.isAtivo()){
                controle = 1;
                operacao.setAtivo(false);
                operacoes.atualizaReservas(cpf,codigo);
            }
            if (controle == 0)
                System.out.println("Erro! Reserva não encontrada.");
        }
     }

    /**
     * Esse método é utilizado para realizar a locação de um filme para um cliente.
     * Para isso, primeiro vemos se o cliente e o filme já estão cadastrados
     * Caso estejam, vemos se o filme tem cópias disponíveis e não há reservas ativas para ele
     * Caso esteja tudo certo, ou caso hajam reservas ativas mas o cliente é a pessoa com maior prioridade, cadastramos a locação no nosso repositório de operações, e atualizamos a fila de reserva.
     * */
    public void locarFilme(long cpf, int codigo){
        if (filmes.buscar(codigo)!= null && buscarCliente(cpf)!= null){
            Locacao locar = new Locacao(cpf,codigo);
            if ((filmes.buscar(codigo).getNumeroCopias() > operacoes.numeroLocacoesAtivas(codigo) && operacoes.numeroReservas(codigo) == 0)){
                operacoes.cadastrar(locar);
                System.out.println("Locação concluída com sucesso");
            }
            else if (operacoes.topPrioridade(codigo) == cpf){
                    operacoes.cadastrar(locar);
                    System.out.println("Locação concluída com sucesso");
                    operacoes.atualizaReservas(cpf,codigo);
                }
            else{
                System.out.println("O filme não está disponível para ser alugado.");
                System.out.println("Caso deseje reservar esse filme, vá na opção 4 do sistema.");
            }

        }
    }

    /**
     * Esse método é utilizado para realizar a devolução de um filme
     * Para isso, primeiro vemos se o cliente e o filme já estão cadastrados
     * Caso estejam, procuramos a locação daquele cliente daquele filme no Vector de operações e trocamos seu campo Ativo para falso.
     * */
    public void devolverFilme(long cpf, int codigo){
        int controle = 0;
        if (filmes.buscar(codigo)!= null && buscarCliente(cpf)!= null) {
            for (Operacao operacao : operacoes.listarLocacao(cpf)){
                if (operacao.getCodigo() == codigo && operacao.isAtivo()){
                    controle = 1;
                    operacao.setAtivo(false);
                }
            }
        }
        if (controle == 0)
            System.out.println("Erro! Locação não encontrada.");
    }

    /**
     * Esse método é utilizado para mostrar o histórico de todas as locações realizadas por um determinado cliente
     * Para isso, primeiro vemos se o cliente já está cadastrado
     * Caso esteja, usamos o método listarLocacao() passando como parâmetro o cpf desse cliente.
     * @see RepositorioOperacao#listarLocacao(long cpf)
     * */
    public void imprimirHistoricoLocacoes(long cpf){
        if (buscarCliente(cpf)!= null){
            for (Operacao operacao : operacoes.listarLocacao(cpf)){
                if (operacoes.isLocacao(operacao))
                    System.out.println("Locação");
                else
                    System.out.println("Reserva");
                System.out.println("Código do Filme: " + operacao.getCodigo() + " - " + filmes.buscar(operacao.getCodigo()));
                System.out.println("Data da Locação: " + operacao.getData());
                System.out.println("Estado da Locação: " + operacao.isAtivo());
            }
            if (operacoes.listarLocacao(cpf).size() == 0)
                System.out.println("O cliente ainda não realizou nenhuma operação.");
        }
        else
            System.out.println("Erro! Cliente não encontrado.");

    }

    /**
     * Esse método é utilizado para mostrar os TOP filmes mais locados (top representa o número de filmes que mostraremos)
     * Para isso, usamos o método listaMaisLocado() para recebermos uma lista que contém os códigos dos filmes com maior número de locações, em ordem decrescente, número esse dado pelo método numeroLocacoes();
     * @see RepositorioOperacao#listaMaisLocado()
     * @see RepositorioOperacao#numeroLocacoes(int codigo)
     * Após recebermos essa lista, pegamos os códigos e criamos uma lista de objetos do tipo Filme que contém os objetos Filmes correspondentes aos nossos códigos
     * Por fim, usamos o método imprimir() de Filme para mostramos os Top primeiros filmes dessa lista de filmes.
     * @see Filme#imprimir()
     * */
    public void imprimirFilmesMaisLocados(int top){
        System.out.println("Lista dos " + top + " filmes mais locados na nossa Locadora:");
        Vector<Operacao> filmesmaisLocados = operacoes.listaMaisLocado();
        Vector<Filme> filmesTopLocacoes = new Vector<>();
        for (Operacao operacao: filmesmaisLocados){
                filmesTopLocacoes.add(filmes.buscar(operacao.getCodigo()));
        }
        for (int i=0;i<top;i++){
            filmesTopLocacoes.get(i).imprimir();
        }
    }

}
