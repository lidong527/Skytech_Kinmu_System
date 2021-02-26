package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.KojinBean;
import sky.co.jp.business.IKojinBusiness;
import sky.co.jp.form.FlagForm;
import sky.co.jp.form.KojinForm;
import sky.co.jp.form.LoginForm;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.SkyKojinMapper;
import sky.co.jp.mapper.StatusMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author 趙思文；
 * @Description TODO メニューから個人情報画面を遷移する；
 * @Date 2020/11/11；
 * @Param model，request，session；
 * @return staff/kojin；
 **/
@Controller
public class KojinController {

    //社員のステータス
    private static final String STATUS = "STAFF_STATUS";
    //先月のマック
    private static final String LAST_MONTH = "LM";
    //今月のマック
    private static final String THIS_MONTH = "TM";

    //SkyKojinMapperを注入する
    @Autowired(required = false)
    SkyKojinMapper skyKojinMapper;
    //SessionMapperを注入する
    @Autowired(required = false)
    SessionMapper sessionMapper;

    @Autowired(required = false)
    HeaderController header;

    @Autowired(required = false)
    IKojinBusiness kojinBusiness;

    //画面遷移リンクの名称を設置する
    @RequestMapping(value = "/kojin")
//    public String kojin(Model model, HttpServletRequest request, HttpSession session, StatusMapper statusMapper,SessionMapper sessionMapper, SkyKojinMapper skyKojinMapper) {
    public String kojin(Model model, HttpServletRequest request, HttpSession session) {

        //staffBgはcookieから取得する
//        HeaderController header = new HeaderController();
//        String staffBg = header.CheckCookie(request, session);

        //staffBgによってDBから検索した結果はKojinBeanを与える
//        KojinBean kojinBean = new KojinBean();
//        kojinBean = skyKojinMapper.selectKojinStatusByStaffBg(staffBg);

        //DBから社員ステータスを検索する
//        GetButtonCdBean getButtonCdBean = new GetButtonCdBean();
//        try {
//            getButtonCdBean = sessionMapper.selectContent2ByIdAndContent(STATUS,String.valueOf(kojinBean.getSTAFF_STATUS()));
//        } catch (NullPointerException e){
//            LoginForm account = new LoginForm();
//            model.addAttribute("account", account);
//            model.addAttribute("msg", "ログインが無効になりました。もう一度ログインしてください");
//            return "skyindex";
//        }
//        String status = getButtonCdBean.getCODE_CONTENT2();

        //KojinFormを定義する
//        KojinForm kojin = new KojinForm();
//        //KojinFormに値を設置する
//        kojin.setStaffStatus(status);
//        kojin.setGanbanName(kojinBean.getGENBAN_NAME());
//        kojin.setGenbanAddress(kojinBean.getGENBAN_ADDRESS());
//        kojin.setItType(kojinBean.getIT_TYPE());
//        kojin.setRemarks(kojinBean.getREMARKS());
//        kojin.setLmKinmu(kojinBean.getLM_KINMU_STATUS());
//        kojin.setLmKoutu(kojinBean.getLM_KOUTU_STATUS());
//        kojin.setLmTate(kojinBean.getLM_TATEKAE_STATUS());
//        kojin.setStaffBg(staffBg);
//        kojin.setStaffName(kojinBean.getSTAFF_NAME());
//        kojin.setStaffSex(kojinBean.getSTAFF_SEX());
//        kojin.setTmkinmu(kojinBean.getTM_KINMU_STATUS());
//        kojin.setTmKoutu(kojinBean.getTM_KOUTU_STATUS());
//        kojin.setTmTate(kojinBean.getTM_TATEKAE_STATUS());
//        kojin.setStandardTime(kojinBean.getSTANDARD_TIME());
//        kojin.setUpperTime(kojinBean.getUPPER_TIME());
        //FlagFormを定義する
//        FlagForm flag = new FlagForm();
        //FlagFormにマックを設置する
//        flag.setLmFlag(LAST_MONTH);
//        flag.setTmFlag(THIS_MONTH);

        //取得した値は画面に送る
//        model.addAttribute("kojin", kojin);
//        model.addAttribute("flag", flag);
//        model.addAttribute("msg", null);
//        return "staff/kojin";
        String result = kojinBusiness.kojin(model, request, session);
        return result;
    }

    //画面を閉じるのfunction
    @RequestMapping(value = "/tojiru", method = {RequestMethod.GET})
    public String tojiru(Model model,HttpSession session) {
        LoginForm account = new LoginForm();
        HeaderController head = new HeaderController();
        model.addAttribute("account", account);
        model.addAttribute("msg", null);
        String result = head.deleteCookie(session);
        return result;
    }
}