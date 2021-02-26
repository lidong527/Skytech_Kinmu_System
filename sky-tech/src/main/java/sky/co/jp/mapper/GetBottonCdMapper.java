package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sky.co.jp.bean.GetButtonCdBean;

import java.util.List;

public interface GetBottonCdMapper {

    @Select("select CODE_CONTENT             " +
            "     , CODE_CONTENT2            " +
            "  from M_CODE                   " +
            " where CODE_ID = #{BUTTON_TYPE} " +
            "   and DELETE_FLG = 0           " )
    List<GetButtonCdBean> selectContent12ById(String buttonType);

    @Select("select CODE_CONTENT        " +
            "  from M_CODE              " +
            " where CODE_ID = #{CODE_ID}" +
            "   and DELETE_FLG = 0      " )
    GetButtonCdBean selectContentById(String codeId);

    @Select("select CODE_CONTENT2" +
            "  from M_CODE" +
            " where CODE_ID = #{codeId}" +
            "   and CODE_CONTENT = #{codeContent}" +
            "   and DELETE_FLG = 0")
    GetButtonCdBean selectContent2ByIdAndC1(@Param("codeId") String id, @Param("codeContent") String Content);

    @Select("select CODE_CONTENT" +
            "  from M_CODE" +
            " where CODE_ID = #{codeId}" +
            "   and CODE_CONTENT2 = #{codeContent2}" +
            "   and DELETE_FLG = 0")
    GetButtonCdBean selectContent1ByIdAndC2(@Param("codeId") String id, @Param("codeContent2") String Content);

    @Select("select CODE_CONTENT" +
            "  from M_CODE" +
            " where CODE_ID = #{codeId}" +
            "   and (CODE_CONTENT2 = '社員'" +
            "    or CODE_CONTENT2 = '組長')")
    List<GetButtonCdBean> selectC1ByIdAndC2s(@Param("codeId") String id);

    @Select("select CODE_CONTENT3" +
            "  from m_code" +
            " where CODE_ID = #{codeId}" +
            "   and CODE_CONTENT = #{codeContent}")
    GetButtonCdBean select3ByIdAnd1(@Param("codeId") String id,@Param("codeContent") String codeContent);
}
