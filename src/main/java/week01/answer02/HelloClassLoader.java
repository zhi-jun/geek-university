package week01.answer02;

import java.io.InputStream;

/**
 * @Author：zhi-jun
 * @Date：2021/03/19
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = new HelloClassLoader().findClass("Hello");
        clazz.getMethod("hello").invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = loadClassData();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData() {
        //load the class data from file
        try (InputStream stream = this.getClass().getResourceAsStream("/Hello.xlass");) {
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
