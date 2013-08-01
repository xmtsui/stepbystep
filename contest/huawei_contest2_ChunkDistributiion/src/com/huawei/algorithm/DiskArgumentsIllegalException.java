package com.huawei.algorithm;

/**
 * <p>Title: 自定义异常1</p>
 *
 * <p>Description: 用于检查初时配置参数的正确性作</p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Company: Huawei</p>
 * 
 * @author cuixiaomin
 * @version v1.0
 */
public class DiskArgumentsIllegalException extends Exception {
	private static final long serialVersionUID = 4709147712708961854L;

	/**
     * Constructs an <code>ArgumentsIllegalException</code> with no 
     * detail message. 
     */
    public DiskArgumentsIllegalException() {
	super();
    }

    /**
     * Constructs an <code>IndexOutOfBoundsException</code> with the 
     * specified detail message. 
     *
     * @param   s   the detail message.
     */
    public DiskArgumentsIllegalException(String s) {
	super(s);
    }
}
