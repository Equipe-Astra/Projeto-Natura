package entidades;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioTagId implements Serializable {

    private int usuarioId;
    private int idTag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioTagId that = (UsuarioTagId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(idTag, that.idTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, idTag);
    }
}
