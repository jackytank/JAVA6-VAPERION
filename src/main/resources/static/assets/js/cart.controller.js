// fetch('/rest/users/' + $('#login-username').text()).then(res => res.json()).then(data => console.log(data.id)).catch(err => { console.error(err); });

angular.module("shopping-cart-app").controller("shopping-cart-ctrl", shoppingCartCtrl);

function shoppingCartCtrl($scope, $http) {
    $scope.cart = {
        items: [],
        stepDown(el) {
            if (el > 0) {
                el = el - 1;
            } else {
                el = 1;
            }
        },

        add(id) {
            let item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(res => {
                    res.data.qty = 1;
                    this.items.push(res.data);
                    this.saveToLocalStorage();
                });
            }
        },
        remove(id) {
            let index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        ammount_of(item) {
        },
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem('cart', json);

        },
        loadFromLocalStorage() {
            let json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        }
    };
    $scope.cart.loadFromLocalStorage();
    // let user = {};
    // fetch('/rest/users/' + $('#login-username').text()).then(res => res.json()).then(data => { user = data; });
    // console.log(user);

    $scope.order = {
        create_date: new Date(),
        address: "",
        user: {
            username: $('#login-username').text()
        },
        get order_details() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty,
                };
            });
        },
        purchase() {
            let order = angular.copy(this);
            console.log(order);
            $http.post('/rest/orders', order).then(res => {
                alert("Order successfully created");
                $scope.cart.clear();
                location.href = "/order/detail/" + res.data.id;
            }).catch(err => {
                alert("Error creating order!");
                console.log(err);
            });
        }
    };
    // console.log($scope.order);
}

