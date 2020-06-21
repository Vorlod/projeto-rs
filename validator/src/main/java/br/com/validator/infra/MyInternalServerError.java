package br.com.validator.infra;

public class MyInternalServerError extends RuntimeException {
	
	private static final long serialVersionUID = 7251499677993258437L;
	
	private String code;
	private Object[] params;

	public MyInternalServerError(String code, Object... params) {
		this.code = code;
		this.params = params;
	}

	public String getCode() {
		return code;
	}

	public Object[] getParams() {
		return params;
	}
}
