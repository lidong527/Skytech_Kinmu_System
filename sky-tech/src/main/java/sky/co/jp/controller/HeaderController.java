package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.SessionBean;
import sky.co.jp.form.LoginForm;
import sky.co.jp.mapper.GetBottonCdMapper;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.util.SessionUtil;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class HeaderController {

    @Autowired(required = false)
    SessionMapper sessionMapper;

    private static final String PERM_BG = "PERM_BG";
    private static final String KEY = "Skytech";

    public String createCookie(Model model, HttpServletRequest request, HttpSession session, LoginForm loginForm, SessionMapper sessionMapper) {

        Object sessionUser = session.getAttribute("UserBg");
        SessionBean sessionBean = new SessionBean();
        sessionBean = sessionMapper.selectstaffBgByMail(loginForm.getUserMail(),loginForm.getPassword());
        if(sessionBean == null){
            return "redirect:sky-tech";
        }
        //zhou 20201104
        //设置staffbg和staffname到session
        SessionUtil.setCurrentStaffBG(sessionBean.getSTAFF_BG());
        SessionUtil.setStaffName(sessionBean.getSTAFF_NAME());
        //zhou 20201023 staffbg改为string类型
        String newUser = sessionBean.getSTAFF_BG();
        if (sessionUser == null) {
            System.out.println("sessionを存在しません，sessionを設置します"+loginForm.getUserMail());
            session.setAttribute("UserBg", sessionBean.getSTAFF_BG());
            session.setAttribute("permiss",sessionBean.getPERMISSION_BG());
        } else {
            System.out.println("session存在します,username=" + sessionUser.toString());
            if(newUser != String.valueOf(sessionUser)){
                session.setAttribute("UserBg", sessionBean.getSTAFF_BG());
                session.setAttribute("permiss",sessionBean.getPERMISSION_BG());
            }
        }
        System.out.println(session.getAttribute("UserBg")+"|"+session.getAttribute("permiss"));
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return String.valueOf(session.getAttribute("UserBg"));
    }
    /*
    @param HttpServletRequest
    @param HttpSession
    @param SessionMapper
     */
    public String CheckCookie(HttpServletRequest request, HttpSession session,SessionMapper sessionMapper){
        Object sessionUser = session.getAttribute("UserBg");
        Object sessionPer = session.getAttribute("permiss");

        if(sessionUser == null){
            return"FALSE";
        }
        SessionBean sessionBean = new SessionBean();
        sessionBean = sessionMapper.selectPerByBg(String.valueOf(sessionUser));
        int per = sessionBean.getPERMISSION_BG();

        if(String.valueOf(sessionPer).equals(per)){
            return "FALSE";
        }else{
            return String.valueOf(sessionUser);
        }
    }

    public String CheckCookie(HttpServletRequest request, HttpSession session){
        Object sessionUser = session.getAttribute("UserBg");
        Object sessionPer = session.getAttribute("permiss");

        if(sessionUser == null){
            return"FALSE";
        }
        SessionBean sessionBean = new SessionBean();
        sessionBean = sessionMapper.selectPerByBg(String.valueOf(sessionUser));
        int per = sessionBean.getPERMISSION_BG();

        if(String.valueOf(sessionPer).equals(per)){
            return "FALSE";
        }else{
            return String.valueOf(sessionUser);
        }
    }

    public String deleteCookie(HttpSession session) {
        session.setAttribute("UserBg", "");
        session.setAttribute("permiss","");
        return "skyindex";
    }

    /*
    @param HttpServletRequest
    @param HttpSession
    @param SessionMapper
     */
    public String CheckPermission(HttpServletRequest request, HttpSession session, SessionMapper sessionMapper, GetBottonCdMapper getBottonCdMapper){
        Object sessionUser = session.getAttribute("UserBg");
        Object sessionPer = session.getAttribute("permiss");
        SessionBean sessionBean = new SessionBean();
        List<GetButtonCdBean> lists = new ArrayList<GetButtonCdBean>();
        if(sessionPer == null){
            return"FALSE";
        }


        lists = getBottonCdMapper.selectC1ByIdAndC2s(PERM_BG);
        sessionBean = sessionMapper.selectPerByBg(String.valueOf(sessionUser));
        int per = sessionBean.getPERMISSION_BG();

        for (GetButtonCdBean bean:lists) {
            if(bean.getCODE_CONTENT().equals(String.valueOf(per))){
                return "FALSE";
            }
        }
        if(String.valueOf(sessionPer).equals(per)) {
            return "FALSE";
        }else{
            return String.valueOf(sessionPer);
        }
    }

    public final String encrypt(String plainText) {
        Key secretKey = getKey(KEY);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);
//            String encodeString = new String(result);
            String encodeString = getBasEncode(result);
            return encodeString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final String decrypt(String cipherText) {
        Key secretKey = getKey(KEY);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] c = getBasDecode(cipherText);
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, "UTF-8");
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* Base64 encode
    @param plainText
     */
    public static String getBasEncode(byte[] plainText){
        String encodedString = Base64.getEncoder().encodeToString(plainText);
        System.out.println("the result of Base64 is : " + encodedString);
        return encodedString;
    }


    /* Base64 decode
        @param plainText
         */
    public static byte[] getBasDecode(String plainText){
        byte[] decodedBytes = Base64.getDecoder().decode(plainText);;
        System.out.println("the result of Base64 is : " + decodedBytes);
        return decodedBytes;
    }

    public static Key getKey(String keySeed) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
