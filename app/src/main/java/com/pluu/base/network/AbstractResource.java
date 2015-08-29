package com.pluu.base.network;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * Base Abstract Request Class
 * Created by PLUUSYSTEM on 2015-04-21.
 */
public abstract class AbstractResource implements IResource {
	private final String TAG = AbstractResource.class.getSimpleName();

	/**
	 * Network Callback Interface
	 * @author nohhs
	 */
	public interface Callback {
		void onResult(IResource data);
	}

	// Request Callback
	private Callback mCallback;
	// Volley 통신 Request Queue

	// //////////////////////////////////////////////////
	// Network Resource
	// //////////////////////////////////////////////////

	private final int TIME_OUT = 1000 * 5;

	// Request 결과 코드
	@Code
	private int mCode = Code.CODE_NONE;

	/** Response Message */
	private String mMessage;

	private final Context context;

	public AbstractResource(Context context) {
		this.context = context;
		if (context == null) {
			throw new IllegalStateException("Context Is Null");
		}
	}

	private String getErrorMsg() {
		// TODO : Error Message
		return null;
	}

	// //////////////////////////////////////////////////
	// Network Resource
	// //////////////////////////////////////////////////

	/** Server URL */
	private String getURL() {
		// TODO : Generate URL
		return null;
	}

	/**
	 * Set Response Data
	 * @param content Response Data
	 */
	private void setResponseContent(JSONObject content) {
		// TODO : Response Parse

		notifyResult();
	}

	/**
	 * Call SystemError Callback
	 * @param msg Message
	 */
	private void notifySystemErrorResult(String msg) {
		mCode = Code.CODE_SYSTEM_ERROR;
		mMessage = msg;
		notifyResult();
	}

	/** Call Callback */
	private void notifyResult() {
		if (mCallback != null) {
			mCallback.onResult(this);
		}
	}

	// //////////////////////////////////////////////////
	// Callback Resource
	// //////////////////////////////////////////////////

	protected void setCode(@Code int code) {
		this.mCode = code;
	}

	protected void setMessage(String message) {
		this.mMessage = message;
	}

	@Code
	@Override
	public int getCode() {
		return mCode;
	}

	@Override
	public String getMessage()
	{
		return mMessage;
	}

	@Override
	public boolean isSuccess()
	{
		return mCode == Code.CODE_SUCCESS;
	}

	@Override
	public Object getItem() {
		return null;
	}

	@Override
	public List<?> getList()
	{
		return null;
	}

	@Override
	public int getListCount()
	{
		return 0;
	}

	// //////////////////////////////////////////////////
	// Request
	// //////////////////////////////////////////////////

	/**
	 * Request Start
	 * @param callback Callback
	 */
	public void request(Callback callback) {
		request(callback, TAG);
	}

	/**
	 * Request Start
	 * @param callback Callback
	 * @param tag Request Tag
	 */
	public void request(Callback callback, String tag) {
		// TODO : Request Start Process
	}

	/**
	 * Get Request Parameter
	 * @return Request Parameter
	 */
	private Map<String, String> getRequestContent() {
		Map<String, String> params = new HashMap<>();

		// 기본 통신 파라매터
		// TODO : Default Param

		// 추가 파라매터
		params.putAll(makeParam());

		Log.i(TAG, "Request [ " + params.toString() + " ]");
		return params;
	}

	// TODO : Response Listener

	// TODO : Error Response Listener

	// //////////////////////////////////////////////////
	// Abstract Method
	// //////////////////////////////////////////////////

	/**
	 * 통신 파라매터 설정
	 * @return Custom Parameter
	 */
	protected abstract Map<String, String> makeParam();

	/**
	 * Parser Response Data
	 * @param param response
	 */
	protected abstract void parse(JSONObject param);

	/**
	 * Multipart Support 유무
	 * @return true/false
	 */
	protected boolean isMultipart() { return false; }

	/**
	 * Multipart Data
	 * @return Multipart Data Map
	 */
	protected Map<String, File> getMultipart() { return null; }

}
