package com.baidu.hina.web.tail.servlet;

import static com.baidu.hina.web.tail.util.ConstantUtil.LOG_TAIL_FLUSH_INTERVAL_CONFIG_KEY;
import static com.baidu.hina.web.tail.util.ConstantUtil.LOG_TAIL_FLUSH_INTERVAL_DEFAULT;
import static com.baidu.hina.web.tail.util.ConstantUtil.MB;
import static com.baidu.hina.web.tail.util.ConstantUtil.ROOT_LOG_DIR_CONFIG_KEY;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import com.baidu.hina.web.tail.util.FilePathValid;
import com.baidu.hina.web.tail.util.PropertyConfigUtil;

public class FileTailerServlet extends WebSocketServlet {

    private static final Logger LOG = Log.getLogger(FileTailerServlet.class);
    private static final long serialVersionUID = -7026451362362726993L;
    private String rootLogPath = null;
    private final long logTailFlushInterval;

    public FileTailerServlet() {
        Properties pMap = PropertyConfigUtil.getPropertiesInstance();
        rootLogPath = pMap.getProperty(ROOT_LOG_DIR_CONFIG_KEY);
        logTailFlushInterval = PropertyConfigUtil.getLong(pMap, LOG_TAIL_FLUSH_INTERVAL_CONFIG_KEY,
                LOG_TAIL_FLUSH_INTERVAL_DEFAULT);
        LOG.info("FileTailerServlet, rootLogPath={}", rootLogPath);
        if (rootLogPath == null || rootLogPath.equals("")) {
            LOG.info("FileTailerServlet rootLogPath is null!!");
            throw new RuntimeException("FileTailerServlet rootLogPath is null!!");
        }
    }

    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
        String logFileName = request.getParameter("logFile");
        if (logFileName == null || logFileName.equals("")) {
            throw new RuntimeException("blank log file!!!");
        }
        File logFile = new File(new File(rootLogPath), logFileName);
        if (!FilePathValid.valid(logFile.getAbsolutePath())) {
            throw new RuntimeException("unvalid file path, path=" + logFile.getAbsolutePath());
        }
        if (logFile.isDirectory()) {
            throw new RuntimeException("log file is directory, log-file=" + logFileName);
        }
        boolean isEnd = logFile.length() > 3 * MB;
        FileTailer tailer = new FileTailer(logFile, isEnd);
        return new StreamWebSocket(tailer.getStream(logTailFlushInterval));
    }

}
