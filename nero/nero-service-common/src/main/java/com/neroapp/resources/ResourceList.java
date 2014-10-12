package com.neroapp.resources;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class ResourceList<T, R extends Resource<T>> extends Resource<List<T>> {

	private List<R> resourceList;
	private List<T> values;

	protected ResourceList(List<T> values) {
		super();
		this.values = values;
		this.resourceList = new ArrayList<R>();
		this.addAll(values);
	}

	private void addAll(List<T> values) {
		try {
			if (values != null && !values.isEmpty()) {
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
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Override
	public List<T> unwrap() {
		return this.values;
	}
}