import com.lwl.base.project.config.redis.RedisConstants;
import com.lwl.base.project.util.RedisUtils;
import com.lwl.base.project.BaseProjectApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author linwenli
 * @date 2020/04/13
 */
@SpringBootTest(classes = BaseProjectApplication.class)
public class ProjectTest {

    @Test
    public void test() {
        Set<Object> zget = RedisUtils.zget("dfd");
        System.out.println();
        RedisUtils.zset("role", "ROLE_ADMIN", "ROLE_USER");
        zget = RedisUtils.zget("role");
        Set<String> roleNameSet = new HashSet<>();
        if (zget != null) {
            for (Object o : zget) {
                roleNameSet.add((String)o);
            }
        }
    }
}
