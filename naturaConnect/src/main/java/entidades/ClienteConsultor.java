package entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class ClienteConsultor implements Serializable {
    private int idCliente;
    private double codigoConsultor;

    // Getters e Setters

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getCodigoConsultor() {
        return codigoConsultor;
    }

    public void setCodigoConsultor(double codigoConsultor) {
        this.codigoConsultor = codigoConsultor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteConsultor that = (ClienteConsultor) o;
        return idCliente == that.idCliente && Double.compare(that.codigoConsultor, codigoConsultor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, codigoConsultor);
    }
}
