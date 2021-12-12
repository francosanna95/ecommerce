const app = Vue.createApp({
    data() {
        return {
            cart: [],
            email: "",
            password: "",
            cart: [],
            currentUser: [],


            firstName: "",
            lastName: "",
            roleUser: "",
            isPasswordVisible: false,
            isAdmin: false,
            isClient: false,

            firstName: "",
            lastName:"",
            email:"",
            country:"", 
            message:""

        }
    },
    created() {
        axios.get("/api/clients/current")
            .then(response => {
                console.log(response.data)
                this.currentUser = response.data
                if (response.data.role == "CLIENT") {
                    this.isClient = true;
                    this.isAdmin = false;
                } else if (response.data.role == "AGENCY") {
                    this.isClient = false;
                    this.isAdmin = true;
                }
            })


        if (sessionStorage.getItem('cart')) {
            try {
                this.cart = JSON.parse(sessionStorage.getItem('cart'));
            } catch (e) {
                sessionStorage.removeItem('cart');
            }
            console.log(this.cart)
        };
    },
    methods: {
        totalProductsInCart() {
            const array = this.cart
            function reducer(previous, current, index, array) {
                const product = array[index];
                const returns = previous + (parseInt(product.quantity));
                return returns;
            }
            return array.reduce(reducer, 0);
        },
        totalPriceCalc() {
            const array = this.cart
            function reducer(previous, current, index, array) {
                const product = array[index];
                const returns = previous + (product.finalPrice * product.quantity);
                return returns;
            }
            return array.reduce(reducer, 0);
        },
        savingCart() {
            const parsed = JSON.stringify(this.cart);
            sessionStorage.setItem('cart', parsed);
        },
        removeOne(prod) {
            console.log(prod);
            console.log(typeof prod.id)
            let findProd = this.cart.findIndex(product => product.id == prod.id);
            console.log(findProd);
            axios.post("/api/clients/current/removeFromCart", `userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(resp => {
                    console.log(findProd)
                    this.cart[findProd].quantity--;
                    if (this.cart[findProd].quantity <= 0) { this.cart.splice(findProd, 1) }
                    this.savingCart();
                    if (this.cart.length == 0) {
                        sessionStorage.removeItem('cart');
                    }
                }).catch(err => console.log(err))
        },
        removeAll(prod) {
            axios.post('/api/clients/current/finalRemoveFromCart', `userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(resp => {
                    if (this.cart.some(product => product.id == prod.id)) {
                        let index = this.cart.findIndex(product => product.id == prod.id);
                        console.log(index);
                        this.cart.splice(index, 1);
                        this.savingCart();
                        if (this.cart.length == 0) {
                            sessionStorage.removeItem('cart');
                        }
                    }
                }).catch(err => console.log(err))
        },
        addProduct(prod) {
            axios.post("/api/clients/current/add1toCart", `userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(resp => {
                    let id = this.cart.findIndex(product => product.id == prod.id);
                    console.log(id)
                    console.log(this.cart)
                    this.cart[id].quantity++;
                    this.savingCart();

                })
        },
        login(e) {
            if (e) {
                e.preventDefault()
            }


            axios.post('/api/login', `email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    console.log(response)
                    window.location.reload()
                })
                .catch(error => {
                    console.log(error.response.status)
                    console.log(error.response.data)
                })
        },
        logout() {
            axios.post('/api/logout')
                .then(response => {
                    sessionStorage.removeItem('cart'); //alerta de exito
                    console.log("loged out!");
                    this.isClient = false;
                    this.isAdmin = false;
                    window.location.reload();
                })
                .catch(error => {
                    console.log('Error', error.message);
                })
        },

        sendMessage(){
            console.log(this.message);
            console.log(typeof this.message);
            axios.post('/api/help', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&country=${this.country}&comment=${this.message}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response=>{
                swal("We will contact you as soon as possible", {
                    title: "Thanks for getting in touch with us!",
                    buttons: "OK!",
                    icon: "success"
                  })                    
            })
        }
    },
})
const verAppVue = app.mount("#app")