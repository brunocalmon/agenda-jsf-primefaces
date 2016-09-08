package br.com.agenda.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class StringUtil {
	
    public static String formatString(String mask, String value) {  
        try {  
            MaskFormatter formatter = new MaskFormatter(mask);  
            formatter.setValueContainsLiteralCharacters(false);  
            return formatter.valueToString(value);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    public static String desformatString(String mask, String value) {  
        try {  
            MaskFormatter desformatter = new MaskFormatter(mask);  
            desformatter.setValueContainsLiteralCharacters(false);  
            return desformatter.stringToValue(value).toString();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
}
