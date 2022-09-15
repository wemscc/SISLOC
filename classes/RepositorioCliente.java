import java.util.Vector;

/**
 * Classe que implemente a interface IRepositorioCliente e que será utilizada como Repositório para objetos do tipo Cliente.
 * Contém métodos que nos permitem manipular esses objetos e o nosso repositório.
 */
public class RepositorioCliente implements IRepositorioCliente{
    /** Esse Vector é onde armazenamos todos os clientes.*/
    private Vector<Cliente> clientes = new Vector<Cliente>();

    /** Esse método é utilizado para cadastrar clientes.
     *  Primeiro, recebemos como parâmetro um objeto do tipo cliente, e então:
     *  Olhamos no nosso Vector, usando o método buscar, para checar se o cliente já está cadastrado.
     *  Caso esteja, o cliente não é cadastrado e retorna uma mensagem de erro. Caso não esteja, adicionamos esse cliente no nosso Vector que usamos de repositório.
     */
    @Override
    public void cadastrar(Cliente cliente) {
        if (buscar(cliente.getCpf()) == null){
            clientes.add(cliente);
        }
        else
            System.out.println("Esse CPF já foi cadastrado!");
    }

    /** Esse método é utilizado para buscar clientes (cadastrados). Recebemos como parâmetro um cpf e então:
     *  Andamos por todo o nosso Vector de clientes, comparando o campo cpf de cada objeto Cliente com o cpf que nos foi passado, para checar se o cliente já está cadastrado.
     *  Caso esteja, retornamos o objeto cliente. Caso não esteja, retornamos null.
     */
    @Override
    public Cliente buscar(long cpf) {
        for(Cliente cliente1 : clientes){
            if (cliente1.getCpf() == cpf){
                return cliente1;
            }
        }
        return null;
    }

    /** Esse método é utilizado para atualizar o cadastro de clientes. Recebemos como parâmetro um cliente e então:
     *  Usamos o método buscar(), passando como parâmetro o cpf desse objeto que recebemos agora, para checar se o cliente já está cadastrado;
     *  Caso esteja, atualizamos as informações do objeto cliente, trocando o objeto que já estava no Vector pelo novo que recebemos.
     *  Caso não esteja, retornamos uma mensagem de erro informando que não se pode atualizar um cliente não cadastrado.
     */
    @Override
    public void atualizar(Cliente cliente) {
        Cliente cliente1 = buscar(cliente.getCpf());
        if (cliente1 == null)
            System.out.println("Não é possível atualizar um cliente não cadastrado!");
        else{
             if (cliente.getEndereco().equals(null))
                     cliente.setEndereco(cliente1.getEndereco());
             if (cliente.getNome().equals(null))
                    cliente.setNome(cliente1.getNome());
        }
            clientes.set(clientes.indexOf(cliente1),cliente); // TESTAR ESSE !!!!!!
        }


    /** Esse método é utilizado para apagar o cadastro de clientes. Recebemos como parâmetro um cpf e então:
     *  Usamos o método buscar(), passando como parâmetro esse mesmo cpf que recebemos, para checar se o cliente já está cadastrado;
     *  Caso esteja, removemos o cliente do nosso Vector de Clientes.
     *  Caso não esteja, retornamos uma mensagem de erro informando que não foi possível realizar a operação, pois o cliente não cadastrado.
     */
    @Override
    public void deletar(long cpf) {
        if(buscar(cpf) != null){
            Cliente cliente1 = buscar(cpf);
            clientes.remove(cliente1);
        }
    }

    /** Esse método é utilizado para retornar o cadastro de todos os clientes.
     * Ele simplesmente retorna o nosso Vector que usamos para armazenar todos os objetos.
     */
    @Override
    public Vector<Cliente> listar() {
        return this.clientes;
    }
}

