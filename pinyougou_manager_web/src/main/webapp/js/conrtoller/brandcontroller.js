// 控制层
app.controller("brandController",function ($scope,$controller,brandService) {

    // 继承baseController
    $controller('baseController',{$scope:$scope});

    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        )
    }


    //分页
    $scope.findPage=function(page,rows){
        brandService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
    // 添加品牌
    $scope.save=function () {
        // 新增和修改公用save方法，定义变量进行区分
        /*var methodName = "add";*/
        var object = null;
        if($scope.entity.id != null){
            // 执行更新操作
            object = brandService.update($scope.entity);
        }
        else
        {
            object = brandService.add($scope.entity);
        }
        object.success(
            function (response) {
                if(response.flag){// 添加成功
                    $scope.reloadList();
                }
                else
                {// 添加失败
                    alert(response.message)
                }
            }
        )
    }
    //根据id查询数据
    $scope.findOne=function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity=response;
            }
        )
    }

    // 条件查询
    $scope.searchEntity={};
    $scope.search=function (page,rows) {
        brandService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
})