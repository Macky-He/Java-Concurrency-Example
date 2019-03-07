package com.better517na.concurrency.other;
/*
 * 文件名：SsoLogin.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： SsoLogin.java
 * 修改人：jianxi
 * 修改时间：2017年7月25日
 * 修改内容：新增
 */

import org.springframework.stereotype.Controller;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 *
 * <pre>
 * </pre>
 *
 * @author jianxi
 */
@Controller
public class SPBBank {

    /**
     * 解密.
     *
     * @param src
     *            src
     * @param sKey
     *            sKey
     * @return page
     * @throws Exception
     *             Exception
     */
    public String decrypt(String src, String sKey) throws Exception {
        byte[] srcDes = this.parseHexStr2Byte(src);
        byte[] key = sKey.getBytes();
        // 初始化向量
        IvParameterSpec iv = new IvParameterSpec(key);
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
        // 真正开始解密操作
        return new String(cipher.doFinal(srcDes), "utf-8");
    }

    /**
     * 将16进制转换为二进制.
     *
     * @param src
     *            hexStr
     * @param sKey
     *            hexStr
     * @return byte
     * @throws Exception
     *             Exception
     */
    public String decryptDes(String src, String sKey) throws Exception {
        byte[] str = this.parseHexStr2Byte(src);
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(sKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte[] encryptedData = str;
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return new String(decryptedData, "utf-8");
    }

    /**
     * 将二进制转换成16进制.
     *
     * @param buf
     *            buf.
     * @return return
     */
    public String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 加密.
     *
     * @param data1
     *            data.
     * @param sKey
     *            sKey.
     * @return return
     */
    public byte[] encrypt(String data1, String sKey) {
        try {
            byte[] data = data1.getBytes("utf-8");
            byte[] key = sKey.getBytes();
            // 初始化向量
            SecureRandom sr = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密.
     *
     * @param args
     *            args. return
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        try {
            SPBBank spbBank = new SPBBank();
            System.out.println(spbBank.parseByte2HexStr(spbBank.encrypt("13096221243", "12345678")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将16进制转换为二进制.
     *
     * @param hexStr
     *            hexStr
     * @return byte
     */
    public byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
