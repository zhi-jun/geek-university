package week02.answer06;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author：zhi-junl
 * @Date：2021/03/25
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        new HttpClientDemo().get("http://localhost:8801");

        String result = new HttpClientDemo().get("https://www.baidu.com");
        System.out.println("请求结果：" + result);
    }

    private static CloseableHttpClient httpClient = null;

    static {
        //设置连接池
        httpClient = HttpClients.custom()
                .setMaxConnTotal(8)
                .evictIdleConnections(60, TimeUnit.SECONDS)
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                httpClient.close();
            } catch (IOException ignored) {
            }
        }));
    }

    public String get(String url) {
        try (CloseableHttpResponse response = httpClient.execute(new HttpGet(url))) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
