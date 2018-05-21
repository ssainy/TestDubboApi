package utils;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by thinkpad on 2018-03-23.
 */

    public final class RedisUtils {

        //Redis服务器IP
        //private static String ADDR;

        //Redis的端口号
        //private static int PORT;

        //访问密码
        //private static String AUTH="redis-dev";

        //可用连接实例的最大数目，默认值为8；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        private static int MAX_ACTIVE = 1024;

        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
        private static int MAX_IDLE = 200;

        //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        private static int MAX_WAIT = 10000;

        private static int TIMEOUT = 10000;

        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        private static boolean TEST_ON_BORROW = true;

        private static JedisPool jedisPool = null;

        public static void init(String IP, int Port ,String PWD){
                try {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxIdle(MAX_IDLE);
                    config.setMaxWaitMillis(MAX_WAIT);
                    config.setTestOnBorrow(TEST_ON_BORROW);
                    jedisPool = new JedisPool(config, IP, Port, TIMEOUT, PWD);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    /**
     * 初始化Redis连接池
     */
            /*
            static {
                try {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxIdle(MAX_IDLE);
                    config.setMaxWaitMillis(MAX_WAIT);
                    config.setTestOnBorrow(TEST_ON_BORROW);
                    jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            */
    /**
     * 获取Jedis实例
     * @return
     */
        public synchronized static Jedis getJedis() {
            try {
                if (jedisPool != null) {
                    Jedis resource = jedisPool.getResource();
                    return resource;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 释放jedis资源
         * @param jedis
         */
        public static void returnResource(final Jedis jedis) {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        /*******************************************
         * 在相应的库中通过key获取值
         * @param db
         * @param key
         * @return
         ***********************************************/
        public static String get(int db, String key) {
            String value = null;
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.select(db);
                if (jedis.exists(key)) {
                    System.out.println("find key....");
                    value = jedis.get(key);
                    value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                }
            } catch (Exception e) {
            } finally {
                returnResource(jedis);
            }
            return value;
        }

        /*********************************************
         * redis在相应的库中增加key:value
         * @param db
         * @param key
         * @param value
         * @return
         ***********************************************/
        public static boolean addValue(int db,String key,String value)
        {
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                jedis.select(db);
                String code = jedis.set(key, value);
                if(code.equals("ok"))
                {
                    return true;
                }
            }
            catch (Exception e) {

                return false;
            }finally {
                getColse(jedis);
            }
            return false;
        }
        public static void getColse(Jedis jedis)
        {
            if(jedis != null)
            {
                jedis.close();
            }
        }
    }

