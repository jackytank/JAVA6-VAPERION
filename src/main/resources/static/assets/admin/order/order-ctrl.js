app.controller("order-ctrl", function ($scope, $http) {
    $scope.orders = [];
    $scope.users = [];
    $scope.orderDetails = [];

    $scope.initialize = function () {
        $scope.userListLoading = true;
        $http.get("/rest/users").then(resp => {
            $scope.users = resp.data;
        }).catch(err => {
            console.error(err);
        }).finally(function () {
            $scope.userListLoading = false;
        });
    };

    $scope.displayOrderByUsername = function (username) {
        $scope.orderLoading = true;
        $http.get(`/rest/orders/list/${username}`).then(resp => {
            $scope.orders = resp.data;
        }).catch(err => {
            console.error(err);
        }).finally(function () {
            $scope.orderLoading = false;
        });
    };

    $scope.initialize();

    $scope.storageProducts = {
        items: [],
        loadFromLocalStorage() {
            let json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        },
        order_details() {
            return this.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty,
                };
            });
        }

    };

    $scope.storageProducts.loadFromLocalStorage();

    $scope.displayOrderDetails = function (orderId) {
        $scope.detailLoading = true;
        $http.get(`/rest/orders/detail/${orderId}`).then(resp => {
            $scope.orderDetails = resp.data;
        }).catch(err => {
            console.error(err);
        }).finally(function () {
            $scope.detailLoading = false;
        });
        $(".nav-tabs a:eq(1)").tab('show');
    };

    // $scope.reset = function () {
    //     $scope.form = {
    //         create_date: new Date(),
    //         image: 'default-product.jpg',
    //         available: true,
    //     };
    // };

    // $scope.edit = function (item) {
    //     $scope.form = angular.copy(item);
    //     $(".nav-tabs a:eq(0)").tab('show');
    // };

    // $scope.create = function () {
    //     var item = angular.copy($scope.form);
    //     if (item.image == null) {
    //         item.image = 'default-product.jpg';
    //     }
    //     if (item.available == null) {
    //         item.available = true;
    //     }
    //     $http.post(`/rest/products`, item).then(resp => {
    //         resp.data.create_date = new Date(resp.data.create_date);
    //         $scope.items.push(resp.date);
    //         $scope.reset();
    //         $scope.initialize();
    //         alert("Thêm mới sản phẩm thành công");
    //     }).catch(error => {
    //         alert("Lỗi thêm mới sản phẩm");
    //         console.log("Error", error);
    //     });
    // };

    // $scope.update = function () {
    //     var item = angular.copy($scope.form);
    //     let check = confirm(`Are you sure to update this product?`);
    //     if (check) {
    //         $http.put(`/rest/products/${item.id}`, item).then(resp => {
    //             var index = $scope.items.findIndex(p => p.id == item.id);
    //             $scope.items[index] = item;
    //             $scope.initialize();
    //             alert("Cập nhập sản phẩm thành công");
    //         }).catch(error => {
    //             alert("Lỗi cập nhập sản phẩm");
    //             console.log("Error", error);
    //         });
    //     }
    // };

    // $scope.delete = function (item) {

    //     let check = confirm(`Are you sure to delete this product ${item.name}?`);
    //     if (check) {
    //         $http.delete(`/rest/products/${item.id}`).then(resp => {
    //             var index = $scope.items.findIndex(p => p.id == item.id);
    //             $scope.items.splice(index, 1);
    //             $scope.reset();
    //             $scope.initialize();
    //             alert("Xóa sản phẩm thành công");
    //         }).catch(error => {
    //             alert("Xóa sản phẩm thất bại");
    //             console.log("Error", error);
    //         });
    //     }
    // };



    // $scope.pager = {
    //     page: 0,
    //     size: 10,
    //     get items() {
    //         let start = this.page * this.size;
    //         return $scope.items.slice(start, start + this.size);
    //     },
    //     get count() {
    //         return Math.ceil(1.0 * $scope.items.length / this.size);
    //     },
    //     first() {
    //         this.page = 0;
    //     },
    //     prev() {
    //         this.page--;
    //         if (this.page < 0) {
    //             this.last();
    //         }
    //     },
    //     next() {
    //         this.page++;
    //         if (this.page >= this.count) {
    //             this.first();
    //         }
    //     },
    //     last() {
    //         this.page = this.count - 1;
    //     }
    // };
});