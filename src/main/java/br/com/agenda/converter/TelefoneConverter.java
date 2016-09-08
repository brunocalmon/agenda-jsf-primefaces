package br.com.agenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.agenda.util.StringUtil;

/**
 * 
 * @author bruno.calmon
 *
 */
@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {
	private static final String TELEFONE_MASCARA = "(##) ####-####"; 
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		if (arg2 == null) {
			return "";
		}
		return StringUtil.formatString(TELEFONE_MASCARA, arg2.toString());
	}
}