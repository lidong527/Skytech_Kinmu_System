package sky.co.jp.queryData;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryObject {
    private int currentPage=1;
    private int pageSize=2;
    private String keyword;
    //0代表上月
    private int monthFlag;
    //zhou 20201028
    //退职标识
    private int resignFlag=1;
    public int getStart() {
        return (currentPage - 1) * pageSize;
    }


}
