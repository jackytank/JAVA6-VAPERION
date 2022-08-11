angular.module("shopping-cart-app").controller("search-ctrl", searchController);

function searchController($scope, $http, $interval) {
    $scope.productList = [];
    $scope.cates = [];

    $scope.initialize = function () {
        $http.get('/rest/products').then(res => {
            $scope.productList = res.data;
        });
        $http.get('/rest/categories').then(res => {
            $scope.cates = res.data;
        });
    };

    $scope.filterProductByCategory = function (categoryId) {
        $http.get('/rest/products/category/' + categoryId).then(res => {
            $scope.productList = res.data;
        });
    };

    $scope.searchProducts = function (query) {
        $http.get('/rest/products/search?query=' + query).then(res => {
            // alert('Product Search Successful');
            $scope.productList = res.data;
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