package com.pluu.androidnetworksample.jsoup;

import com.squareup.otto.Bus;

/**
 * Created by nohhs on 2015-08-26.
 */
public final class OttoBusHolder {

	private static final Bus mBus = new OttoMainThreadBus();

	public static Bus get() {
		return mBus;
	}

}