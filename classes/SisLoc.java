import java.util.Scanner;

public class SisLoc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepositorioCliente clientes = new RepositorioCliente();
        RepositorioFilme filmes = new RepositorioFilme();
        RepositorioOperacao operacoes = new RepositorioOperacao();
        boolean controle = true;
        Locadora sistema = new Locadora(clientes, filmes, operacoes);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\tSISLOC - Sua locadora favorita!");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        while (controle) {
            System.out.println("Olá, no que posso ajudar? ");
            System.out.println("0: Sair do sistema");
            System.out.println("1: Cadastrar filme");
            System.out.println("2: Cadastrar cliente");
            System.out.println("3: Alugar um filme");
            System.out.println("4: Reservar um filme");
            System.out.println("5: Devolver um filme");
            System.out.println("6: Atualizar os dados de algum filme");
            System.out.println("7: Atualizar os dados de algum cliente ");
            System.out.println("8: Buscar algum cliente");
            System.out.println("9: Buscar algum filme");
            System.out.println("10: Imprimir histórico do cliente");
            System.out.println("11: Imprimir filmes mais locados");
            System.out.println("12: Remover filme");
            System.out.println("13: Remover cliente");
            int opcao = scanner.nextInt();
            /*
              Esse switch serve para o usuário poder manipular o sistema, sendo cada opção de escolha sendo apresentada a cada iteração.
              Para deixar o código mais limpo e tornar mais fácil a manipulação/crescimento do sistema no futuro, faço tudo o que o sistema oferece através de métodos.
             */

            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistema! Esperamos vê-lo novamente!");
                    controle = false;
                    break;
                case 1:
                    cadastrarFilme(sistema);
                    break;
                case 2:
                    cadastrarCliente(sistema);
                    break;
                case 3:
                    alugarFilme(sistema);
                    break;
                case 4:
                    reservarFilme(sistema);
                    break;
                case 5:
                    devolverFilme(sistema);
                    break;
                case 6:
                    atualizarFilme(sistema);
                    break;
                case 7:
                    atualizarCliente(sistema);
                    break;
                case 8:
                    buscarCliente(sistema);
                    break;
                case 9:
                    buscarFilme(sistema);
                  break;
                case 10:
                    historicoCliente(sistema);
                    break;
                case 11:
                    imprimirFilmesMaisLocados(sistema);
                    break;
                case 12:
                    removerFilme(sistema);
                    break;
                case 13:
                    removerCliente(sistema);
                    break;
                default:
                    System.out.println("Número inválido. Por favor digite um número entre 0 e 10.");
                    break;
            }
        }
    }
    /**
     * Esse método serve para cadastrar filmes.
     * Restrição: não devem ser cadastrados clientes com o mesmo código, isto é, o código é um identificador único;
     */
    public static void cadastrarFilme(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código do filme que deseja cadastrar");
        int codigo = scanner.nextInt();scanner.nextLine();
        if (sistema.buscarFilme(codigo) != null) {
            System.out.println("Já existe um filme com esse código. Tente novamente.");
        }
        if (sistema.buscarFilme(codigo) == null) {
            int quantidade;
            System.out.println("Digite o título do filme: ");
            String titulo = scanner.nextLine();
            Filme filme = new Filme(codigo, titulo);
            System.out.println("Digite o nome do diretor do filme: ");
            filme.setDiretor(scanner.nextLine());
            System.out.println("Digite a sinopse do filme: ");
            filme.setSinopse(scanner.nextLine());
            System.out.println("Digite o preço de locação do filme: ");
            filme.setPrecoLocacao(scanner.nextFloat());
            System.out.println("Digite o número de cópias do filme: ");
            filme.setNumeroCopias(scanner.nextInt());
            System.out.println("Digite a quantidade de gêneros que deseja inserir: ");
            quantidade = scanner.nextInt();
            for (int i = 0 ; i < quantidade; i++) {
                System.out.println("Digite o " + (i+1) + "o gênero");
                filme.addGenero(scanner.nextLine());
            }
            System.out.println("Digite a quantidade de atores que deseja inserir: ");
            quantidade = scanner.nextInt();
            for (int i = 0 ; i < quantidade; i++) {
                System.out.println("Digite o " + (i+1) + "o ator");
                filme.addAtor(scanner.nextLine());
            }
            sistema.cadastrarFilme(filme);
            System.out.println("Cadastro realizado com sucesso!");
        }
    }

    /**
     * Esse método serve para cadastrar clientes.
     * Restrição: não devem ser cadastrados clientes com o mesmo CPF, isto é, o CPF é um identificador único;
     */
    public static void cadastrarCliente(Locadora sistema) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente que deseja cadastrar");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        if (sistema.buscarCliente(cpf) != null)
            System.out.println("Já existe um cliente com esse cpf. Tente novamente.");
        else {
            Cliente cliente = new Cliente(cpf);
            System.out.println("Preencha agora os outros campos para concluir o cadastro: ");
            System.out.println("Nome do cliente:");
            cliente.setNome(scanner.nextLine());
            System.out.println("Endereço: ");
            cliente.setEndereco(scanner.nextLine());
            sistema.cadastrarCliente(cliente);
            System.out.println("Cadastro concluído com sucesso!");
        }
    }
    /**
     * Esse método serve para alugar filmes.
     * Restrição: a locação só pode ser feita se, ambos, o filme e o cliente forem cadastrados e se o filme estiver disponível;
     */
    public static void alugarFilme(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu cpf: ");
        long cpf = scanner.nextLong();
        System.out.println("Digite o código do filme que você deseja alugar: ");
        int codigo = scanner.nextInt();
        if (sistema.buscarCliente(cpf) != null && sistema.buscarFilme(codigo) != null)
            sistema.locarFilme(cpf, codigo);
    }

    /**
     * Esse método serve para reservar filmes.
     * Restrição: só podem ser feitas reservas de filmescadastrados para clientes cadastrados e se não houver cópias do filme disponível,;
     */
    public static void reservarFilme(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu cpf: ");
        long cpf = scanner.nextLong();
        System.out.println("Digite o código do filme que você deseja reservar: ");
        int codigo = scanner.nextInt();
        if (sistema.buscarCliente(cpf) != null && sistema.buscarFilme(codigo) != null)
            sistema.reservarFilme(cpf, codigo);
        else
            System.out.println("Erro! Tente novamente.");

    }

    /**
     * Esse método serve para devolver um filme já alugado.
     * Restrição: a devolução só pode ser feita se, ambos, o filme e o cliente forem cadastrados e se a locação existir
     */
    public static void devolverFilme(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu cpf: ");
        long cpf = scanner.nextLong();
        System.out.println("Digite o código do filme que você deseja devolver: ");
        int codigo = scanner.nextInt();
        if (sistema.buscarCliente(cpf) != null && sistema.buscarFilme(codigo) != null)
            sistema.devolverFilme(cpf, codigo);
        else
            System.out.println("Erro! Tente novamente.");
    }

    /**
     * Esse método é responsável por atualizar o cadastro de um filme,
     * Restrição: o mesmo já deve estar cadastrado
     *
     */
    public static void atualizarFilme(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código do filme que você deseja atualizar: ");
        int codigo = scanner.nextInt();
        if (sistema.buscarFilme(codigo) != null){
            Filme filme = new Filme(codigo, null);
            System.out.println("Confirme os campos que deseja atualizar: Digite 1 caso queira atualizar o campo em questão ou 0 se não");
            System.out.println("Nome do filme: ");
            int auxiliar = scanner.nextInt();scanner.nextLine();
            if (auxiliar == 1) {
                 filme.setTitulo(scanner.nextLine());
            }
            System.out.println("Sinopse do filme: ");
            auxiliar = scanner.nextInt();scanner.nextLine();
            if (auxiliar == 1) {
                filme.setSinopse(scanner.nextLine());
            }
            System.out.println("Genero do filme: ");
            auxiliar = scanner.nextInt();scanner.nextLine();
            if (auxiliar == 1) {
                System.out.println("Digite quantos gêneros você quer adicionar: ");
                int i = scanner.nextInt();scanner.nextLine();
                for (int j = 0 ; j < i ; j++){
                    filme.addGenero(scanner.nextLine());
                }
            }
            System.out.println("Atores do filme: ");
            if (auxiliar == 1) {
                System.out.println("Digite quantos gêneros você quer adicionar: ");
                int i = scanner.nextInt();scanner.nextLine();
                for (int j = 0 ; j < i ; j++){
                    filme.addAtor(scanner.nextLine());
                }
            }
            System.out.println("Diretor do filme: ");
            auxiliar = scanner.nextInt();scanner.nextLine();
            if (auxiliar == 1) {
                filme.setDiretor(scanner.nextLine());
            }
            sistema.atualizarCadastroFilme(filme);

        }

    }

    /**
     * Esse método é responsável por atualizar o cadastro de um cliente,
     * Restrição: o mesmo já deve estar cadastrado
     *
     */
    public static void atualizarCliente(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o cpf do cliente que você deseja atualizar: ");
        long cpf = scanner.nextLong();
        if (sistema.buscarCliente(cpf) != null) {
            Cliente cliente = new Cliente(cpf);
            System.out.println("Confirme os campos que deseja atualizar: Digite 1 caso queira atualizar o campo em questão ou 0 se não");
            System.out.println("Nome do cliente: ");
            int auxiliar = scanner.nextInt();scanner.nextLine();
            if (auxiliar == 1) {
                cliente.setNome(scanner.nextLine());
            }
            System.out.println("Endereço: ");
            auxiliar = scanner.nextInt();scanner.nextLine();
            if (auxiliar == 1) {
                cliente.setEndereco(scanner.nextLine());
            }
            System.out.println("Atualização concluída com sucesso!");
            sistema.atualizarCadastroCliente(cliente);
        }
        else
            System.out.println("Não é possível atualizar um CPF não cadastrado. Tente novamente.");
    }

    /**
     * Esse método é responsável por buscar e retornar um cliente,
     * Restrição: o mesmo já deve estar cadastrado
     *
     */
    public static void buscarCliente(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o cpf do cliente que você deseja buscar: ");
        long cpf = scanner.nextLong();
        if (sistema.buscarCliente(cpf) != null) {
            Cliente cliente = sistema.buscarCliente(cpf);
            System.out.println(cliente.toString());
        }
        else
            System.out.println("Erro. Tente novamente.");

    }

    /**
     * Esse método é responsável por buscar e retornar um filme,
     * Restrição: o mesmo já deve estar cadastrado
     *
     */
    public static void buscarFilme(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código do filme que você deseja buscar: ");
        int codigo = scanner.nextInt();
        if (sistema.buscarFilme(codigo) != null) {
            Filme filme = sistema.buscarFilme(codigo);
            System.out.println(filme.toString());
        }
        else
            System.out.println("Erro. Tente novamente.");

    }

    /**
     * Imprime o histórico de locações realizadas por um determinado cliente.
     * Restrição: o mesmo já deve estar cadastrado.
     */
    public static void historicoCliente(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o cpf do cliente que você deseja ver o histórico: ");
        long cpf = scanner.nextLong();
        if (sistema.buscarCliente(cpf) != null)
            sistema.imprimirHistoricoLocacoes(cpf);

    }

    /**
     * Imprime, em ordem decrescente, a lista dos filmes mais locados, lista essa que contem top elementos, isto é, mostra os primeiros top filmes mais locados
     */
    public static void imprimirFilmesMaisLocados(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite quantas posições você quer na lista: ");
        int top = scanner.nextInt();
        sistema.imprimirFilmesMaisLocados(top);

    }

    public static void removerFilme(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o cpf do cliente que você deseja remover: ");
        long cpf = scanner.nextLong();
        if (sistema.buscarCliente(cpf) != null)
            sistema.removerCliente(cpf);
    }
    public static void removerCliente(Locadora sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código do filme que você deseja remover: ");
        int codigo = scanner.nextInt();
        if (sistema.buscarFilme(codigo) != null)
            sistema.removerFilme(codigo);

    }









}


