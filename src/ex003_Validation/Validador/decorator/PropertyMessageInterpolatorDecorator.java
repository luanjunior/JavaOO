package ex003_Validation.Validador.decorator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.MessageInterpolator;


public class PropertyMessageInterpolatorDecorator implements MessageInterpolator {

    private static final Pattern MESSAGE_PARAMETER_PATTERN = Pattern.compile( "(\\{[^\\}]+?\\})" );
    
    private MessageInterpolator delegate;

    public PropertyMessageInterpolatorDecorator(MessageInterpolator delegate) {
		super();
		this.delegate = delegate;
	}
    
    @Override
    public String interpolate(String message, Context context, Locale locale) {
    	// Simplesmente delega a interpolação
    	return replacePropertyNameWithPropertyValues(delegate.interpolate(message, context, locale), context.getValidatedValue());
    }

	@Override
    public String interpolate(String message, Context context) {
        return replacePropertyNameWithPropertyValues(delegate.interpolate(message, context), context.getValidatedValue());
    }

	private String replacePropertyNameWithPropertyValues(String resolvedMessage, Object validatedValue) {
		Matcher matcher = MESSAGE_PARAMETER_PATTERN.matcher(resolvedMessage);
		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String parameter = matcher.group(1);

			String propertyName = parameter.replace("{", "");
			propertyName = propertyName.replace("}", "");

			PropertyDescriptor desc = null;
			try {
				desc = new PropertyDescriptor(propertyName, validatedValue.getClass());
			} catch (IntrospectionException ignore) {
				matcher.appendReplacement(sb, parameter);
				continue;
			}

			try {
				Object propertyValue = desc.getReadMethod().invoke(validatedValue);
				matcher.appendReplacement(sb, propertyValue.toString());
			} catch (Exception ignore) {
				matcher.appendReplacement(sb, parameter);
			}
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}