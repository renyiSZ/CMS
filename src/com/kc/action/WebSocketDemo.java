package com.kc.action;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;  

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;  
import javax.websocket.OnError;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  
import javax.websocket.Session;  
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;  

import com.kc.utils.*;

import net.sf.json.JSONObject;
   
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。  
@ServerEndpoint(value="/web/{username}")
public class WebSocketDemo {  
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
    private static int onlineCount = 0;  
       
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识  
    private static CopyOnWriteArraySet<WebSocketDemo> webSocketSet = new CopyOnWriteArraySet<WebSocketDemo>();  
    String uname; 
    String uhead;
   // String[] datastring;
    //与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session;  
    //private HttpSession httpSession;
    private static ArrayList users=new ArrayList();   
    /** 
     * 连接建立成功调用的方法 
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据 
     */  
    @OnOpen  
    public void onOpen(@PathParam("username")String username,Session session){
        this.session = session;  
        webSocketSet.add(this);     //加入set中  
        addOnlineCount();           //在线数加1  
        uname=username;
        System.out.println("name:"+uname+" head:"+uhead);
        users.add(uname);
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());  
        
    }  
       
    /** 
     * 连接关闭调用的方法 
     */  
    @OnClose  
    public void onClose(){  
        webSocketSet.remove(this);  //从set中删除  
        subOnlineCount();           //在线数减1   
        users.remove(uname);
        for(int i=0;i<users.size();i++){
        System.out.println(users.get(i));
        }
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());  
    }  
       
    /** 
     * 收到客户端消息后调用的方法 
     * @param message 客户端发送过来的消息 
     * @param session 可选的参数 
     */  
    @OnMessage  
    public void onMessage(String message, Session session) {  
        System.out.println("来自客户端的消息:" + message);  
        JSONObject obj = JSONObject.fromObject(message);   
        //群发消息  
        for(WebSocketDemo item: webSocketSet){               
            try {
            	obj.put("head",uhead);
            	obj.put("isSelf", item.session.equals(session));
            	obj.put("count", onlineCount);
            	obj.put("onlineusers",users.toString());
                item.sendMessage(obj.toString());
                
            } catch (IOException e) {  
                e.printStackTrace();  
                continue;  
            }  
        }  
    }  
       
    /** 
     * 发生错误时调用 
     * @param session 
     * @param error 
     */  
    @OnError  
    public void onError(Session session, Throwable error){  
        System.out.println("发生错误");  
        error.printStackTrace();  
    }  
       
    /** 
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。 
     * @param message 
     * @throws IOException 
     */  
    public void sendMessage(String message) throws IOException{  
        this.session.getBasicRemote().sendText(message);  
        //this.session.getAsyncRemote().sendText(message);  
    }  
   
    public static synchronized int getOnlineCount() {  
        return onlineCount;  
    }  
   
    public static synchronized void addOnlineCount() {  
    	WebSocketDemo.onlineCount++;  
    }  
       
    public static synchronized void subOnlineCount() {  
    	WebSocketDemo.onlineCount--;  
    }  
}  


