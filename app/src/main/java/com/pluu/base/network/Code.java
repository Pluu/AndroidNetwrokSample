package com.pluu.base.network;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-29.
 */
@IntDef({Code.CODE_NONE, Code.CODE_SUCCESS, Code.CODE_SYSTEM_ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface Code {

//	public @interface Code {}

	public static final int CODE_NONE = -1;
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_SYSTEM_ERROR = 99;

}
