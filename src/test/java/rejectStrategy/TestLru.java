package rejectStrategy;
import com.dongdaxiaodong.dongCache.rejectStrategy.CallBack;
import com.dongdaxiaodong.dongCache.rejectStrategy.Lru;
import org.junit.Test;
public class TestLru {

    @Test
    public void testLru(){
        Lru lru = new Lru(30L, new CallBack() {
            public void onEvicted(String key, byte[] value) {
                System.out.println(key+" oooo kkk");
            }
        });
        lru.Add("name","colin".getBytes());
        lru.Add("score","90".getBytes());
        lru.Add("gender","boy".getBytes());
        System.out.println(new String(lru.Get("score")));
        System.out.println(new String(lru.Get("name")));
        lru.Add("major","software engineering".getBytes());
    }

    @Test
    public void testLru1(){

    }
}
