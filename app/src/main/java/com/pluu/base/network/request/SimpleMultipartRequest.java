package com.pluu.base.network.request;

import android.content.Context;

import java.io.File;
import java.util.Map;

import com.pluu.base.network.AbstractResource;

/**
 * Simple Multipart Supported Request
 * @author nohhs
 */
public abstract class SimpleMultipartRequest extends AbstractResource {

	public SimpleMultipartRequest(Context context) {
		super(context);
	}

	@Override
	protected boolean isMultipart() {
		return true;
	}

	protected abstract Map<String, File> getMultipart();


}
