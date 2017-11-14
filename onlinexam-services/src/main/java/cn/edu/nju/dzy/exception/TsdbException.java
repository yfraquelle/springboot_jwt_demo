package cn.edu.nju.dzy.exception;

public class TsdbException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TsdbException (Exception ex){
		super(ex);
	}
	
	public TsdbException (String ex){
		super(ex);
	}

}
