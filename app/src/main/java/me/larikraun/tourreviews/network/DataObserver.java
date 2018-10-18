package me.larikraun.tourreviews.network;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * Author: Omolara Adejuwon
 * Date: 22/09/2018.
 */
public class DataObserver<T> implements Observer<DataWrapper<T>> {
	private ChangeListener<T> changeListener;
	private static final int ERROR_CODE = 0;
	
	public DataObserver (ChangeListener<T> changeListener) {
		this.changeListener = changeListener;
	}
	
	@Override
	public void onChanged (@Nullable DataWrapper<T> tDataWrapper) {
		if (tDataWrapper != null) {
			if (tDataWrapper.getApiException () != null) {
				changeListener.onException (tDataWrapper.getApiException ());
			} else {
				changeListener.onSuccess (tDataWrapper.getData ());
			}
			return;
		}
		//custom exceptionn to suite my use case
		changeListener.onException (new Exception ("I dont know what happened"));
	}
	
	public interface ChangeListener<T> {
		void onSuccess (T dataWrapper);
		
		void onException (Exception exception);
	}
}

