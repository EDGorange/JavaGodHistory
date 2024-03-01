package com.example.springdemo.logAspect;

import com.alibaba.fastjson.JSON;
import com.example.springdemo.annotation.NoReturnLog;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wuxinx
 * @date 2022/2/17
 * @time 14:03
 * @discription
 **/
@Aspect
@Slf4j
@Component
public class WebLogAspect {

    private final static String LINE_SEP = System.getProperty("line.separator");
    public static final String LOG = "log ";

/*
    @Autowired
    private SysInterfaceLogService sysInterfaceLogService;

    @Autowired
    private SysOptLogService sysOptLogService;
*/

    /**
     * 此切点覆盖了所有用注解定义的post或get方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping)||@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void webLog() {
        // 切点方法体必须为空
    }

    /**
     * 如果不需要切面日志,则可在方法上使用NoLog注解以忽略
     */
    @Pointcut("!@annotation(com.example.springdemo.annotation.NoLog)")
    public void noHealLog() {
        // 切点方法体必须为空
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog() && noHealLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Date date = new Date();
        Stopwatch watch = Stopwatch.createStarted();
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.info("无法获取请求参数ServletRequestAttributes");
            return proceedingJoinPoint.proceed();
        }
        Signature signature = proceedingJoinPoint.getSignature();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        List<String> logList = Lists.newArrayList();
        logList.add(LOG);
        final String requestUrl = request.getRequestURL().toString();
        // 打印请求 url
        String url = MessageFormatter.arrayFormat("[URL : {}]", new Object[]{requestUrl})
                .getMessage();
        logList.add(url);
        // 打印请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String headerName = enumeration.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        String headerJsonStr = JSON.toJSONString(headerMap);
        if (headerJsonStr.length() > 0) {
            String header = MessageFormatter.arrayFormat("[HTTP_HEADERS INFO : {}]", new Object[]{headerJsonStr})
                    .getMessage();
            logList.add(header);
        }
        // 打印 Http method
        String httpMethod =
                MessageFormatter.arrayFormat("[HTTP Method : {}]", new Object[]{request.getMethod()}).getMessage();
        logList.add(httpMethod);
        // 打印调用 controller 的全路径以及执行方法
        String method = MessageFormatter.arrayFormat("[Class Method : {}.{}]", new Object[]{
                signature.getDeclaringTypeName(), signature.getName()
        }).getMessage();
        logList.add(method);
        // 打印请求的 IP
        String ip = MessageFormatter.arrayFormat("[IP : {}]", new Object[]{request.getRemoteAddr()}).getMessage();
        final Map<String, Object> fieldMaps = getFieldsName(proceedingJoinPoint);
        final String fieldJson = JSON.toJSONString(fieldMaps);
        logList.add(ip);
        // 打印请求入参
        String reqArgs = MessageFormatter.arrayFormat("[Request args : {}]", new Object[]{
                fieldJson
        }).getMessage();
        logList.add(reqArgs);
        Object result = proceedingJoinPoint.proceed();

        //根据注解判断是否打印返回参数
        NoReturnLog annotation = ((MethodSignature) signature).getMethod().getAnnotation(NoReturnLog.class);
        if (annotation == null) {
            // 打印出参
            String responseArgs =
                    MessageFormatter.arrayFormat("[Response Args  : {}]", new Object[]{JSON.toJSONString(result)})
                            .getMessage();
            if (log.isDebugEnabled()) {
                logList.add(responseArgs);
            }
        }

        final long elapsed = watch.elapsed(TimeUnit.MILLISECONDS);

        // 执行耗时
        String time = MessageFormatter
                .arrayFormat("[Time-Consuming : {} ms]", new Object[]{elapsed})
                .getMessage();
        logList.add(time);
        log.info(StringUtils.join(logList, LINE_SEP));


