app.controller("authority-ctrl", function ($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function () {
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        }).catch(err => {
            console.error(err);
        });

        $http.get("/rest/users?admin=true").then(resp => {
            $scope.admins = resp.data;
        }).catch(err => {
            console.error(err);
        });

        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(err => {
            console.error(err);
            $location.path("/unauthorized");
        })
    }
    $scope.initialize();
    $scope.authority_of = function (acc, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(ur => ur.user.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function (_user, _role) {
        var authority = $scope.authority_of(_user, _role)
        if (authority) {
            $scope.revoke_authority(authority)
        } else {
            authority = {user: _user, role: _role};
            $scope.grant_authority(authority);
        }
    }

    $scope.revoke_authority = function (authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id)
            $scope.authorities.splice(index, 1);
            alert("Thu hồi quyền sử dụng thành công")
        }).catch(error => {
            alert("Thu hồi quyền sử dụng thất bại")
            console.log("Error", error);
        })
    }

    $scope.grant_authority = function (authority) {
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data)
            alert("Cập nhập quyền sử dùng thành công")
        }).catch(error => {
            alert("Cập nhập quyền sử dụng thất bại")
            console.log("Error", error)
        })
    }
})
