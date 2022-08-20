app.controller("user-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.providers = [];
    $scope.form = {};
    $scope.roles = [];

    $scope.initialize = function () {
        $scope.loading = true;
        $http.get("/rest/roles").then(resp => {
            new Set(resp.data).forEach(role => {
                if (!$scope.roles.includes({ id: role.id })) {
                    $scope.roles.push(role);
                }
            });
            // $scope.roles.forEach(role => console.log(role.id));
            // let findShit = $scope.roles.find(({ id }) => id === 'DIRE');
            // console.log('find role:' + findShit.id);
        }).catch(err => {
            console.error(err);
        });
        $http.get("/rest/users").then(resp => {
            $scope.items = resp.data;
            let i = 0;
            new Set(resp.data).forEach(user => {
                if (!$scope.providers.includes(user.provider)) {
                    $scope.providers.push(user.provider);
                }
            });
            // console.log('providers: ' + $scope.providers.find(p => p === 'GOOGLE'));
        }).finally(function () {
            $scope.loading = false;
        });
    };


    $scope.reset = function () {
        $scope.form = {
            image_url: 'default-user.jpg',
        };
    };

    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    };

    $scope.create = function () {
        var item = angular.copy($scope.form);
        if (item.image_url == null) {
            item.image_url = 'default-user.jpg';
        }
        if (item.role == null) {
            let findRole = $scope.roles.find(({ id }) => id === 'STAF');
            item.role = findRole.id;
        }
        if (item.provider == null) {
            item.provider = $scope.providers.find(p => p === 'DATABASE');
        }
        let isExist = false;
        $scope.items.forEach(user => {
            if (user.username === item.username) {
                isExist = true;
                alert("Username already exists. Please choose another username.");
            } else if (user.email === item.email) {
                isExist = true;
                alert("Email already exists. Please choose another email address.");
            }
        });
        if (!isExist) {
            // alert(item);
            let userRole = {};

            console.log(item);
            $http.post(`/rest/users`, item).then(resp => {
                let message = '';
                $scope.items.push(resp.data);
                // savedUser = resp.data;
                userRole = { user: resp.data, role: { id: item.role } };
                console.log(`Saved user role is: ${JSON.stringify(userRole)}`);

                // set authority for new user
                $scope.grant_authority(userRole);

                $scope.reset();
                $scope.initialize();
                alert(message + "Create new user successfully!");
            }).catch(error => {
                alert("Error while creating user: " + error.message);
                console.log("Error", error);
            });

            // console.log('User role' + JSON.stringify(userRole));

        }
    };

    $scope.update = function () {
        let item = angular.copy($scope.form);
        let check = confirm(`Are you sure to update this user?`);
        if (check) {


            $http.put(`/rest/users/${item.id}`, item).then(resp => {
                let index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                $scope.initialize();
                alert("Update user successfully!");
            }).catch(error => {
                alert("Error while updating user: " + error.message);
                console.log("Error", error);
            });
        }
    };

    $scope.delete = function (item) {
        let check = confirm(`Are you sure to delete this user ${item.username}?`);
        if (check) {
            $http.delete(`/rest/users/${item.username}`).then(resp => {
                let index = $scope.items.findIndex(p => p.username == item.username);
                $scope.items.splice(index, 1);
                $scope.reset();
                $scope.initialize();
                alert("Delete user successfully!");
            }).catch(error => {
                alert("Error while deleting user: " + error.message);
                console.log("Error", error);
            });
        }
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
            alert("Lối upload hình ảnh");
            console.log("Error", error);
        });
    };
    $scope.initialize();

    $scope.authority_changed = function (_user, _role) {
        var authority = $scope.authority_of(_user, _role);
        if (authority) {
            $scope.revoke_authority(authority);
        } else {
            authority = { user: _user, role: _role };
            $scope.grant_authority(authority);
        }
    };

    $scope.revoke_authority = function (authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            alert("Thu hồi quyền sử dụng thành công");
        }).catch(error => {
            alert("Thu hồi quyền sử dụng thất bại");
            console.log("Error", error);
        });
    };

    $scope.grant_authority = function (authority) {
        $http.post(`/rest/authorities`, authority).then(resp => {
            alert("Cập nhập quyền sử dùng thành công");
        }).catch(error => {
            alert("Cập nhập quyền sử dụng thất bại");
            console.log("Error", error);
        });
    };

    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            let start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    };
});