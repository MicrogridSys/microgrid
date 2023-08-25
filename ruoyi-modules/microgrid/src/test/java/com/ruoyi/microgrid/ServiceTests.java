package com.ruoyi.microgrid;

import com.ruoyi.microgrid.domain.Devices;
import com.ruoyi.microgrid.domain.PowerGen;
import com.ruoyi.microgrid.mapper.DevicesMapper;
import com.ruoyi.microgrid.mapper.LoadsMapper;
import com.ruoyi.microgrid.mapper.PowerGenMapper;
import com.ruoyi.microgrid.mapper.PowerPvMapper;
import com.ruoyi.microgrid.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ServiceTests {

    @Autowired
    private DataService dataService;
    @Autowired
    private PowerGenMapper powerGenMapper;
    @Autowired
    private PowerPvMapper powerPvMapper;
    @Autowired
    private LoadsMapper loadsMapper;
    @Autowired
    private DevicesMapper devicesMapper;

    private static final Random random = new Random();
    private static final int[] LOAD_STDPOWER = {40, 37, 35, 30, 33, 36, 40, 49, 53, 62, 70, 80, 72, 65, 54, 56, 63, 72, 80, 91, 82, 74, 66, 55};


    @Test
    void testPowerGen(){
//        PowerGen powerGen = new PowerGen();
//        String ter = "data_gen_002";
//        powerGen.setTerminalNo(ter);
//        powerGen.setTimestamp(new Date());
//        powerGen.setGenCurrent(new BigDecimal(40));
//        powerGen.setGenVoltage(new BigDecimal(500));
//        powerGen.setGenPower(new BigDecimal(20000));
//        dataService.insertPowerGen(powerGen);
//        List<?> genData = dataService.getGenData(1, 10);
//        for (Object gen: genData
//             ) {
//            System.out.println(gen);
//        }
        long total = dataService.getTotal();
        System.out.println(total);

    }

    @Test
    void testLoads(){
//        int i = dataService.insertLoads(new Loads(new Date(),BigDecimal.valueOf(23452.12332)));
//        System.out.println(new Loads(new Date(),BigDecimal.valueOf(23452.12332)));
        // 获取当前时段的标准功率
//        int hour = LocalDateTime.now().getHour();
//        int power = LOAD_STDPOWER[hour];
//        // 生成随机功率
//        double randomLoad = Double.valueOf(power) + random.nextDouble() * 10 - 5;
//        System.out.println(randomLoad);

    }

    @Test
    void testSocketIO(){
//        List<PowerGen> powerGenList = powerGenService.getRecentPowerGenData();
//        for (PowerGen powerGen : powerGenList) {
//            System.out.println(powerGen);
//        }
//        Date date = new Date();
//        System.out.println(date);
//        long genTimestamp = Instant.now().getEpochSecond();
//        System.out.println(genTimestamp);
//        String data = "{\"genNo\": 1, \"genTimestamp\": 1684591085, \"genPower\": 1056.92481, \"terminalNo\": 1}";
//        ObjectMapper objectMapper = new ObjectMapper();
//        PowerGen powerGen = null;
//        try {
//            powerGen = objectMapper.readValue(data, PowerGen.class);
//            System.out.println(powerGen);
//            Date date = new Date();
//            System.out.println(date);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        // 将新数据发送给前端
        List<PowerGen> newDataList = getDataFromDatabase();
        for (PowerGen newData : newDataList) {
            // 将数据实体类转换成json
            // 发送前端

        }
    }

    @Test
    void testPvTotal(){
//        BigDecimal totalPowerPvData = powerPvMapper.getTotalPowerPvData();
//        System.out.println(totalPowerPvData);
//        String a = "a";
//        String b = "b";
//        String c = a+b;
//        System.out.println(c);
    }

    @Test
    void testDeviceList(){
//        List<Devices> deviceList = deviceService.getDeviceList("gen");
//        for (Devices d: deviceList) {
//            System.out.println(d);
//        }
    }

    private List<PowerGen> getDataFromDatabase() {
        // 在这里编写数据库查询逻辑，获取最新的数据
        // 返回新数据的列表
        List<PowerGen> newDataList = dataService.getRecentPowerGenData();
        for (PowerGen powerGen:newDataList) {
            System.out.println(powerGen);
        }
        // ...
        return newDataList;
    }

    private static String genData(String genNo) {
        System.out.println(genNo);
        // 生成模拟数据
        String terminalNo = "data_gen_"+genNo;
        long timestamp = Instant.now().getEpochSecond();
        double genCurrent  = 10;
        double genVoltage = 400;
        // 功率
        double genPower = genCurrent * genVoltage;

        // 组装数据
        String data = String.format(
                "{\"terminalNo\": \"%s\", \"timestamp\": %d, \"genCurrent\": %.5f, \"genVoltage\": %.5f, \"genPower\": %.5f}",
                terminalNo, timestamp, genCurrent, genVoltage, genPower
        );

        return data;
    }
}
