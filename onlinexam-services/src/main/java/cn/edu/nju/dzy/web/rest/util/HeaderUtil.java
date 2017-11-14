package cn.edu.nju.dzy.web.rest.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

/**
 * Utility class for HTTP headers creation.
 *
 */
public class HeaderUtil {

	private final static Logger log = LoggerFactory.getLogger(HeaderUtil.class);

	public static HttpHeaders createAlert(String message, String param) {
		HttpHeaders headers = new HttpHeaders();
		String msgStr=message;
		try {
			 msgStr = Base64Utils.encodeToUrlSafeString(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.debug("UTF-8", e);
		}
		headers.add("X-UT-Cloud-alert", msgStr);
		headers.add("X-UT-Cloud-params", param);
		return headers;
	}

	public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
		return createAlert("iChemMangApp." + entityName + ".created", param);
	}

	public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
		return createAlert("iChemMangApp." + entityName + ".updated", param);
	}

	public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
		return createAlert("iChemMangApp." + entityName + ".deleted", param);
	}

	public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-UT-Cloud-error", "error." + errorKey);
		headers.add("X-UT-Cloud-params", entityName);
		headers.add("X-UT-Cloud-message", defaultMessage);
		return headers;
	}

	public static HttpHeaders createFailureAlert(String errorKey, String defaultMessage) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-UT-Cloud-error", "error." + errorKey);
		headers.add("X-UT-Cloud-message", defaultMessage);
		return headers;
	}

	public static HttpHeaders createEntityReadAlert(String string, String unionid) {
		String param = "iChemMangApp";
		return createAlert("iChemMangApp." + ".read", param);
	}

	public static HttpHeaders createAlert(String msg) {
		HttpHeaders headers = new HttpHeaders();
		
		if (msg!= null){
			String msgStr=msg;
		
			try {
				msgStr = Base64Utils.encodeToUrlSafeString(msg.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.debug("UTF-8", e);
			}
			headers.add("X-UT-Cloud-message", msgStr);
		} else {
			headers.add("X-UT-Cloud-message", "null");
		}
	
		return headers;
	}
}
