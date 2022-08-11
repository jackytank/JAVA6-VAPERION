app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function () {
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.create_date = new Date(item.create_date);
            });
        });

        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
    };


    $scope.reset = function () {
        $scope.form = {
            create_date: new Date(),
            image: 'default-product.jpg',
            available: true,
        };
    };

    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    };

    $scope.create = function () {
        var item = angular.copy($scope.form);
        if (item.image == null) {
            item.image = 'default-product.jpg';
        }
        if (item.available == null) {
            item.available = true;
        }
        $http.post(`/rest/products`, item).then(resp => {
            resp.data.create_date = new Date(resp.data.create_date);
            $scope.items.push(resp.date);
            $scope.reset();
            $scope.initialize();
            alert("Thêm mới sản phẩm thành công");
        }).catch(error => {
            alert("Lỗi thêm mới sản phẩm");
            console.log("Error", error);
        });
    };

    $scope.update = function () {
        var item = angular.copy($scope.form);
        let check = confirm(`Are you sure to update this product?`);
        if (check) {
            $http.put(`/rest/products/${item.id}`, item).then(resp => {
                var index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                $scope.initialize();
                alert("Cập nhập sản phẩm thành công");
            }).catch(error => {
                alert("Lỗi cập nhập sản phẩm");
                console.log("Error", error);
            });
        }
    };

    $scope.delete = function (item) {

        let check = confirm(`Are you sure to delete this product ${item.name}?`);
        if (check) {
            $http.delete(`/rest/products/${item.id}`).then(resp => {
                var index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items.splice(index, 1);
                $scope.reset();
                $scope.initialize();
                alert("Xóa sản phẩm thành công");
            }).catch(error => {
                alert("Xóa sản phẩm thất bại");
                console.log("Error", error);
            });
        }
    };

    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lối upload hình ảnh");
            console.log("Error", error);
        });
    };
    $scope.initialize();

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
    console.log($scope.pager.items);
});