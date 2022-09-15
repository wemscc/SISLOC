public class Locacao extends Operacao{
    private int periodo;

    public Locacao(long cpf, int codigo) {
        super(cpf, codigo);
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
}
