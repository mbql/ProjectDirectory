package com.ht.mvc.servlet;

import com.ht.mvc.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mbql
 * @date 2020/7/5 12:53
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private List<String> clzList = new ArrayList<>();       //存放所有扫描的类
    private Map<String, Object> beanMap = new HashMap<>();   //存放所有实例化bean
    private Map<String, Method> urlMapping = new HashMap<>(); //存放url映射路径

    @Override
    public void init() throws ServletException {
        try {
            // 1、扫描包配置
            scanPackages("com.ht.mvc");

            // 2、实例化bean
            doInstance();

            // 3、将对象放入Ioc容器中
            entryIoc();

            // 4、创建controller中url映射关系
            doMapping();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doMapping() {
        if (beanMap.isEmpty() || beanMap.size() < 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Class<?> obj = entry.getValue().getClass();
            if (!obj.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseUrl = "";
            if (obj.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping rm = obj.getAnnotation(RequestMapping.class);
                baseUrl = rm.value();
            }
            Method[] methods = obj.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping anno = method.getAnnotation(RequestMapping.class);
                //处理一下url路径
                String url = getHandleUrl(baseUrl, anno);
                urlMapping.put(url, method);
                System.out.println("Mapping：" + url + "---Method：" + method);
            }
        }
    }

    private String getHandleUrl(String baseUrl, RequestMapping anno) {
        int length = baseUrl.length();
        String isEndSlash = baseUrl.substring(length - 1 , length);
        String handleUrl = "";
        if (!"/".equals(isEndSlash)){
            handleUrl = baseUrl.concat("/");
        }else {
            handleUrl = baseUrl;
        }
        String isFirstSlash = baseUrl.substring(0,1);
        if (!"/".equals(isFirstSlash)){
            handleUrl = "/" + handleUrl;
        }
        String value = anno.value();
        int length1 = value.length();
        String isExists = value.substring(0, 1);
        if ("/".equals(isExists)){
            value = value.substring(1,length1);
        }
        return handleUrl + value;
    }

    private void entryIoc() throws Exception {
        if (beanMap.isEmpty() || beanMap.size() < 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();       //根据实例bean获取依赖注入的所有属性
            for (Field field : fields) {
                //如果字段属性是private，需要开启此属性
                field.setAccessible(true);
                if (!field.isAnnotationPresent(Autowired.class)){
                    continue;
                }
                if (field.isAnnotationPresent(Autowired.class) && field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    String beanName = qualifier.value();
                    if ("".equals(beanName)) {
                        beanName = lowerFirstCase(field.getType().getSimpleName());
                    }
                    field.set(entry.getValue(), beanMap.get(beanName));
                    System.out.println("beanName：" + beanName);
                } else if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String beanName = autowired.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(field.getType().getSimpleName());
                    }
                    field.set(entry.getValue(), beanMap.get(beanName));
                }
            }
        }
    }

    private void doInstance() throws Exception {
        if (clzList.isEmpty() || clzList.size() < 0) {
            return;
        }
        for (String clzName : clzList) {
            //使用反射获取对应的类
            Class<?> clzClass = Class.forName(clzName);
            //判断类是否有Controller注解
            if (clzClass.isAnnotationPresent(Controller.class)) {
                // RequestMapping rm = clzClass.getAnnotation(RequestMapping.class);
                String key = lowerFirstCase(clzClass.getSimpleName());
                beanMap.put(key, clzClass.newInstance());
            } else if (clzClass.isAnnotationPresent(Service.class)) {                 //判断是否有Service注解
                Service service = clzClass.getAnnotation(Service.class);
                String beanName = service.value();
                if ("".equals(beanName.trim())) {                //判断beanName是否为空
                    beanName = lowerFirstCase(clzClass.getSimpleName());
                }
                Object instance = clzClass.newInstance();
                beanMap.put(beanName, instance);
                //根据接口类型实例化
                Class<?>[] interfaces = clzClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    beanMap.put(lowerFirstCase(i.getSimpleName()), instance);
                }
                System.out.println("beanMap：" + beanMap);
            } else {
                continue;
            }
        }
    }

    private void scanPackages(String packagesPath) {
        String fileUrl = getClass().getClassLoader().getResource("/" + packagesPath.replaceAll("\\.", "/")).getFile();
        File scanFile = new File(fileUrl);
        String[] files = scanFile.list();
        for (String fileName : files) {
            File file = new File(fileUrl + fileName);
            if (file.isDirectory()) {
                scanPackages(packagesPath + "." + fileName);
            } else {
                clzList.add(packagesPath + "." + fileName.replace(".class", ""));
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //发送dispatchServlet请求
        doSendRequest(req,resp);
    }

    private void doSendRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        requestURI = requestURI.replace(contextPath, "").replace("/+", "/");      //得到请求url
        if (!urlMapping.containsKey(requestURI)) {
            resp.getWriter().write("404 NOT Found");
            return;
        }
        Method method = urlMapping.get(requestURI);
        String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
        System.out.println("beanName：" + beanName);
        try {
            Object obj = method.invoke(beanMap.get(beanName));
            resp.getOutputStream().write(obj.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把类名转首字母小写
     *
     * @param simpleName
     * @return
     */
    private String lowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
