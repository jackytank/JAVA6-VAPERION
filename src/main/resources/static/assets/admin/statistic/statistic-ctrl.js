app.controller("statistic-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};
    $scope.totalOrder = 0;
    $scope.totalUserCount = 0;

    $scope.totalDirectorCount = 0;
    $scope.totalStaffCount = 0;
    $scope.totalCustomerCount = 0;
    $scope.shit = [];

    $scope.initialize = function () {
        $http.get(`/rest/orders`).then(resp => {
            $scope.totalOrder = resp.data.length;

        }).catch(err => { console.error(err); });
        $http.get(`/rest/users`).then(resp => {
            $scope.totalUserCount = resp.data.length;
        }).catch(err => { console.error(err); });

        $http.get(`/rest/usersrole/count`).then(resp => {
            shit = resp.data;
            $scope.totalDirectorCount = shit.find(el => el.role_id === 'DIRE').count;
            $scope.totalStaffCount = shit.find(el => el.role_id === 'STAF').count;
            $scope.totalCustomerCount = shit.find(el => el.role_id === 'CUST').count;
        }).catch(err => { console.error(err); });
    };

    $scope.initialize();

});