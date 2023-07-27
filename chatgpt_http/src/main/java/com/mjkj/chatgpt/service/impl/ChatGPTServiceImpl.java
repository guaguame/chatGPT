/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mjkj.chatgpt.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.mjkj.chatgpt.model.*;
import com.mjkj.chatgpt.service.IChatGPTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * chatgpt相关
 */
@Slf4j
@Service
public class ChatGPTServiceImpl implements IChatGPTService {


    //获取GPT内容
    @Override
    public String getChatContent(ChatGptParam gptModel) {
        String url = gptModel.getUrl();
        String apiKey = gptModel.getKey();
        String model = gptModel.getModel();//text-davinci-003
        String prompt = gptModel.getPrompt();//输入的文本提示 必填

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", model);
        paramMap.put("prompt", prompt);
        paramMap.put("engine", null);
        if (ObjectUtil.isNotEmpty(gptModel.getTemperature())) {//控制生成文本的多样性
            paramMap.put("temperature", gptModel.getTemperature());
        }

        if (ObjectUtil.isNotEmpty(gptModel.getMax_tokens())) {//控制生成文本的长度
            paramMap.put("max_tokens", gptModel.getMax_tokens());
        }
        if (ObjectUtil.isNotEmpty(gptModel.getTop_p())) {////控制生成文本的多样性
            paramMap.put("top_p", gptModel.getTop_p());
        }

       String jsonStr = JSONUtil.toJsonStr(paramMap);

        HttpRequest request  = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey);
//        String body =/*request.setHttpProxy("127.0.0.1", 10809)*/

        request.setHttpProxy("127.0.0.1", 10809);

        String body = request.body(jsonStr)
                .execute().body();
        ResultStr resultStr1 = JSONObject.parseObject(body, ResultStr.class);
        Choice[] choices = resultStr1.getChoices();
        String text = choices[0].getText();
        log.info("****** body *****" + text );
        return text;
    }

    //获取GPT内容
    @Override
    public String getChatGptPublic(ChatGptPublicParam param) {
        String url = param.getUrl();
        String apiKey = param.getKey();
        String req_body = param.getBody();


        HttpRequest request  = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey);
        request.setHttpProxy("127.0.0.1", 7890);
		String body =/*request.setHttpProxy("127.0.0.1", 7890)*/
        request.body(req_body)
                .execute().body();
        log.info("****** body *****" + body);
        return body;
    }



    @Override
    public String getChatGPTContent(String jsonParam) {

        JSONObject chatGPTParam = JSONObject.parseObject(jsonParam);
        //https://api.openai.com/v1/chat/completions
        String url =(String) chatGPTParam.get("url");
        //密钥
        String apiKey =(String) chatGPTParam.get("apiKey");
        //参数
        String data =(String) chatGPTParam.get("data");

        HttpRequest request  = HttpRequest.post(url)
                                          .header("Content-Type", "application/json")
                                          .header("Authorization", "Bearer " + apiKey);

        request.setHttpProxy("localhost", 7890);

        String body = request.body(data).execute().body();

        log.info("content:" + body);
        return body;
    }
}
