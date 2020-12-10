package com.pack.domain;

import java.io.Serializable;

public interface AbstractEntity<T> extends Serializable {
	public abstract T getId();
	public abstract void setId(T id);
}