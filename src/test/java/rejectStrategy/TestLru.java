package rejectStrategy;
import com.dongdaxiaodong.dongCache.ByteView;
import com.dongdaxiaodong.dongCache.DongCache;
import com.dongdaxiaodong.dongCache.Group;
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
    public void testDongCache(){
        DongCache.NewGroup("scores",2<<10);
        Group  group = DongCache.GetGroup("scores");
        System.out.println(group);
        group.Add("Tom",new ByteView("630".getBytes()));
        group.Add("Jack",new ByteView("589".getBytes()));
        System.out.println(group.Get("Tom").String());
    }
}
