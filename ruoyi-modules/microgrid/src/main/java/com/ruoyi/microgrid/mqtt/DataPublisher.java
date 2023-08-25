package com.ruoyi.microgrid.mqtt;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//@Component
public class DataPublisher {
    private static final String MQTT_BROKER = "tcp://localhost:1883";
    private static final String[] MQTT_TOPICS = {
            "microgrid/generator/gen001", "microgrid/generator/gen002",
            "microgrid/pv/pv001",
            "microgrid/wind/wind001",
            "microgrid/energy/ene001",
            "microgrid/load/load001",
            "microgrid/mg/mg001"
    }; // 定义多个主题
    private static final int INTERVAL = 5000; // 5秒
    private static final int[] LOAD_STDPOWER = {40, 36, 30, 33, 36, 40, 49, 53, 62, 70, 80, 72, 65, 54, 56, 63, 72, 80, 91, 82, 74, 70, 62, 50};
    private static final double[] PV_STDPOWER = {0, 0, 0, 0, 0, 1.33, 6.4, 10.67, 13.87, 20, 26.13, 26.67, 24.53, 19.73, 16, 13.87, 9.33, 5.33, 0, 0, 0, 0, 0, 0};
    private static final double[] WIND_STDPOWER = {25.33, 19.73, 18.13, 16.53, 10.67, 12.27, 15.47, 16.53, 17.87, 26.13, 25.33, 32, 33.6, 25.07, 37.33, 34.13, 25.07, 26.13, 25.33, 16.53, 19.73, 22.93, 28.8, 23.47};
    private static final Random random = new Random();

    public static void main(String[] args) {
        DataPublisher dataPublisher = new DataPublisher();
        dataPublisher.startPublishing();
    }

