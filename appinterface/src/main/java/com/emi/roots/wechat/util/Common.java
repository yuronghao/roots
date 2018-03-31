package com.emi.roots.wechat.util;



import com.emi.roots.util.PropertyHolder;
import com.emi.roots.util.Sha1Util;
import com.emi.roots.wechat.aes.AesException;
import com.emi.roots.wechat.aes.WXBizMsgCrypt;
import com.emi.roots.wechat.bean.WeChatConf;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhaoyf on 2014/12/22.
 */
public class Common {
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 微信接口接入校验
     *
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static final boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        List<String> params = new ArrayList<String>();
        params.add(token);
        params.add(timestamp);
        params.add(nonce);
        Collections.sort(params, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String temp = params.get(0) + params.get(1) + params.get(2);
        return Sha1Util.encode(temp).equals(signature);
    }

    /**
     * 将流转换成字符串
     *
     * @param in
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static final String streamtoStr(InputStream in) throws UnsupportedEncodingException, IOException {
        if (in == null)
            return "";

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }

    /**
     * 解密
     * @param ciphertext
     * @return
     * @throws Exception
     */
    public static String  decryption(String ciphertext,String nonce,String msg_signature,String timestamp) throws  Exception{
        WXBizMsgCrypt pc = new WXBizMsgCrypt(PropertyHolder.getProperty("wechat.token"),PropertyHolder.getProperty("wechat.EncodingAESKey"), PropertyHolder.getProperty("wechat.appid"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(ciphertext);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);
        Element root = document.getDocumentElement();
        NodeList nodelist1 = root.getElementsByTagName("Encrypt");
        String encrypt = nodelist1.item(0).getTextContent();
        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);
        String result2 = pc.decryptMsg(msg_signature, timestamp, nonce, fromXML);
        System.out.println("解密后明文: " + result2);
         return result2;
    }
    
    
    public static String  decryption(String ciphertext,String nonce,String msg_signature,String timestamp,WeChatConf conf) throws  Exception{
        WXBizMsgCrypt pc = new WXBizMsgCrypt(conf.getWxtoken(),conf.getEncodingaeskey(),conf.getWxappid());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(ciphertext);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);
        Element root = document.getDocumentElement();
        NodeList nodelist1 = root.getElementsByTagName("Encrypt");
        String encrypt = nodelist1.item(0).getTextContent();
        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);
        String result2 = pc.decryptMsg(msg_signature, timestamp, nonce, fromXML);
        System.out.println("解密后明文: " + result2);
         return result2;
    }

    /**
     * 加密
     * @param plaintext
     * @param nonce
     * @return
     */
    public  static String encryption(String plaintext,String nonce,WeChatConf conf){
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(conf.getWxtoken(),conf.getEncodingaeskey(), conf.getWxappid());

            String miwen = pc.encryptMsg(plaintext, String.valueOf(System.currentTimeMillis()), nonce);
            System.out.println("加密后: " + miwen);
            return miwen;
        }catch (AesException e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 加密
     * @param plaintext
     * @param nonce
     * @return
     */
    public  static String encryption(String plaintext,String nonce){
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(PropertyHolder.getProperty("wechat.token"), PropertyHolder.getProperty("wechat.EncodingAESKey"), PropertyHolder.getProperty("wechat.appid"));
            String miwen = pc.encryptMsg(plaintext, String.valueOf(System.currentTimeMillis()), nonce);
            System.out.println("加密后: " + miwen);
            return miwen;
        }catch (AesException e){
            e.printStackTrace();
        }
        return null;
    }
}
