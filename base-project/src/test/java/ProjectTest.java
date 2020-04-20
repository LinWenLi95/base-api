import com.lwl.base.project.config.redis.RedisConstants;
import com.lwl.base.project.entity.pojo.SysPermission;
import com.lwl.base.project.util.RedisUtils;
import com.lwl.base.project.BaseProjectApplication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author linwenli
 * @date 2020/04/13
 */
@SpringBootTest(classes = BaseProjectApplication.class)
public class ProjectTest {

    @Autowired
    WebApplicationContext applicationContext;

    @Test
    public void test() {
        List<Map<String, String>> resultList = new ArrayList<>();

        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            Map<String, String> resultMap = new LinkedHashMap<>();

            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();
            // 类名
            resultMap.put("className", handlerMethod.getMethod().getDeclaringClass().getName());
            Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            for (Annotation annotation : parentAnnotations) {
                if (annotation instanceof Api) {
                    Api api = (Api) annotation;
                    resultMap.put("classDesc", api.value());
                } else if (annotation instanceof RequestMapping) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    requestMapping.value();
                    if (requestMapping.value().length > 0) {
                        //类URL
                        resultMap.put("classURL", requestMapping.value()[0]);
                    }
                }
            }
            // 方法名
            resultMap.put("methodName", handlerMethod.getMethod().getName());
            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            // 处理具体的方法信息
            for (Annotation annotation : annotations) {
                if (annotation instanceof ApiOperation) {
                    ApiOperation methodDesc = (ApiOperation) annotation;
                    String desc = methodDesc.value();
                    //接口描述
                    resultMap.put("methodDesc", desc);
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
                //请求URL
                resultMap.put("methodURL", url);
            }
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                //请求方式：POST/PUT/GET/DELETE
                resultMap.put("requestType", requestMethod.toString());
            }
            resultList.add(resultMap);
        }
        System.out.println();
    }

    @Test
    public void savePermission() {
        SysPermission source = new SysPermission();
        source.setIsDel(false);
        source.setState(1);
        source.setCreatorId(1);
        source.setUpdaterId(1);
        List<SysPermission> permissions = new ArrayList<>();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();
            SysPermission permission = new SysPermission();
            BeanUtils.copyProperties(source, permission);
            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            // 处理具体的方法信息
            for (Annotation annotation : annotations) {
                if (annotation instanceof ApiOperation) {
                    ApiOperation methodDesc = (ApiOperation) annotation;
                    //标题及描述
                    permission.setTitle(methodDesc.value());
                    permission.setDescription(methodDesc.notes());
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
                //url替换{xx}为**

                permission.setUrl(url);
            }
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                //请求方式：POST/PUT/GET/DELETE
                permission.setMethod(requestMethod.toString());
            }
            permissions.add(permission);
        }
        System.out.println(permissions.toString());
    }

    public static void main(String[] args) {
        //url从数据库存入redis中时将{xx}转为**
        //外部请求url在要到redis匹配url前先
        String url = "/b/{id}/p/{oo}";
        int count = url.length() - url.replace("{", "").length();
        for (int i = 0; i < count; i++) {
            int x = url.indexOf("{");
            int j = url.indexOf("}");
            System.out.println(x+":"+j);
            url = url.replace(url.substring(x, j+1), "**");
            System.out.println(url);
        }
        boolean matches = url.matches("");
    }
}
