/*获取用户登录名*/
app.service('loginService',function ($http) {
    this.getLoginName=function () {
        return $http.get('../../login/getLoginName.do');
    }
})