package com.youku.share.crowdfunding.web.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	@InitBinder("page")
	public void initBinder2(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("page.");
	}
}
