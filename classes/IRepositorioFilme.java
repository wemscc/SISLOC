import java.util.Vector;

public interface IRepositorioFilme {
    void cadastrar(Filme filme);
    Filme buscar(int codigo);
    void atualizar(Filme filme);
    void deletar(int codigo);
    Vector<Filme> listar();
}
