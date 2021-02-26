package sky.co.jp.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.co.jp.business.IQueryBusiness;
import sky.co.jp.mapper.SkyKinmuMapper;
import sky.co.jp.mapper.KoutuMapper;
import sky.co.jp.mapper.TatekaeMapper;
import sky.co.jp.queryData.AllReportQueryObject;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.QueryObject;
import sky.co.jp.util.DateUtil;
import sky.co.jp.util.StringUtil;

import java.util.List;
@Service
public class QueryBusinessImpl implements IQueryBusiness {

    @Autowired
    KoutuMapper koutuMapper;
    @Autowired
    SkyKinmuMapper kinmuMapper;
    @Autowired
    TatekaeMapper tatekaeMapper;


    @Override
    public PageResult<AllReportQueryObject> queryList(QueryObject qo) {
        if (!StringUtil.hasLength(qo.getKeyword())){
            qo.setKeyword(null);
        }
        //查总条数
        int count = koutuMapper.selectForCount(qo);
        System.out.println("count="+count );
        //总条数不为0的时候查结果集
        if(count == 0){
            return new PageResult<AllReportQueryObject>(qo.getCurrentPage(),qo.getPageSize());
        }
        List<AllReportQueryObject> list = koutuMapper.selectKoutuByQuery(qo);

        return new PageResult<AllReportQueryObject>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }


    @Override
    public PageResult<AllReportQueryObject> queryListByKinmu(QueryObject qo) {
        if (!StringUtil.hasLength(qo.getKeyword())){
            qo.setKeyword(null);
        }
        //查总条数
        int count = kinmuMapper.selectForCount(qo);
        System.out.println("count="+count );
        //总条数不为0的时候查结果集
        if(count == 0){
            return new PageResult<AllReportQueryObject>(qo.getCurrentPage(),qo.getPageSize());
        }
        List<AllReportQueryObject> list = kinmuMapper.selectKinmuByQuery(qo);

        return new PageResult<AllReportQueryObject>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }


    @Override
    public PageResult<AllReportQueryObject> queryListByTate(QueryObject qo) {
        if (!StringUtil.hasLength(qo.getKeyword())){
            qo.setKeyword(null);
        }
        //查总条数
        int count = tatekaeMapper.selectForCount(qo);
        System.out.println("count="+count );
        //总条数不为0的时候查结果集
        if(count == 0){
            return new PageResult<AllReportQueryObject>(qo.getCurrentPage(),qo.getPageSize());
        }
        List<AllReportQueryObject> list = tatekaeMapper.selectTateByQuery(qo);
        return new PageResult<AllReportQueryObject>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }


    @Override
    public PageResult<AllReportQueryObject> queryListByAll(QueryObject qo) {
        if (!StringUtil.hasLength(qo.getKeyword())){
            qo.setKeyword(null);
        }
        //查总条数
        int count = koutuMapper.selectForAllCount(qo);
        System.out.println("count="+count );
        //总条数不为0的时候查结果集
        if(count == 0){
            return new PageResult<AllReportQueryObject>(qo.getCurrentPage(),qo.getPageSize());
        }
        List<AllReportQueryObject> list = koutuMapper.selectAllByQuery(qo);
        return new PageResult<AllReportQueryObject>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }


}
