import java.util.Date;
public class Operacao {
    private Date data;
    private long cpf;
    private int codigo;
    private boolean ativo;

    public Operacao(long cpf, int codigo) {
        this.cpf = cpf;
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo)  {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Operacao{" +
                "data=" + data +
                ", cpf =" + cpf +
                ", código do filme=" + codigo +
                ", ativa=" + ativo +
                '}';
    }
}
