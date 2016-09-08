package br.com.agenda.validacoes;

import java.util.List;

/**
 * Validacoes
 * 
 * @author bruno.calmon
 *
 */
public class Validacoes {

	public static final Integer TRES = 3;
	public static final Integer DEZ = 10;
	public static final Integer CINQUENTA = 50;

	protected Validacoes() {
	}

	/**
	 * 
	 * @param valor
	 * @return Boolean
	 */
	public static Boolean nullOrEmpty(Object valor) {
		return null == valor;
	}

	/**
	 * 
	 * @param valor
	 * @return Boolean
	 */
	public static Boolean nullOrEmpty(List<Object> valor) {
		return null == valor || valor.isEmpty();
	}

	/**
	 * 
	 * @param valor
	 * @return Boolean
	 */
	public static Boolean nullOrEmpty(String valor) {
		return null == valor || valor.isEmpty();
	}

	/**
	 * 
	 * @param valor
	 * @return Boolean
	 */
	public static Boolean nullOrEmpty(Integer valor) {
		return null == valor;
	}

	/**
	 * 
	 * @param valor
	 * @return Boolean
	 */
	public static Boolean nullOrEmpty(Long valor) {
		return null == valor;
	}

	/**
	 * 
	 * @param valor
	 * @return Boolean
	 */
	public static Boolean nullOrEmpty(Float valor) {
		return null == valor;
	}

	/**
	 * 
	 * @param valor
	 * @return Boolean
	 */
	public static Boolean nullOrEmpty(Double valor) {
		return null == valor;
	}
}
