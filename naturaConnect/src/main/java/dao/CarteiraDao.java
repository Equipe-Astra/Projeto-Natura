package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dto.ClientesDTO;
import entidades.Usuario;
import entidades.ClienteConsultor;

public class CarteiraDao {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public CarteiraDao() {
        emf = Persistence.createEntityManagerFactory("naturaConnect");
        em = emf.createEntityManager();
    }
    
    public Double buscaConsultor(Integer usuario) {
		try { 
			String sql = "SELECT codigo_consultor FROM Usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", usuario);

			if ((Double) query.getSingleResult() == null) {
				return null;
			}

			return (Double) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
    
    public List<ClientesDTO> getUsuarios(Integer usuario, boolean ativo, boolean inativo, boolean maisRecentes, boolean maisAntigos) {
        StringBuilder sql = new StringBuilder("SELECT u.nome_completo, u.logradouro, u.numero_casa, u.data_cadastro, t.telefone, c.status, MAX(o.data_compra) AS data_compra ")
                .append("FROM usuario u ")
                .append("LEFT JOIN cliente c ON u.id_cliente = c.id_cliente ")
                .append("LEFT JOIN ordem_de_compra o ON o.id_cliente = c.id_cliente ")
                .append("LEFT JOIN telefone t ON t.id_cliente = c.id_cliente ")
                .append("JOIN cliente_consultor cc ON u.id_cliente = cc.id_cliente ")
                .append("WHERE cc.codigo_consultor = :codigo_consultor ");

        if (ativo || inativo) {
            sql.append("AND (");
            if (ativo) {
                sql.append("c.status = 'ATIVO'");
            }
            if (inativo) {
                if (ativo) {
                    sql.append(" OR ");
                }
                sql.append("c.status = 'INATIVO'");
            }
            sql.append(") ");
        }

        sql.append("GROUP BY u.id, u.nome_completo, u.logradouro, u.numero_casa, u.data_cadastro, t.telefone, c.status ");

        if (maisRecentes) {
            sql.append("ORDER BY u.data_cadastro DESC ");
        } else if (maisAntigos) {
            sql.append("ORDER BY u.data_cadastro ASC ");
        }

        List<ClientesDTO> usuarios = new ArrayList<>();
        try {
            Double codigo_consultor = buscaConsultor(usuario);
            if (codigo_consultor == null) {
                return new ArrayList<>();
            }
            Query query = em.createNativeQuery(sql.toString());
            query.setParameter("codigo_consultor", codigo_consultor);
            List<Object[]> results = query.getResultList();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (Object[] result : results) {
                Date dataCadastro = (Date) result[3];
                String dataCadastroFormatada = dateFormat.format(dataCadastro);

                Date dataCompra = (Date) result[6];
                String dataCompraFormatada = (dataCompra != null) ? dateFormat.format(dataCompra) : null;

                ClientesDTO dto = new ClientesDTO(
                    (String) result[0],
                    (String) result[1],
                    (String) result[2],
                    dataCadastroFormatada,
                    (String) result[4],
                    (String) result[5],
                    dataCompraFormatada
                );
                usuarios.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}