import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class Test {

//    public static void main(String[] args) {
//        /**
//         * //评测题目: 无
//         * 1.问题：如何应对缓存穿透和缓存雪崩问题
//         *   缓存穿透：导致缓存穿透的原因是大量的不同的缓存同一时间失效导致请求都打到了db，
//         *           解决方案：设置过期时间时，尽量不要设置一样的过期时间，可以在一段时间范围内随机生成一个过期时间
//         *   缓存雪崩：redis集群
//         *           压测
//         *
//         *
//         * 2.问题：实现一个多线程类，并用该线程类实例化3个线程A,B,C；A线程打印字符A,B线程打印字符B，C线程打印字符C；启动这3个线程，要求启动线程的顺序为C线程->B线程->A线程，并且最后输出内容为：
//         * A
//         * B
//         * C
//         * 不能用sleep函数，注意考虑线程安全问题。编程语言不限
//         */
//        TestThread testThreadA = new TestThread("A",null);
//        TestThread testThreadB = new TestThread("B",testThreadA);
//        TestThread testThreadC = new TestThread("C",testThreadB);
//
//        testThreadA.start();
//        testThreadB.start();
//        testThreadC.start();
//
//
//    }

    public static void main(String[] args) {
//        String str = "{\"FirstKey\":1,\"SecondKey\":2,\"ThirdKey\":{\"FirstSubKey\":3}}";
//        test(str);
    }

    public static void test(String str){
        //将字符串转化成map结构
        Gson json = new Gson();
        HashMap map = json.fromJson(str, HashMap.class);

        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();

            //todo 判断value是不是又是map
            if(entry.getValue() instanceof HashMap){
                //todo 接着递归
            }
            //首字母转小写
            new StringBuilder().append(Character.toLowerCase(key.charAt(0))).append(key.substring(1)).toString();
        }

    }

    /**
     * 判断是否可以再转化成map
     * @param transferStr
     */
    private static void isTransferMap(String transferStr){
        System.out.println("transferStr:"+transferStr);
        Gson json = new Gson();
        HashMap map = json.fromJson(transferStr, HashMap.class);
    }

}
class TestThread extends Thread{

    private String str;

    private Thread thread;

    public TestThread(String str, Thread thread) {
        this.str = str;
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            if(thread != null){
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+str);
    }
}
