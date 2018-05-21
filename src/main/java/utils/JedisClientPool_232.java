package utils;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.Properties;

/**
 * Created by thinkpad on 2017/6/27.
 */
public class JedisClientPool_232 {
    /**
     * 属性文件加载对象
     */
    //private static PropertiesLoader loader = PropertiesLoader.getIntances("hys");

    public static JedisClientPool_232 jedisClientPool232 = getInstance();
    public static JedisPool jedisPool;
    public static int maxTotal;
    public static int maxIdle;
    public static int maxWaitMillis;

    public static synchronized JedisClientPool_232 getInstance() {
        if (null == jedisClientPool232) {
            jedisClientPool232 = new JedisClientPool_232();
        }
        return jedisClientPool232;
    }

    public JedisClientPool_232() {
        if (null == jedisPool) {
            init();
        }
    }

    /**
     * 初始化Jedis
     *
     * @return
     */
    private static JedisPoolConfig initPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // #最大分配的对象数 :控制一个pool最多有多少个状态为idle的jedis实例
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 当池内没有返回对象时，最大等待时间 : 超时时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 当调用borrow Object方法时，是否进行有效性检查:
        // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
        jedisPoolConfig.setTestOnBorrow(true);
        // 当调用return Object方法时，是否进行有效性检查 :在还给pool时，是否提前进行validate操作
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }

    /**
     * 初始化jedis连接池
     */
    public static void init() {
        Properties properties= ReadProper.readproper("/redis.properties");
        maxTotal = Integer.parseInt( properties.getProperty("redis.pool.maxTotal"));
        maxIdle = Integer.parseInt(properties.getProperty("redis.pool.maxIdle"));
        maxWaitMillis = Integer.parseInt(properties.getProperty("redis.pool.maxWaitMillis"));
        JedisPoolConfig jedisPoolConfig = initPoolConfig();
        String host =  properties.getProperty("redis.host.232");// "localhost";
        int port = Integer.parseInt(properties.getProperty("redis.port.232"));// 6379;
        int timeout = Integer.parseInt( properties.getProperty("redis.timeout"));// 60000;
        String pwd = StringUtils.isBlank(properties.getProperty("redis.password.232"))?"":properties.getProperty("redis.password.232");
        // 构造连接池
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,pwd);
    }
}
