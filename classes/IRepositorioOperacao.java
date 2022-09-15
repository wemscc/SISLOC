import java.util.List;
import java.util.Vector;

public interface IRepositorioOperacao {
    boolean isLocacao(Operacao operacao);
    boolean isReserva(Operacao operacao);
    void cadastrar(Operacao operacao);
    Vector<Reserva> buscarReservas(long cpf);
    Vector<Locacao> buscarLocacoes(long cpf);
    void deletarReserva(long cpf, int codigo);
    void deletarLocacao(long cpf, int codigo);
    Vector<Locacao> listarLocacao(long cpf);
    int numeroLocacoes(long cpf);
    int numeroLocacoes(int codigo);
    int numeroLocacoesAtivas(long cpf);
    int numeroLocacoesAtivas(int codigo);
    int numeroReservas(int codigo);
    long topPrioridade(int codigo);
    void atualizaReservas(long cpf, int codigo);
    Vector<Operacao> listaMaisLocado();
}
