package com.pluu.base.network;

import java.util.List;

/**
 * Created by PLUUSYSTEM on 2015-04-21.
 */
public interface IResource {
	/**
	 * Get Response Result - Code
	 * @return code
	 */
	int getCode();
	/**
	 * Get Response Result - Message
	 * @return Message
	 */
	String getMessage();
	/**
	 * Is Request Result
	 * @return true / false
	 */
	boolean isSuccess();
	/**
	 * Get Response Result - List
	 * @return list
	 */
	List<?> getList();
	/**
	 * Get Response Result - List Count
	 * @return the number of elements in this List
	 */
	int getListCount();

	Object getItem();
}
