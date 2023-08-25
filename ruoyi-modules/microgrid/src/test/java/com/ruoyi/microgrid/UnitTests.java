package com.ruoyi.microgrid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.microgrid.domain.PowerGen;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitTests {
    @Test
    public void test01(){
        String a = "a";
        System.out.println(a);
    }

    @Test
    public void test02(){
        Map<String, Object> data = new HashMap<>();
        data.put("name", "John");
        data.put("age", 30);
        data.put("city", "New York");

        // 将 AjaxResult 对象转换为 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(data);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testDate(){
//        for (PowerGen powerGen : powerGenService.getRecentPowerGenData()) {
//            System.out.println(powerGen);
//        }
//
//        String timestampString = "1684590044";
//        long timestamp = Long.parseLong(timestampString) * 1000; // 将秒转换为毫秒
//        Date date = new Date(timestamp);
//        System.out.println(date);
//        for (PowerGen recentPowerGenDatum : powerGenMapper.getRecentPowerGenData(date)) {
//            System.out.println(recentPowerGenDatum);
//        }

//        Calendar calendar = Calendar.getInstance();
//
//        // 将 Calendar 的时间减去 5 秒
//        calendar.add(Calendar.SECOND, -5);
//
//        // 获取前5秒时的时间
//        Date lastDate = calendar.getTime();
//
//        System.out.println(lastDate);

        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        int hour = date.getHour();
        System.out.println(hour);
    }

    @Test
    public void test03(){
        String str = "microgrid/generator/gen012";
        String[] strs = str.split("/");
        for (String str1 : strs) {
            System.out.println(str1);
        }
        String strx = strs[strs.length-1];
        // gen001 ==> gen_001
//        String s = strx.replaceFirst("(\\D+)(\\d+)", "$1_$2");
        String[] s = strx.split("(?<=\\D)(?=\\d)");

//        boolean contains = str.contains("microgrid/generator");
//        System.out.println(contains);
        String s1 = genData(s[s.length - 1]);
        System.out.println(s1);
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
