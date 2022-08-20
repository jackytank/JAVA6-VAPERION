angular.module("shopping-cart-app").controller("product-ctrl", productController);

function productController($scope, $http, $interval) {
    $scope.loading = true;
    $scope.productList = [];
    $scope.cates = [];
    // $scope.sortNames = ['Price (Ascending)', 'Price (Descending)'];
    $scope.sortNames = {
        price: [
            { code: 'price_asc', name: 'Price (Ascending)' },
            { code: 'price_desc', name: 'Price (Descending)' }
        ]
    };

    $scope.sortProducts = function (sortCode) {
        $scope.productList = [];
        $scope.loading = true;
        $http.get('/rest/products?sort=' + sortCode).then(res => {
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });
    };


    $scope.initialize = function () {
        $http.get('/rest/products').then(res => {
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });

        $http.get('/rest/categories').then(res => {
            $scope.cates = res.data;
        }).catch(error => { console.error(error); });
    };

    $scope.filterProductByCategory = function (categoryId) {
        $scope.productList = [];
        $scope.loading = true;
        $http.get('/rest/products/category/' + categoryId).then(res => {
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });
    };

    $scope.searchProducts = function (query) {
        $scope.productList = [];
        $scope.loading = true;
        $http.get('/rest/products/search?query=' + query).then(res => {
            // alert('Product Search Successful');
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });
    };

    $scope.pager = {
        page: 0,
        size: 10,
        get productList() {
            let start = this.page * this.size;
            return $scope.productList.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.productList.length / this.size);
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

    $scope.initialize();
    // reload products list every 10s
    // $interval($scope.initialize, 1000);

}