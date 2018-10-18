package me.larikraun.tourreviews.network;

/**
 * Author: Omolara Adejuwon
 * Date: 22/09/2018.
 */
public class DataWrapper<T> {
	private Exception apiException;
	private T data;
	
	public Exception getApiException () {
		return apiException;
	}
	
	public void setApiException (Exception apiException) {
		this.apiException = apiException;
	}
	
	public T getData () {
		return data;
	}
	
	public void setData (T data) {
		this.data = data;
	}
}
