angular.module("shopping-cart-app").controller("user-ctrl", userController);

function userController($scope, $http, $interval) {
    $scope.items = [];
    $scope.providers = [];
    $scope.form = {};
    $scope.roles = [];
    $scope.message = '';

    $scope.initialize = function () {
        $http.get("/rest/users/principal").then(resp => {
            $scope.form = resp.data;
        }).catch(error => {
            console.log("Error", error);
        });

        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
    };

    $scope.initialize();


    $scope.update = function () {
        let item = angular.copy($scope.form);
        // alert(JSON.stringify(item));
        $http.put(`/rest/users/${item.id}`, item).then(resp => {
            $scope.message = "Update successfully!";
        }).catch(error => {
            $scope.message = "Error while updating: " + error.message;
            console.log("Error", error);
        });
    };

    $scope.imageChanged = function (files) {
        let data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image_url = resp.data.name;
        }).catch(error => {
            $scope.message = "Error while upload image: " + error.message;
            console.log("Error", error);
        });
    };

    // reload products list every 10s
    // $interval($scope.initialize, 1000);

}