       /* // 用户操作日志记录
        ApiOperation apiOperationAnnotation = ((MethodSignature) signature).getMethod().getAnnotation(ApiOperation.class);
        DescLog descLog = ((MethodSignature) signature).getMethod().getAnnotation(DescLog.class);
        String logType = null;

        String name = (apiOperationAnnotation == null) ? "" : apiOperationAnnotation.value();
        String comment = null;
        if (Objects.nonNull(descLog)) {
            logType = descLog.type().getKey();
            comment = descLog.modulePrefix() + descLog.moduleName();
            if (StrUtil.isNotBlank(descLog.optionName())) {
                comment = comment.concat(StrUtil.SPACE + StrUtil.COMMA + StrUtil.SPACE + descLog.optionPrefix() + descLog.optionName());
            }
        }

        final String uri = request.getRequestURI();
        try {
            if (uri.startsWith("/api/portal/")) {
                // 接口日志
                final SysInterfaceLog
                        sysInterfaceLog = getSysInterfaceLog(date, request, requestUrl, fieldMaps, elapsed, fieldJson, result, name);
                sysInterfaceLogService.insertLog(sysInterfaceLog);
            } else {
                // 用户操作日志
                final SysOptLog
                        sysOptLog = getSysOptLog(date, request, requestUrl, elapsed, fieldJson, result, name, comment, logType);
                sysOptLogService.insertLog(sysOptLog);
            }
        } catch (Exception ex) {
            log.error("写入日志错误，{},{}", ex.getMessage(), com.aliyun.citybrain.common.utils.StringUtils.getString(ex.getStackTrace()));
        }*/

        return result;
    }

 /*   *//**
     * @param date       请求时间
     * @param request    请求
     * @param requestUrl 请求地址
     * @param elapsed    请求耗时
     * @param fieldJson  请求参数json字符串
     * @param result     请求返回的结果
     * @param name       操作名称
     * @return
     *//*
    private SysOptLog getSysOptLog(Date date, HttpServletRequest request, String requestUrl, long elapsed,
                                   String fieldJson, Object result, String name, String comment, String type) {
        final UserInfo userContext = UserContext.getUserContext();
        final SysOptLog sysOptLog = new SysOptLog();
        Long userId = null;
        if (userContext != null) {
            userId = userContext.getBaseInfo().getId();
        } else {
            // 用户时，通过返回参数获取用户id
            if (result instanceof ResultResponse) {
                final Object data = ((ResultResponse) result).getData();
                if (data instanceof SysUserVO) {
                    userId = ((SysUserVO) data).getId();
                }
            }
        }
        sysOptLog.setRoleName("");
        sysOptLog.setUserId(userId);
        sysOptLog.setName(name);
        sysOptLog.setComments(comment);
        sysOptLog.setType(type);
        sysOptLog.setRequestParam(fieldJson);
        sysOptLog.setRequestTime(date);
        sysOptLog.setUrl(requestUrl);
        sysOptLog.setResponTime(new Date());
        sysOptLog.setCostTime(elapsed);
        sysOptLog.setResponResult(JSON.toJSONString(result));
        sysOptLog.setIp(com.aliyun.citybrain.common.utils.StringUtils.getIp(request));
        return sysOptLog;
    }

    *//**
     * @param date      请求时间
     * @param request   请求
     * @param url       请求地址
     * @param fieldMaps 请求参数
     * @param elapsed   请求耗时
     * @param fieldJson 请求参数json字符串
     * @param result    请求返回的结果
     * @return
     *//*
    private SysInterfaceLog getSysInterfaceLog(Date date, HttpServletRequest request, String url, Map<String, Object> fieldMaps, Long elapsed,
                                               String fieldJson, Object result, String name) {
        final SysInterfaceLog sysInterfaceLog = new SysInterfaceLog();
        sysInterfaceLog.setRequestParam(fieldJson);
        sysInterfaceLog.setName(name);
        sysInterfaceLog.setRequestTime(date);
        sysInterfaceLog.setUrl(url);
        sysInterfaceLog.setResponTime(new Date());
        sysInterfaceLog.setCostTime(elapsed);
        sysInterfaceLog.setResponResult(JSON.toJSONString(result));
        sysInterfaceLog.setIp(com.aliyun.citybrain.common.utils.StringUtils.getIp(request));
        return sysInterfaceLog;
    }*/

    /**
     * 获取参数列表
     *
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private static Map<String, Object> getFieldsName(ProceedingJoinPoint joinPoint) {
        // 参数值
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        if (parameterNames == null) {
            return new HashMap<>(1);
        }
        Map<String, Object> paramMap = new HashMap<>(32);
        for (int i = 0; i < parameterNames.length; i++) {
            Object arg = args[i];
            if ((arg instanceof HttpServletRequest) || (arg instanceof HttpServletResponse) ||
                    (arg instanceof MultipartFile)) {
                continue;
            }
            paramMap.put(parameterNames[i], arg);
        }
        return paramMap;
    }

}

