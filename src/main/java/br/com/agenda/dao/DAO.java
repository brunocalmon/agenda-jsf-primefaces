package br.com.agenda.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Entidade;

/**
 * 
 * @author bruno.calmon
 *
 * @param <T>
 */
public abstract class DAO<T extends Entidade> {

	private static final Logger LOGGER = Logger.getLogger(DAO.class);

	@PersistenceContext
	EntityManager em;

	private Class<T> classeEntidade = null;

	/**
	 * 
	 * @param entidade
	 */
	public void inserir(T entidade) {
		em.persist(entidade);
	}

	/**
	 * 
	 * @param entidade
	 */
	public void atualizar(T entidade) {
		em.merge(entidade);
	}

	/**
	 * 
	 * @param entidade
	 */
	public void remover(T entidade) {
		em.remove(em.find(entidade.getClass(), entidade.getPk()));
	}

	/**
	 * 
	 * @param entidade
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT e FROM " + getClasseEntidade().getSimpleName() + " e ");
		try {
			Query query = em.createQuery(sql.toString());
			return query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	public Class<T> getClasseEntidade() {
		if (this.classeEntidade == null) {
			try {
				Type tipoSuperClasseGenerica = getClass().getGenericSuperclass();
				Field tipoArgumentoAtributoAtual = tipoSuperClasseGenerica.getClass()
						.getDeclaredField("actualTypeArguments");
				tipoArgumentoAtributoAtual.setAccessible(true);
				Type[] tipoArgumentoValorAtual = (Type[]) tipoArgumentoAtributoAtual.get(tipoSuperClasseGenerica);
				this.classeEntidade = (Class<T>) tipoArgumentoValorAtual[0];
			} catch (Exception t) {
				LOGGER.info(t);
				this.classeEntidade = null;
			}
		}
		return this.classeEntidade;
	}
}
