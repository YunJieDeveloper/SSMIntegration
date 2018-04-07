package com.ssm.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
/***
 * @Date 2018/3/29
 * @Description  注册socket.js、stomp.js资源,实现ServletContextAware接口,在页面上通过${socketJs}就可以引用js
 * @author zhanghesheng
 * */
@Component
public class RegistryJS implements ServletContextAware {

    public final static String GLOBAL_ENDPOINT = "platform-ws";
    private final static String SOCKET_JS = "socketJS";


    @Override
    public void setServletContext(ServletContext servletContext) {
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute(SOCKET_JS,buildSocketJSResource(contextPath));
    }

    private String buildSocketJSResource(String contextPath){
        String socketGloablJS = "<script type=\"text/javascript\" >var wsEndPoint = '" + GLOBAL_ENDPOINT + "';</script>";
        String socketJS = "<script type=\"text/javascript\" src=\"" + contextPath + "/websocket/js/sockjs.min.js\"></script>";
        String stompJS = "<script type=\"text/javascript\" src=\"" + contextPath + "/websocket/js/stomp.min.js\"></script>";
        return socketGloablJS + socketJS + stompJS;
    }
}
