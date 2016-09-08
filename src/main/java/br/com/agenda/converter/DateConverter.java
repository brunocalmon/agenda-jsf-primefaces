package br.com.agenda.converter;

import java.text.SimpleDateFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author bruno.calmon
 *
 */
@FacesConverter("dateConverter")
public class DateConverter implements Converter {
	private static final String DATA_MASCARA = "dd/MM/yyyy";

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		if (arg2 == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATA_MASCARA);
		return sdf.format(arg2);
	}
}