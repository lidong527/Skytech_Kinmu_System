package sky.co.jp.business;

import sky.co.jp.queryData.AllReportQueryObject;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.QueryObject;

import java.util.List;

public interface IQueryBusiness {
    //liuyang 20201028 交通费根据条件查询
    PageResult<AllReportQueryObject> queryList(QueryObject qo);

    //liuyang 20201028 勤务表根据条件查询
    PageResult<AllReportQueryObject> queryListByKinmu(QueryObject qo);

    //liuyang 20201028 立替金根据条件查询
    PageResult<AllReportQueryObject> queryListByTate(QueryObject qo);

    //liuyang 20201106 根据条件查询所有
    PageResult<AllReportQueryObject> queryListByAll(QueryObject qo);
}
