/**
 * 去掉前端左右两边的字符空格
 * @param str
 *  字符串
 *
 */
function trim(str){
    //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}