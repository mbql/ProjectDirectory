package com.ht;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

/**
 * @author mbql
 * @date 2020/3/24 17:23
 */
public class AlipayServerTest {
    private static final String outtradeno = "123562455564663";

    public static void main(String[] args) {

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016101700705226", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsouqPYY8aL+QdcY+ssu1bZr0+L6STatUnP7RdlonRlePGsb5hzPPtltCWfQyZgI8U8BtNY6meV+WWkCxeCe+g4YnC4BiA85FWMhS6ikMXGo879DA/rO5UaKpkkDWq3sxoDsyyZqJAqzvmJ+12WHVcEmlrh9HjNRMbcZEVrKWmefuDL9/iPVarYCqyBfyoutWXBYMhWHCY8l9dIz4+uRbP7nTb5YSx1DwRZ5Gd+QU4APU2SbjBDaUQF9TqLVp6KK5VFgv4VGOXWsbYliWpvZJrvemqpPSbZTmj8gbVhQeb8F+Vki05fkG51h/wzTrbYHC5pfxmT05DHftaHjrgqx5RAgMBAAECggEAFzMBgGkk2OkMp6RGIXgeC4Vf8wZmAGV/5XlbHKSJOsAAzwe9xNjNAuWcfEyrr8kl8TNfB+RXu+6l7LjNLYn6SM1mPOjugg25PzMVtAe2gllQXEcxYK01jeRO2ZrDi4XLRESQ33yJW1VnhENjVt8GTmwu/INRAk50leCKFbaQwDE1IT9XeArUGeByp7G0WjlT7hEp6AOcegoojxhQkx5tIaXDO4B4Id1mhXYSb2RMZprjoYQg4pZcWFvRmoquNKVS07bo6EcDKs6I+gA3UD3/lxvzJ2ijZfmK3W3GyOjo3OdmjWOGhqThuA5amJwxDnwJxSpCw3g//cuxOVetEGcAPQKBgQD03y+T8ZcHF3w7WWoABaNV5ugZ0/MFw6j+qyclY5YoSBIYLxWsQBoH68rxKvfZ9hlY4IHE4ysIuevwbZNL/qj90XxRsfSI6uaPNr2ILXV7JBgoVSj3hGRL4aSu7gTDdmwK5ghwJsWoxNUUSBmyLFf4k25Z7TKyfV1+VALB9UXp1wKBgQC0e1nGiWvZ8u0TakWneHVpjk5rn2GfzgfBGxGHl3d0H8Wp3xQqWPmlv3NLna8O+i+iQM0WACYjGl/PBjNPLYYKpvRfOqmWFDSfkCNlv8wI1H6I/Xkd1NjZUz6ZjGlWnCn5kHZaUy58Yp3Y6F2tZ99UvSLpUbIIVt4ULOL7WCpEFwKBgQDEmumtZBy5ke6qDCliaSRyVRnnmtgVubnW935Ps7ydDPiSOEOzcCg2Ueg4aCJl+wTw6hjnBunHcNdxy9im/ImcePTS9wl6YPfK5s2YJ7+apilLQMzKpp6MmlfKJ8rlURCAU59UxJyQ27ClPxp6hsX/DAhe5WZ+Ds/hHPy11ANVMwKBgBtQ3AqXe/JVuRoKKJarPaIHPsk9KypjpXrNnP6XpyW1NVZ7g/LQ2qACdPHJ4Nf0d8cn5aJhzWPctwl/V76xFiuAj1XmbAph7oED8MeSms/vUWNscatZLqTd24ij3sGUCypRegg7tLpwtpFAwgTfxsZthd4oVji65QvKq2P9ORBrAoGBANO6AJ+S+JgpfApfY9OBF4dzsKxVMaD9BC/wIEbRIIol537d67bCNNxfS79FYyEiKCF062waNWtnwDAhyZ4x34kvV/3X0PY+bC8SNeaLNNFz0TrwhQzhxcDipO74Pdc+lHNpQWTUsevyO4LJP+pmJ/vpIFOqsbEtPD4Zy+AFlMgm",
                "json", "GBK", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArKLqj2GPGi/kHXGPrLLtW2a9Pi+kk2rVJz+0XZaJ0ZXjxrG+Yczz7ZbQln0MmYCPFPAbTWOpnlfllpAsXgnvoOGJwuAYgPORVjIUuopDFxqPO/QwP6zuVGiqZJA1qt7MaA7MsmaiQKs75iftdlh1XBJpa4fR4zUTG3GRFaylpnn7gy/f4j1Wq2AqsgX8qLrVlwWDIVhwmPJfXSM+PrkWz+502+WEsdQ8EWeRnfkFOAD1Nkm4wQ2lEBfU6i1aeiiuVRYL+FRjl1rG2JYlqb2Sa73pqqT0m2U5o/IG1YUHm/BflZItOX5BudYf8M0622BwuaX8Zk9OQx37Wh464KseUQIDAQAB",
                "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(outtradeno);
        model.setTimeoutExpress("30m");
        model.setTotalAmount("100");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("https://www.alipay.com");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
