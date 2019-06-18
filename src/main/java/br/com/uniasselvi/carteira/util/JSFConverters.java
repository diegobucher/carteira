package br.com.uniasselvi.carteira.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.util.MessageFactory;

import br.com.uniasselvi.carteira.entidade.Entidade;

public class JSFConverters {

	@FacesConverter(value = "localDateTimeConverter")
	public static class LocalDateTimeConverter implements Converter {
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			try {
				return LocalDateTime.parse(value, buildParser(component.getAttributes().getOrDefault("pattern", "").toString()));
			} catch (DateTimeParseException e) {
				throw new ConverterException(formatErrorMessage("Date/Time", context, component, value), e);
			}
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value instanceof String) {
				return (String) value;
			}
			LocalDateTime dateValue = (LocalDateTime) value;
			return primefacesSupport(component,
					dateValue.format(DateTimeFormatter.ofPattern(component.getAttributes().getOrDefault("pattern", "dd/MM/yyyy HH:mm:ss").toString())));
		}
	}

	@FacesConverter(value = "localDateConverter")
	public static class LocalDateConverter implements Converter {
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			try {
				return LocalDate.parse(value, buildParser(component.getAttributes().getOrDefault("pattern", "").toString()));
			} catch (DateTimeParseException e) {
				throw new ConverterException(formatErrorMessage("Date", context, component, value), e);
			}
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value instanceof String) {
				return (String) value;
			}
			LocalDate dateValue = (LocalDate) value;
			return primefacesSupport(component, dateValue.format(DateTimeFormatter.ofPattern(component.getAttributes().getOrDefault("pattern", "dd/MM/yyyy").toString())));
		}
	}

	private static String primefacesSupport(UIComponent component, String val) {
		if (component instanceof Calendar) {
			Calendar cal = (Calendar) component;
			cal.setValue(val);
		}
		return val;
	}

	private static FacesMessage formatErrorMessage(String which, FacesContext context, UIComponent component, String value) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR,
				String.format("%s Falha na conversão: %s - %s", which, (String) MessageFactory.getLabel(context, component), value), "");
	}

	private static DateTimeFormatter buildParser(String pattern) {
		DateTimeFormatterBuilder dtf = new DateTimeFormatterBuilder().parseLenient();
		dtf.appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy")).appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		if (StringUtils.isNotBlank(pattern)) {
			dtf.appendOptional(DateTimeFormatter.ofPattern(pattern));
		}
		return dtf.appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
	}

	@FacesConverter(value = "entityConverter")
	public static class EntityConverter implements Converter {
		@Override
		public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
			if (value == null || !value.matches("\\d+"))
				return null;

			return this.getAttributesForm(uiComponent).get(value);
		}

		@Override
		public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
			if (value != null && !value.equals("")) {
				Entidade entidade = (Entidade) value;
				if (entidade.getId() != null) {
					this.addAttribute(uiComponent, entidade);
					return entidade.getId().toString();
				}
				return value.toString();
			}
			return "";
		}

		private void addAttribute(UIComponent component, Entidade entidade) {
			this.getAttributesForm(component).put(entidade.getId().toString(), entidade);
		}

		private Map<String, Object> getAttributesForm(UIComponent component) {
			return component.getAttributes();
		}
	}
}