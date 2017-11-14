package cn.edu.nju.dzy.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cn.edu.nju.dzy.web.rest.util.HeaderUtil;

public class ResourceController {

	protected <T> ResponseEntity<T> getNoAuthorResponse(T body) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.headers(HeaderUtil.createAlert(HttpStatus.UNAUTHORIZED.name(), "用户无权访问")).body(body);
	}

	protected <T> ResponseEntity<T> getNotFoundResponse(T body) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.headers(HeaderUtil.createAlert(HttpStatus.UNAUTHORIZED.name(), "无法找到指定对象"  ))
				.body(body);

	}

	protected <T> ResponseEntity<T> getConflictResponse(T body) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.headers(HeaderUtil.createAlert(HttpStatus.CONFLICT.name(), "指定的对象已经存在")).body(body);

	}
	
	protected <T> ResponseEntity<T> getConflictResponse(T body, String msg) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.headers(HeaderUtil.createAlert(msg)).body(body);

	}

	protected <T> ResponseEntity<T> getSysErrorResponse(T body, String exs) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.headers(HeaderUtil.createAlert(exs)).body(body);
	}

	

	protected <T> ResponseEntity<T> getSysErrorResponse(T body, int statusCode,String exs) {
		HttpStatus status = HttpStatus.valueOf(statusCode);
		return ResponseEntity.status(status).headers(HeaderUtil.createAlert(exs)).body(body);
	}

	protected <T> ResponseEntity<T> getOKResponse(T body, String msg) {
		return ResponseEntity.ok().body(body);
	}
	

	
	protected <T> ResponseEntity<T> getOKResponse(T body, HttpHeaders headers, String msg) {
		return ResponseEntity.ok().headers(headers).body(body);
	}
}
