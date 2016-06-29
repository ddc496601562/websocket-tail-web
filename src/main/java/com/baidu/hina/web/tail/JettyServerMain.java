package com.baidu.hina.web.tail;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import com.baidu.hina.web.tail.util.PropertyConfigUtil;

public class JettyServerMain {
    private static final Logger LOG = Log.getLogger(JettyServerMain.class);

    public static void main(String[] args) throws Exception {
        String host = PropertyConfigUtil.getHost();
        int port = PropertyConfigUtil.getPort();
        int minThreads = PropertyConfigUtil.getMinThreads();
        int maxThreads = PropertyConfigUtil.getMaxThreads();
        LOG.info("host={}, port={}, minThreads={}, maxThreads={}", host, port, minThreads, maxThreads);

        Server webServer = new Server();
        SelectChannelConnector listener = new SelectChannelConnector();
        listener.setAcceptQueueSize(128);
        listener.setResolveNames(false);
        listener.setUseDirectBuffers(false);
        listener.setHost(host);
        listener.setPort(port);
        webServer.addConnector(listener);
        QueuedThreadPool queue = new QueuedThreadPool();
        queue.setMinThreads(minThreads);
        queue.setMaxThreads(maxThreads);
        webServer.setThreadPool(queue);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setDisplayName("hina-tail-web");
        webAppContext.setWar("webapp");

        webServer.setHandler(webAppContext);
        webServer.start();
        webServer.join();
    }
}
