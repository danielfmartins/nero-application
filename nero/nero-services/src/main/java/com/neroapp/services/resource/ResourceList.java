package com.neroapp.services.resource;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class ResourceList<R extends Resource, T> extends Resource {

	private List<R> resourceList;

	protected ResourceList(List<T> values) {
		super();
		this.resourceList = new ArrayList<R>(values.size());
		this.addAll(values);
	}

	private void addAll(List<T> values) {
		try {
			if (!values.isEmpty()) {
				Constructor<R> constructor = resourceType().getConstructor(values.get(0).getClass());
				for (T model : values) {
					this.resourceList.add(constructor.newInstance(model));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected List<R> resourceList() {
		return this.resourceList;
	}

	@SuppressWarnings("unchecked")
	private Class<R> resourceType() {
		return (Class<R>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}