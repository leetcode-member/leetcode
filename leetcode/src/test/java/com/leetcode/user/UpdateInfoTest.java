package com.leetcode.user;

import com.leetcode.model.dto.UpdateInfoRequestDTO;
import com.leetcode.web.mapper.UserMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.oracle.deploy.update.UpdateInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/26 20:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateInfoTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void mapperTest(){
        UpdateInfoRequestDTO dto = new UpdateInfoRequestDTO();
        dto.setNickname("hfdasfdasfhh");
        dto.setSex("1");
        boolean b = userMapper.updateInfo(dto, "1357259569548955649");
        System.out.println(b);
    }

    @Test
    public void test() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/user/update-info")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTQzNDQ1OTkyODcsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTM1NzI1OTU2OTU0ODk1NTY0OSJ9.GewHc4evwxkOv4lzccVWuFcDFc9A1mJH_8Lq3PhmKfA")
                .header("Content-Type", "application/json")
                .body("{\r\n  \"nickname\": \"fdsaf\",\r\n  \"password\": \"demofdasfData\",\r\n  \"sex\": \"1\",\r\n  \"avatar\": \"fdsaf\"\r\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
