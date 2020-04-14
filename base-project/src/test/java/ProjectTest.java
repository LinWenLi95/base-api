import com.lwl.base.project.util.RedisUtils;
import com.lwl.base.project.BaseProjectApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author linwenli
 * @date 2020/04/13
 */
@SpringBootTest(classes = BaseProjectApplication.class)
public class ProjectTest {

    @Test
    public void test() {
        Boolean result = RedisUtils.set("abc:hahahha", "def");
        Boolean result2 = RedisUtils.set("abc:hghhhh","defdfdf");
        System.out.println(RedisUtils.get("abc:hahahha"));
        System.out.println(RedisUtils.getString("abc:hahahha"));
        Set<String> abc = RedisUtils.keys("abc:*");
        for (String s : abc) {
            System.out.println(s);
        }

    }
}
