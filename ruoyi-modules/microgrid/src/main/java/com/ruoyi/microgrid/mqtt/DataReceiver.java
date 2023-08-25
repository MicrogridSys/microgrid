package com.ruoyi.microgrid.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.microgrid.domain.*;
import com.ruoyi.microgrid.service.DataService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
@Service
public class DataReceiver extends ObjectMapper{

    @Autowired
    private DataService dataService;

    private static final String MQTT_BROKER = "tcp://localhost:1883";

    private static final String[] MQTT_TOPICS = {
            "microgrid/generator/#",
            "microgrid/pv/#",
            "microgrid/wind/#",
            "microgrid/energy/#",
            "microgrid/load/#",
            "microgrid/mg/#"
    }; // 定义多个主题

//    public static void main(String[] args) {
//        DataReceiver dataReceiver = new DataReceiver();
//        dataReceiver.receiverData();
//    }

    /**
    * spring启动时启动
    * 接收数据并处理数据
    * */
    @PostConstruct
    public void receiverData() throws MqttException {
        try {
            MqttClient client = new MqttClient(MQTT_BROKER, MqttClient.generateClientId(), new MemoryPersistence());
            client.connect();

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost:"+cause);
                }

                /*
                * 在这里处理接收到的数据：解析json，存储数据库等
                * */
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {

                    // 机组发送过来的数据
                    String data = new String(message.getPayload());
                    System.out.println("Received data from topic " + topic + ": " + data);

                    // 使用Jackson解析JSON字符串并返回对应的实体类
                    // 通过tools中的TimestampToDateUtils类进行时间戳转换成需要的Date格式
                    ObjectMapper objectMapper = new ObjectMapper();

//                    System.out.println(powerGen);
                    // 接收gen数据
                    if (topic.contains("microgrid/generator")) {
                        PowerGen powerGen = objectMapper.readValue(data, PowerGen.class);
//                        System.out.println(powerGen);
                        int i = dataService.insertPowerGen(powerGen);
                        System.out.println("gen是否成功：" + i);
                    }
                    // 接收pv数据
                    else if (topic.contains("microgrid/pv")) {
                        PowerPv powerPv = objectMapper.readValue(data, PowerPv.class);
                        int i = dataService.insertPowerPv(powerPv);
                        System.out.println("pv是否成功：" + i);
                    }
                    // 接收wind数据
                    else if (topic.contains("microgrid/wind")) {
                        PowerWind powerWind = objectMapper.readValue(data, PowerWind.class);
                        int i = dataService.insertPowerWind(powerWind);
                        System.out.println("wind是否成功：" + i);
                    }
                    // 接收energy数据
                    else if (topic.contains("microgrid/energy")) {
                        PowerEnergy powerEnergy = objectMapper.readValue(data, PowerEnergy.class);
                        int i = dataService.insertPowerEnergy(powerEnergy);
                        System.out.println("energy是否成功：" + i);
                    }
                    // 接收load数据
                    else if (topic.contains("microgrid/load")) {
                        Loads loads = objectMapper.readValue(data, Loads.class);
//                        System.out.println(loads);
                        int i = dataService.insertLoads(loads);
                        System.out.println("load是否成功：" + i);
                    }
                    // 接收mg数据
                    else if (topic.contains("microgrid/mg")) {
                        MainGrid mainGrid = objectMapper.readValue(data, MainGrid.class);
//                        System.out.println(mainGrid);
                        int i = dataService.insertMainGrid(mainGrid);
                        System.out.println("mg是否成功：" + i);
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // 消息传递完成回调
                }
            });

            // 订阅了哪些topic
//            client.subscribe(MQTT_TOPIC);
            for (String topic : MQTT_TOPICS) {
                client.subscribe(topic);
                System.out.println("Subscribed to topic: " + topic);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}