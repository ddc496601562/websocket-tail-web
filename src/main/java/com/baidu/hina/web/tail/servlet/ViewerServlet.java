package com.baidu.hina.web.tail.servlet;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import com.baidu.hina.web.tail.util.StreamUtil;


public class ViewerServlet extends WebSocketServlet {
	private static final long serialVersionUID = -1151103043909699131L;

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest req, String protocol) {
		return new StreamWebSocket(StreamUtil.createDummyStream());
	}
}