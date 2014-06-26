package common.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import websocket.WebSocketServer;

import common.Context.ProtocolsContext;
import common.config.Config;
import common.db.C3P0Utils;

/**
 * 
 * @Description 启服关服控制器
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class InitListener implements ServletContextListener{
	private WebSocketServer websocket=new WebSocketServer(8088);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		C3P0Utils.destroy();
		websocket.stop();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ProtocolsContext.parseCPackets();
		initConfig(arg0);
		websocket.start();
	}
	
	private void initConfig(ServletContextEvent sce){
		Config.CONFIG_DIR=sce.getServletContext().getRealPath("")+File.separator+"WEB-INF"+File.separator+File.separator+Config.CONFIG_DIR;
	}

}
