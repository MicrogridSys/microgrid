//package com.ruoyi.microgrid.tools.socketio;
//
//import com.corundumstudio.socketio.AckRequest;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.DataListener;
//import com.ruoyi.microgrid.domain.PowerGen;
//import com.ruoyi.microgrid.service.DataService;
//import io.socket.client.Socket;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
////@Configuration
//@Service
//public class SocketIOServerConfig {
//
////    @Value("${socketio.host}")
////    private String host;
////    private String host = "0.0.0.0";
////    private String host = "localhost";
////    @Value("${socketio.port}")
////    private Integer port;
////    private Integer port = 3000;
//
//    @Autowired
//    private DataService powerGenService;
//
//
////    @PostConstruct
//    public void socketIOServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname("localhost");
//        config.setPort(3000);
//
//        SocketIOServer server = new SocketIOServer(config);
//        server.addEventListener(Socket.EVENT_MESSAGE, String.class, new DataListener<String>() {
//            @Override
//            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
//                System.out.println("client data:" + data);
//                server.getBroadcastOperations().sendEvent(Socket.EVENT_MESSAGE, "hi");
//            }
//        });
//
//        server.start();
//        System.out.println("Socket.IO server started");
//        try {
//            Thread.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        server.stop();
//    }
//
//
//    // SpringBoot启动时启动
////    @PostConstruct
//    /*public SocketIOServer socketIOServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname(host);
//        config.setPort(port);
//        SocketIOServer server = new SocketIOServer(config);
//
//        // 监听对应事件请求，并进行处理
//        server.addEventListener("getGeneratorData", String.class, (client, data, ackSender) -> {
//            System.out.println("已调用对应事件处理函数");
//            // 在收到请求后进行数据库查询，获取新数据
//            List<PowerGen> newDataList = getDataFromDatabase();
//
//            // 将新数据发送给前端
//            for (PowerGen newData : newDataList) {
//                // 将数据实体类转换成json
//
//                // 发送前端
//                client.sendEvent("newGeneratorData", newData);
//            }
//        });
//
//
//        server.addConnectListener(client -> {
//            // 连接成功事件处理
//            System.out.println("Client connected: " + client.getSessionId());
//        });
//
//
//
//        server.addDisconnectListener(client -> {
//            // 连接断开事件处理
//            System.out.println("Client disconnected: " + client.getSessionId());
//        });
//
//        server.start();
//        System.out.println("Socket.IO server started");
//        return server;
//    }*/
//
//    // 模拟从数据库获取新数据的方法
//    private List<PowerGen> getDataFromDatabase() {
//        // 在这里编写数据库查询逻辑，获取最新的数据
//        // 返回新数据的列表
//        List<PowerGen> newDataList = powerGenService.getRecentPowerGenData();
//        for (PowerGen powerGen : newDataList) {
//            System.out.println("newDataList" + powerGen);
//        }
//        // ...
//        return newDataList;
//    }
//}
//
//