    /*
    * spring启动时启动
    * 在此处发布数据
    * */
//    @PostConstruct
    public void startPublishing() {
        try {
            MqttClient client = new MqttClient(MQTT_BROKER, MqttClient.generateClientId(), new MemoryPersistence());
            client.connect();

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    for (String topic : MQTT_TOPICS) {
                        String data = null;
                        // 拆分topic，如[microgrid, generator, gen001]
                        String[] topicNames = topic.split("/");
                        // 拆分机组名，如[gen, 001]
                        String[] powerSet =  topicNames[topicNames.length-1].split("(?<=\\D)(?=\\d)");

                        // 发布generator数据
                        if (topic.contains("microgrid/generator")) {
                            String genNo = powerSet[powerSet.length-1];
                            data = genData(genNo);
                            // 发布数据到MQTT主题
                            try {
                                client.publish(topic, new MqttMessage(data.getBytes()));
                                System.out.println("publish topic " + topic + ": " + data);
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        }
                        // 发布pv数据
                        else if (topic.contains("microgrid/pv")) {
                            String pvNo = powerSet[powerSet.length-1];
                            data = pvData(pvNo);
                            // 发布数据到MQTT主题
                            try {
                                client.publish(topic, new MqttMessage(data.getBytes()));
                                System.out.println("publish topic " + topic + ": " + data);
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        }
                        // 发布wind数据
                        else if (topic.contains("microgrid/wind")) {
                            String windNo = powerSet[powerSet.length-1];
                            data = windData(windNo);
                            // 发布数据到MQTT主题
                            try {
                                client.publish(topic, new MqttMessage(data.getBytes()));
                                System.out.println("publish topic " + topic + ": " + data);
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        }
                        // 发布energy数据
                        else if (topic.contains("microgrid/energy")) {
                            String eneNo = powerSet[powerSet.length-1];
                            data = energyData(eneNo);
                            // 发布数据到MQTT主题
                            try {
                                client.publish(topic, new MqttMessage(data.getBytes()));
                                System.out.println("publish topic " + topic + ": " + data);
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        }
                        // 发布load数据
                        else if (topic.contains("microgrid/load")) {
                            data = loadData();
                            // 发布数据到MQTT主题
                            try {
                                client.publish(topic, new MqttMessage(data.getBytes()));
                                System.out.println("publish topic " + topic + ": " + data);
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        }
                        // 发布grid_main数据
                        else if (topic.contains("microgrid/mg")) {
                            String mgNo = powerSet[powerSet.length-1];
                            data = mainGridData(mgNo);
                            // 发布数据到MQTT主题
                            try {
                                client.publish(topic, new MqttMessage(data.getBytes()));
                                System.out.println("publish topic " + topic + ": " + data);
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }, 0, INTERVAL);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

/**参与调度的数据********************************************************************************************************/
    /**
     * 生成gen数据
     *
     * @param genNo 编号
     * @return 组成json格式的数据
     */
    private static String genData(String genNo) {
        // 生成模拟数据
        String terminalNo = "data_gen_"+genNo;
        long timestamp = Instant.now().getEpochSecond();
        double genCurrent  = 10 + generatorRandomDeviation();
        double genVoltage = 400 + generatorRandomDeviation();
        // 功率
        double genPower = genCurrent * genVoltage;

        // 组装数据
        String data = String.format(
                "{\"terminalNo\": \"%s\", \"timestamp\": %d, \"genCurrent\": %.5f, \"genVoltage\": %.5f, \"genPower\": %.5f}",
                terminalNo, timestamp, genCurrent, genVoltage, genPower
        );

        return data;
    }

    /**
     * 生成energy数据
     * @param eneNo
     * @return
     */
    private String energyData(String eneNo) {
        // 生成模拟数据
        String terminalNo = "data_energy_"+eneNo;
        long timestamp = Instant.now().getEpochSecond();
        double eneCurrent  = 10 + generatorRandomDeviation();
        double eneVoltage = 400 + generatorRandomDeviation();
        // 功率
        double enePower = eneCurrent * eneVoltage;
        double eneContain = 50000 - enePower;

        // 组装数据
        String data = String.format(
                "{\"terminalNo\": \"%s\", \"timestamp\": %d, \"eneCurrent\": %.5f, \"eneVoltage\": %.5f, \"enePower\": %.5f, \"eneContain\": %.5f}",
                terminalNo, timestamp, eneCurrent, eneVoltage, enePower, eneContain
        );

        return data;
    }

    /**
     * 配电网数据
     * @param mgNo 配电网编号
     * @return 组成json格式的数据
     */
    private String mainGridData(String mgNo) {
        // 生成模拟数据
        String terminalNo = "data_mg_"+mgNo;
        long timestamp = Instant.now().getEpochSecond();
        double mgCurrent  = 10 + generatorRandomDeviation();
        double mgVoltage = 220 + generatorRandomDeviation();
        // 功率
        double mgPower = mgCurrent * mgVoltage;

        // 组装数据
        String data = String.format(
                "{\"terminalNo\": \"%s\", \"timestamp\": %d, \"mgCurrent\": %.5f, \"mgVoltage\": %.5f, \"mgPower\": %.5f}",
                terminalNo, timestamp, mgCurrent, mgVoltage, mgPower
        );

        return data;
    }

/**不参与调度的数据*******************************************************************************************************/
    /**
     * 生成load数据
     * @return 组成json格式的数据
     */
    private String loadData() {
        long timestamp = Instant.now().getEpochSecond();
        double loadsPower = loadDataByTime();
        String data = String.format(
                "{\"timestamp\": %d, \"loadsPower\": %.5f}",
                timestamp, loadsPower
        );
        return  data;
    }

    /**
     * 生成pv数据
     *
     * @param pvNo 编号
     * @return 组转成json格式的数据
     */
    private static String pvData(String pvNo) {
        // 生成模拟数据
        String terminalNo = "data_pv_"+pvNo;
        long timestamp = Instant.now().getEpochSecond();
        // 生成随机偏差数据
        double pvPower = pvDataByTime();
        double pvVoltage, pvCurrent;
        if (pvPower == 0){
            pvVoltage = 0;
            pvCurrent = 0;
        }else {
            pvVoltage = 400 + generatorRandomDeviation();
            pvCurrent = pvPower / (1.732 * pvVoltage);
        }

        // 组装数据
        String data = String.format(
                "{\"terminalNo\": \"%s\", \"timestamp\": %d, \"pvCurrent\": %.5f, \"pvVoltage\": %.5f, \"pvPower\": %.5f}",
                terminalNo, timestamp, pvCurrent, pvVoltage, pvPower
        );

        return data;
    }

    /**
     * 生成wind数据
     *
     * @param windNo 编号
     * @return 组转成json格式的数据
     */
    private static String windData(String windNo) {
        // 生成模拟数据
        String terminalNo = "data_wind_"+windNo;
        long timestamp = Instant.now().getEpochSecond();
        double windPower = windDataByTime();
        double windVoltage = 400 + generatorRandomDeviation();
        double windCurrent  = windPower / (1.732 * windVoltage);
        // 功率

        // 需更改设计
        // 组装数据
        String data = String.format(
                "{\"terminalNo\": \"%s\", \"timestamp\": %d, \"windCurrent\": %.5f, \"windVoltage\": %.5f, \"windPower\": %.5f}",
                terminalNo, timestamp, windCurrent, windVoltage, windPower
        );

        return data;
    }

/**其他工具函数**********************************************************************************************************/
    /**
     * 根据时间生成负荷数据
     * @return loadData
     */
    private static double loadDataByTime(){
        // 读取时段
        int hour = LocalDateTime.now().getHour();
        // 根据时间找到对应的标准负荷
        int stdLoad = LOAD_STDPOWER[hour] * 1000; // KW ==> W
        // 在标准负荷下生成随机偏差负荷数据
        double loadData = Double.valueOf(stdLoad) + generatorRandomDeviation();

        return loadData;
    }

    /**
     * 根据时间生成pv数据
     * @return pvData
     */
    private static double pvDataByTime() {
        int hour = LocalDateTime.now().getHour();
        double stdPV = PV_STDPOWER[hour] * 1000; // KW ==> W
        if (stdPV == 0){
            return 0;
        }
        return stdPV + generatorRandomDeviation();
    }

    /**
     * 根据时间生成wind数据
     * @return windData
     */
    private static double windDataByTime(){
        int hour = LocalDateTime.now().getHour();
        double stdWind = WIND_STDPOWER[hour] * 1000; // KW ==> W
        return stdWind + generatorRandomDeviation();
    }

    /**
     * 生成-5到5之间的偏差值
     * @return 偏差值
     */
    private static double generatorRandomDeviation(){
        return random.nextDouble() * 10 - 5;
    }






//    /**
//     * 通过机组编号绑定对应终端
//     *
//     * @param dataNo 机组编号
//     * @return 对应终端编号
//     */
//    private static int terminalNo(int dataNo){
//        int terminalNo=-1;
//
//        // 待改进
//        // 通过机组编号绑定对应终端
//        if (dataNo == 0){
//            terminalNo = 0;
//        } else if (dataNo == 1){
//            terminalNo = 1;
//        } else if (dataNo == 2) {
//            terminalNo = 2;
//        }else {
//            terminalNo = 3;
//        }
//        return terminalNo;
//    }
}
