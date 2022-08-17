let app = angular.module("admin-app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/statistic", {
            templateUrl: "/assets/admin/statistic/index.html",
            controller: "statistic-ctrl"
        })
        .when("/product", {
            templateUrl: "/assets/admin/product/index.html",
            controller: "product-ctrl"
        })
        .when("/user", {
            templateUrl: "/assets/admin/user/index.html",
            controller: "user-ctrl"
        })
        .when("/order", {
            templateUrl: "/assets/admin/order/index.html",
            controller: "order-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/assets/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "/assets/admin/product/unauthorized.html",
            controller: "authority-ctrl"
        })
        .otherwise({
            templateUrl: "/assets/admin/statistic/index.html",
            controller: "statistic-ctrl"
        });
});