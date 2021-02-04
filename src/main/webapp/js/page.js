let globalPageNum=0;
let globalSize=0;
//分页
function page(pageInfo){
    $(".pageList").html("");
    let $lis="";
    $(pageInfo.navigatepageNums).each(function () {
        let isActive="";
        if(pageInfo.pageNum==this){
            isActive=`class="active"`;
        }
        $lis+=`<li `+isActive+` data-pagenum="`+this+`"><a href="javascript:void(0)">`+this+`</a></li>`;
    });

    let isFirstPage="";
    if(pageInfo.isFirstPage){
        isFirstPage=`class="disabled"`;
    }
    let isLastPage="";
    if(pageInfo.isLastPage){
        isLastPage=`class="disabled"`;
    }

    let $nav=`<nav aria-label="Page navigation">
                      <ul class="pagination">
                        <li data-pagenum="`+pageInfo.prePage+`" `+isFirstPage+`>
                          <a  href="javascript:void(0)" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                          </a>
                        </li>
                        <li data-pagenum="1" >
                          <a  href="javascript:void(0)" aria-label="Previous">
                            <span aria-hidden="true">首页</span>
                          </a>
                        </li>
                        `+$lis+`
                        <li data-pagenum="`+pageInfo.pages+`" >
                          <a  href="javascript:void(0)" aria-label="Previous">
                            <span aria-hidden="true">尾页</span>
                          </a>
                        </li>
                        <li data-pagenum="`+pageInfo.nextPage+`" `+isLastPage+`>
                          <a  href="javascript:void(0)" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                          </a>
                        </li>
                      </ul>
                    </nav>`;
    $(".pageList").append($nav);
}

//点击页面跳转
$("body").on("click",".pagination li",function () {
    let pageNum=$(this).data("pagenum");
    if($(this).prop("class")=="disabled"){
        return;//结束方法
    }
    loadData(pageNum);
    setPageNum(pageNum);
});