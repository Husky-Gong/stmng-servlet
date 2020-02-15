package com.sxt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

public class CheckCodeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7986776568427297379L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LineCaptcha checkObj = CaptchaUtil.createLineCaptcha(200, 100, 4, 100);
		
		String code = checkObj.getCode();
		
		req.getSession().setAttribute("code", code);
		
		checkObj.write(resp.getOutputStream());
	}
	
}
