package com.revature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.RecController;

public class JSONRequestHelper {

	public static void process(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println(req.getRequestURI());

		switch (req.getRequestURI()) {

		case "/project-1---ers-app-mroszel/recSubmit.json":
			RecController.getRec(req, res);
			break;
		case "/project-1---ers-app-mroszel/pendSubmit.json":
			RecController.getPend(req,res);
			break;
		case "/project-1---ers-app-mroszel/appSubmit.json":
			RecController.getApp(req,res);
			break;
		case "/project-1---ers-app-mroszel/denSubmit.json":
			RecController.getDen(req,res);
		case "/project-1---ers-app-mroszel/emprecSubmit.json":
			RecController.empgetRec(req, res);
			break;
		default:
			break;
		}
	}
}
