package week02.answer06;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author：zhi-jun
 * @Date：2021/03/25
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        System.out.println("请求结果：" + new HttpClientDemo().get("http://localhost:8800"));
    }

    public String get(String url) {
        try {
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
                HttpGet httpGet = new HttpGet(url);
                RequestConfig requestConfig = RequestConfig.custom()
                        .setConnectTimeout(1000)
                        .setSocketTimeout(3000).build();
                httpGet.setConfig(requestConfig);
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getHeaders("user").length > 0)
                        System.out.println("请求头 user：" + response.getHeaders("user")[0].getValue());
                    return EntityUtils.toString(response.getEntity());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
