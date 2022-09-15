import java.util.Objects;

public class Cliente {
    private long cpf ;
    private String nome;
    private String endereco;

    public Cliente(long cpf) {
        this.cpf = cpf;
    }
    
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void imprimir(){
        System.out.println("Nome do cliente: " + getNome());
        System.out.println("Endereço do cliente: " + getEndereco());
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " \ncpf = " + cpf + '\'' + ", \nendereco='" + endereco + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf == cliente.cpf;
    }


}
