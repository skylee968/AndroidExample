package com.orangestudio.mobilereader.NavigationServe;

import android.graphics.drawable.Drawable;

public class EntryItem implements Item {

	private String title;
	private Drawable resource;
	private Class<?> className;
	private int classType;

	public EntryItem(String title, Drawable resource, Class<?> _className, int type) {
		this.setTitle(title);
		this.setResource(resource);
		this.setClassName(_className);
		this.setClassType(type);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setResource(Drawable resource) {
		this.resource = resource;
	}

	public Drawable getResource() {
		return resource;
	}

	@Override
	public ItemType itemType() {
		return ItemType.ENTRY;
	}


	public Class<?> getClassName() {
		return className;
	}

	public void setClassName(Class<?> className) {
		this.className = className;
	}

	public int getClassType() {
		return classType;
	}

	public void setClassType(int classType) {
		this.classType = classType;
	}
}
