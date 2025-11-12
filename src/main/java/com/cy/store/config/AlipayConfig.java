package com.cy.store.config;

import lombok.Data;

import org.springframework.stereotype.Component;



@Data
@Component
public class AlipayConfig {
    //自己的appId
    public static String appId = "9021000157656785";
    //应用私有秘钥
    public static String appPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCejbR58tWcgIVlpWZop3PIfbv7F4HlxDVAszIGs/BCCp8ggf+amvjcElh7ZhrtovErJQfr0OtaRPt7+Ap5Rza5/M6GbfpI0lJ5r0Ulf1AVvGfVBapEpht5lVGNChrhUvX3U5QxhCtW2qKPimezUOcEzRIMdGroAfQ3QUeF8Hz9xgtj6d/nbNbzQ/q3oTxB17ZY+AAwQajmaODKfEGS8zrvlgtiwa0ma8/t1oq0N0cx3DWHWv1a3X8LPCi40VdwFM9VOvjNhnNtmE1DqfB77wsrdRXeZjx8q3tXzJ+7KTJYOkXaJQuCbNFcmOgbc+K94qncYqEFrOOYIpt8ppYp9QYjAgMBAAECggEAO/An5N33AMXZJ/biAvging1iLebSFVEaLI5RgBUv41dulnRuczzYjSFmT6fm0CUD9KJ4Hx6IikR/qXq4Qua3+pFmZY1Xy4Wu5g9rHWTGmqDxw6bzmiYNRuY6wQ2r4bhiAZCX+iwT0i6uu4K/9xrDap43j3vHGlP6xZSo/CpQpgJKxPSD5MY5tQbD/DyVZTBJTqnyB8dixRFZtLfUDREN8hh82mbI/qoQlzGMgiJPhSbbjApbotXqml+2YAtHe2PmIXtqkAYjWLU5418kVeazlWUJOPV2ICfwt/HtxDg0tg+8muUpGaQERuq8n7oRroysB+XiDk9n1M/sxcqiYc18cQKBgQDRgUE4yN9vOUKQPapR6qFQN3Jbf+Lk52w6XE54SSCh9B3xx3/Kmk/Ey1Vxy1eN8SP6hxw31W3hMjTSgley2chsSvC2BjdRQf9T2nOeFpNC0b88XqoiqEpnlRa4UJG2oJ0ksIBToRwIUjmUDws8SQZd+pPxgNMn+PR2w+8BdxdR6QKBgQDBvbckJ+xUVtsEtOsw9W9EQoiKKr/5B9dPGwNsYq7Ihdq/6s30eGywnXSGbrAncw7gMhUM3lsnGRRj0uj0caSv8dtzB3L5jYnCB8BFOI3khGZfRR5zEb8GriGBI4rWHiyrLIoB7a7WTGZr5w7kwhCl0IgPYExF0CP4QFK6oGmkKwKBgGT5uljHIEG4RGfHAOc4ggVqo/iL2+zdyaEbSkrZXBwSz7fvop0g4N+F7SnKiumD1wZxxlz2xl3q1+C6J9Cw/NONDxV8S38jow9kU6NXWCS1eUl8it3nZO0lw2yUkJaINRUw6LjmBt/P2aaN1al5vNLg6htVLvCuIg14BCMdpiS5AoGASAx5cyS1qpeyU4nEfSyI+GWVyWwkgEP+OhjdqQz52vCMbRqxBN1T7kpf7y5yq/kJ6y40zdhTJbMZ9hWVNHKewB+9cIWwqEWOmW2wfw2NFxJ1z7AwprJOy/kt1IyXMRF/oxipKhZuEpvO6wm+bCwPbNqj/rPdKYlCFyw0l9rX7okCgYAj22nZ4Au8hM2+oVGvv+fzMEluJ/8z2IoLGEbseqANF/eoZp0WJxEnxa+jv0n8/UhgShh83izuA8cw0b2/IxZYu8jck9l5QTAftO+mM6quIIWa703EQExjBThCAWhUALV/b9/f1ZRCCi47+FvDjrTuuuBssG37kOwYeQylifoEsg==";
    //支付宝公钥
    public static String alipayPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgMcGqJjT+pYR1iamq9gpInFOjys+Kn+qT+D8Knibjb/e+12xcAUEqreSGuC2REKT57w+9duWmgTdGb8JYh2Qhb0IR629g6BzQF8F7qgYz3V0+L2M9aw3d/MRcSIyhDyXHEKD8lwH3RlLaV2mqUofe4NWZm/OYLc/D5DhRuTQqyYflApY2TmLPsfSvWjp1+rky7hNSfi+T+mdKBFehF6PYC1FU3RhKx2HT/iuSlvhEj6EFJXd64LLWf2LUG8PA5/GQfLO6SVAerNmb3TJ7y1gqEbwtD2MwPcJucOxDgxNiyxKK26Yf8681Txwe6Q3jQk3lguT+OwOPdf6j0VLG3LSowIDAQAB";
    //异步回调地址
    // 异步回调地址（支付宝服务器主动推送支付结果）
    public static String notifyUrl = "https://thuy-colourful-impetuously.ngrok-free.dev/alipay/notifyNotice";
    // 同步回调地址（用户支付后跳转回你的页面）
    public static String returnUrl = "https://thuy-colourful-impetuously.ngrok-free.dev/alipay/returnNotice";
    //推荐使用这个秘钥
    public static String signType = "RSA2";
    //使用的编码格式
    public static String charset = "UTF-8";
    //支付宝默认网关
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

}

