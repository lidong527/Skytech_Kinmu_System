//package sky.co.jp.controller;
//
//import org.junit.platform.commons.util.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import sky.co.jp.bean.StaffBean;
//import sky.co.jp.form.AllStatusResultForm;
//import sky.co.jp.form.AllStatusSearchForm;
//import sky.co.jp.mapper.StaffMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class AllStatusController {
//
//    @Autowired(required = false)
//    StaffMapper staffMapper;
//
//    /**
//     * 初期画面表示
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/allStatus", method = {RequestMethod.GET})
//    public String signUp(Model model) {
//
//        // 画面情報設定
//        AllStatusSearchForm search = new AllStatusSearchForm();
//        List<AllStatusResultForm> result = new ArrayList<>();
//
//        // データ取得
//        List<StaffBean> staffBeans = staffMapper.selectAll();
//        for (StaffBean staffBean : staffBeans) {
//            AllStatusResultForm resultForm = new AllStatusResultForm();
//            resultForm.setStaffId(String.valueOf(staffBean.getSTAFF_BG()));
//            resultForm.setStaffName(staffBean.getSTAFF_NAME());
//            resultForm.setCurrentType(String.valueOf(staffBean.getSTAFF_STATUS()));
//            resultForm.setPaymentCurrentMonth(String.valueOf(staffBean.getTM_TATEKAE_STATUS()));
//            resultForm.setPaymentLastMonth(String.valueOf(staffBean.getLM_TATEKAE_STATUS()));
//            resultForm.setTransExpenseCurrentMonth(String.valueOf(staffBean.getTM_KOUTU_STATUS()));
//            resultForm.setTransExpenseLastMonth(String.valueOf(staffBean.getLM_KOUTU_STATUS()));
//            resultForm.setWorkTableCurrentMonth(String.valueOf(staffBean.getTM_KINMU_STATUS()));
//            resultForm.setWorkTableLastMonth(String.valueOf(staffBean.getLM_KINMU_STATUS()));
//            result.add(resultForm);
//        }
//
//        model.addAttribute("msg", null);
//        model.addAttribute("search", search);
//        model.addAttribute("result", result);
//
//        return "allstatus";
//    }
//
//    /**
//     * 社員情報登録
//     *
//     * @param
//     * @return
//     */
//    @PostMapping(value = "/allStatus/search")
//    public String search(Model model, @ModelAttribute AllStatusSearchForm search) {
//
//        List<AllStatusResultForm> result = new ArrayList<>();
//        List<StaffBean> searchResult;
//
//        // 検索実施
//        searchResult = staffMapper.selectAll();
//
//        for(StaffBean staffBean:searchResult){
//
//            // 検索条件で絞り込み
//            // 社員番号
//            if (StringUtils.isNotBlank(search.getStaffId())
//                    && !search.getStaffId().equals(String.valueOf(staffBean.getSTAFF_BG()))) {
//                continue;
//            }
//            // 名前
//            if (StringUtils.isNotBlank(search.getStaffName())
//                    && !search.getStaffName().equals(staffBean.getSTAFF_NAME())) {
//                continue;
//            }
//            // 勤務表状態
//            if (!"0".equals(search.getStatusWorkTable())
//                    && !search.getStatusWorkTable().equals(String.valueOf(staffBean.getTM_KINMU_STATUS()))) {
//                continue;
//            }
//            // 交通費状態
//            if (!"0".equals(search.getStatusTransExpense())
//                    && !search.getStatusTransExpense().equals(String.valueOf(staffBean.getTM_KOUTU_STATUS()))) {
//                continue;
//            }
//            // 立替金明細
//            if (!"0".equals(search.getStatusPayment())
//                    && !search.getStatusPayment().equals(String.valueOf(staffBean.getTM_TATEKAE_STATUS()))) {
//                continue;
//            }
//
//            AllStatusResultForm resultForm = new AllStatusResultForm();
//            resultForm.setStaffId(String.valueOf(staffBean.getSTAFF_BG()));
//            resultForm.setStaffName(staffBean.getSTAFF_NAME());
//            resultForm.setCurrentType(String.valueOf(staffBean.getSTAFF_STATUS()));
//            resultForm.setPaymentCurrentMonth(String.valueOf(staffBean.getTM_TATEKAE_STATUS()));
//            resultForm.setPaymentLastMonth(String.valueOf(staffBean.getLM_TATEKAE_STATUS()));
//            resultForm.setTransExpenseCurrentMonth(String.valueOf(staffBean.getTM_KOUTU_STATUS()));
//            resultForm.setTransExpenseLastMonth(String.valueOf(staffBean.getLM_KOUTU_STATUS()));
//            resultForm.setWorkTableCurrentMonth(String.valueOf(staffBean.getTM_KINMU_STATUS()));
//            resultForm.setWorkTableLastMonth(String.valueOf(staffBean.getLM_KINMU_STATUS()));
//            result.add(resultForm);
//        }
//
//        model.addAttribute("msg", null);
//        model.addAttribute("search", search);
//        model.addAttribute("result", result);
//
//        return "allstatus";
//    }
//}
