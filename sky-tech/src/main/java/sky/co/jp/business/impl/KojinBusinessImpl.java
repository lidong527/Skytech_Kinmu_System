package sky.co.jp.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.KojinBean;
import sky.co.jp.business.IKojinBusiness;
import sky.co.jp.controller.HeaderController;
import sky.co.jp.form.FlagForm;
import sky.co.jp.form.KojinForm;
import sky.co.jp.form.LoginForm;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.SkyKojinMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class KojinBusinessImpl implements IKojinBusiness {
    //社員のステータス
    private static final String STATUS = "STAFF_STATUS";
    //先月のマック
    private static final String LAST_MONTH = "LM";
    //今月のマック
    private static final String THIS_MONTH = "TM";

    @Autowired(required = false)
    SkyKojinMapper skyKojinMapper;

    @Autowired(required = false)
    HeaderController headerController;

    @Autowired(required = false)
    SessionMapper sessionMapper;

    @Override
    public String kojin(Model model, HttpServletRequest request, HttpSession session) {
        String staffBg = headerController.CheckCookie(request, session);

        //staffBgによってDBから検索した結果はKojinBeanを与える
        KojinBean kojinBean = new KojinBean();
        kojinBean = skyKojinMapper.selectKojinStatusByStaffBg(staffBg);

        //DBから社員ステータスを検索する
        GetButtonCdBean getButtonCdBean = new GetButtonCdBean();
        try {
            getButtonCdBean = sessionMapper.selectContent2ByIdAndContent(STATUS,String.valueOf(kojinBean.getSTAFF_STATUS()));
        } catch (NullPointerException e){
            LoginForm account = new LoginForm();
            model.addAttribute("account", account);
            model.addAttribute("msg", "ログインが無効になりました。もう一度ログインしてください");
            return "skyindex";
        }
        String status = getButtonCdBean.getCODE_CONTENT2();

        //KojinFormを定義する
        KojinForm kojin = new KojinForm();
        //KojinFormに値を設置する
        kojin.setStaffStatus(status);
        kojin.setGanbanName(kojinBean.getGENBAN_NAME());
        kojin.setGenbanAddress(kojinBean.getGENBAN_ADDRESS());
        kojin.setItType(kojinBean.getIT_TYPE());
        kojin.setRemarks(kojinBean.getREMARKS());
        kojin.setLmKinmu(kojinBean.getLM_KINMU_STATUS());
        kojin.setLmKoutu(kojinBean.getLM_KOUTU_STATUS());
        kojin.setLmTate(kojinBean.getLM_TATEKAE_STATUS());
        kojin.setStaffBg(staffBg);
        kojin.setStaffName(kojinBean.getSTAFF_NAME());
        kojin.setStaffSex(kojinBean.getSTAFF_SEX());
        kojin.setTmkinmu(kojinBean.getTM_KINMU_STATUS());
        kojin.setTmKoutu(kojinBean.getTM_KOUTU_STATUS());
        kojin.setTmTate(kojinBean.getTM_TATEKAE_STATUS());
        kojin.setStandardTime(kojinBean.getSTANDARD_TIME());
        kojin.setUpperTime(kojinBean.getUPPER_TIME());
        //FlagFormを定義する
        FlagForm flag = new FlagForm();
        //FlagFormにマックを設置する
        flag.setLmFlag(LAST_MONTH);
        flag.setTmFlag(THIS_MONTH);

        //取得した値は画面に送る
        model.addAttribute("kojin", kojin);
        model.addAttribute("flag", flag);
        model.addAttribute("msg", null);
        return "staff/kojin";
    }
}
