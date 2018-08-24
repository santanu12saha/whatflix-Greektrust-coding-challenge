package org.santanu.santanubrains.whatflix.model;

import com.google.gson.annotations.SerializedName;

public class Cast extends CreditBase {

	@SerializedName("character")
	private String character;

	@SerializedName("order")
	private int order;

	@SerializedName("cast_id")
	private int castId;

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getCastId() {
		return castId;
	}

	public void setCastId(int castId) {
		this.castId = castId;
	}

}
