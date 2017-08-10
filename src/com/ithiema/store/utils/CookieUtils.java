package com.ithiema.store.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (name.equals(cookieName)) {
				return cookie;
			}
		}
		return null;
	}

	public static boolean cookieIsExist(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (name.equals(cookieName)) {
				return true;
			}
		}
		return false;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (name.equals(cookieName)) {
				String value = cookie.getValue();
				return value;
			}
		}
		return null;
	}

	public static void cookieSelected(HttpServletRequest request, HttpServletResponse response, String selectedValue,
			String key, String value) {
		if (selectedValue != null) {
			Cookie cookieKey = new Cookie(key, value);
			cookieKey.setPath("/");
			cookieKey.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookieKey);
		} else {
			// if (CookieUtils.cookieIsExist(request,key)) {
			// }
			Cookie cookieKey = new Cookie(key, key);
			cookieKey.setPath("/");
			cookieKey.setMaxAge(0);
			response.addCookie(cookieKey);
		}
	}
}
