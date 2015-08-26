package com.pluu.androidnetworksample.jsoup;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by nohhs on 2015-08-26.
 */
public class OttoMainThreadBus extends Bus {

	private final Handler handler = new Handler(Looper.getMainLooper());

	@Override public void post(final Object event) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			super.post(event);
		} else {
			handler.post(new Runnable() {
				@Override
				public void run() {
					OttoMainThreadBus.super.post(event);
				}
			});
		}
	}
}
