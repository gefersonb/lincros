package com.pack.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.pack.domain.AbstractEntity;
import com.pack.util.StringUtils;

@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (!StringUtils.isNullOrEmpty(value)) {
			if (component != null) {
				if (component.getChildren() != null
						&& component.getChildren().size() > 0) {
					for (Object curItem : component.getChildren()) {
						if (curItem instanceof UISelectItems) {
							if (((UISelectItems) curItem).getValue() != null
									&& ((UISelectItems) curItem).getValue() instanceof List<?>) {
								List<Object> itens = (List<Object>) ((UISelectItems) curItem)
										.getValue();
								for (Object item : itens) {
									if (item instanceof AbstractEntity) {
										if (((AbstractEntity<?>) item).getId()
												.toString().equals(value)) {
											return item;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof AbstractEntity<?>) {
			return ((AbstractEntity<?>) value).getId().toString();
		} else if (value instanceof Integer) {
			return value.toString();
		} else if (value != null) {
			return value.toString().trim();
		}
		return "";
	}

}