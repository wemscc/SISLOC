public class Reserva extends Operacao{
    private int prioridade;

    public Reserva(long cpf, int codigo) {
        super(cpf, codigo);
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
