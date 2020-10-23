package rejectStrategy;

import com.dongdaxiaodong.dongCache.ByteView;
import com.dongdaxiaodong.dongCache.DongCache;
import com.dongdaxiaodong.dongCache.Group;
import org.junit.Test;

public class TestHttp {

    @Test
    public void testHttp(){
        DongCache.NewGroup("scores",2<<10);
        Group group = DongCache.GetGroup("scores");
        group.Add("Tom",new ByteView("68".getBytes()));
        group.Add("Peter",new ByteView("80".getBytes()));

    }
}
