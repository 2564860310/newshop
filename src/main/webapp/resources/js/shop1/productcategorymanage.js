$(function () {
    var shopId=getQueryString('shopId');
    var queryProductCategoryUrl="/newshop/shopadmin/getproductcategorylist?shopId="+shopId;
    var addProductCategoryUrl="/newshop/shopadmin/addproductcategorys";
    var deleteProductCategoryUrl = "/newshop/shopadmin/removeproductcategory";
    function getList() {
        $.getJSON(queryProductCategoryUrl,function (data) {
            if (data.success) {
                var dataList=data.data;
                $(".category-wrap").html('');
                var temphtml = '';
                dataList.map(function (item,index) {
                    temphtml += '<div class="row row-product-category now">'
                             + '<div class="col-33 product-category-name">'
                             + item.productCategoryName
                             + '</div>'
                             + '<div class="col-33">'
                             + item.priority
                             + '</div>'
                             + '<div class="col-33"><a href="#" class="button delete" data-id="'
                             + item.productCategoryId
                             + '">删除</a></div>'
                             + '</div>';
                });
                $(".category-wrap").append(temphtml);
            }
        });
    }

});