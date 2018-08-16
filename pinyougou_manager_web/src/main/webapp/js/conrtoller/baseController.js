app.controller('baseController',function ($scope) {

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }
    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };
    // 获取选中的项目id
    $scope.selectIds = [];
    $scope.getSelections=function ($event,id) {
        // 如果复选框为选中状态，就添加到数组中
        if($event.target.checked){
            $scope.selectIds.push(id);
        }else{
            // 获取到未选中的索引
            var idx = $scope.selectIds.indexOf(id);
            // 从数组中移除
            $scope.selectIds.splice(idx,1);
        }
    }
    // 删除选中项目
    $scope.deleteSelected=function () {
        brandService.deleteSelected($scope.selectIds).success(
            function (response) {
                if(response.flag){
                    $scope.reloadList();
                }
                else
                {alert("删除失败")
                }
            }
        )
    }
})