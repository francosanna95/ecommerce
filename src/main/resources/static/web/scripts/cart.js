
const app = Vue.createApp({
  data() {
    return {
      hotels: [],
      tickets: [],
      events: [],

      email: "",
      password: "",
      cart: [],
      currentUser: [],

      fullName:"",
      cvv:"",
      expirationDate:"",
      cardNumber:"",


      firstName: "",
      lastName: "",
      roleUser: "",
      isPasswordVisible: false,
      isAdmin: false,
      isClient: false,

      isPasswordVisible: false,
      cart: [],
      backCart: [],
      totalPrice: 0,
    }
  },
  created() {

    axios.get("/api/clients/current")
      .then(response => {
        this.currentUser = response.data
        if (response.data.role == "CLIENT") {
          this.isClient = true;
          this.isAdmin = false;
        } else if (response.data.role == "AGENCY") {
          this.isClient = false;
          this.isAdmin = true;
        }
      })


    axios.get('/api/products/hotels')
      .then(response => {
        this.hotels = response.data;
      })
      .catch(error => {
        return error.message;
      })

    axios.get('/api/products/tickets')
      .then(response => {
        this.tickets = response.data
      })
      .catch(error => {
        return error.message;

      })

    axios.get('/api/products/events')
      .then(response => {
        this.events = response.data
      })
      .catch(error => {
        return error.message;
      })
  },
  mounted() {
    if (sessionStorage.getItem('cart')) {
      try {
        this.cart = JSON.parse(sessionStorage.getItem('cart'));
      } catch (e) {
        sessionStorage.removeItem('cart');
      }
      console.log(this.cart)
    };
  },
  computed: {
    showPassword() {
      if (this.isPasswordVisible) {
        return "text";
      } else {
        return "password";
      }
    }
  },
  methods: {
    payAll(e) {
      swal({
        title: "We are processing your purchase, this may take a few moments...",
        buttons: "OK!",
        icon: "success"
      })
      axios.post(`https://mh-homebanking.herokuapp.com/api/transactions/cardPayment?amount=${this.totalPriceCalc()}&description=Melba%Trips%Purchasing&cvv=${this.cvv}&thruDate=${this.expirationDate}&email=${this.currentUser.email}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
        .then(res => {
          console.log(res);
          if (res.status == 202) {
            this.finalPurchase()
          } else {
            //alerta con el error
          }
        })
        .catch(response => {
          swal(response.response.data, {
            title: "Something went wron with the details you provided us, please retry.",
            buttons: "OK!",
            icon: "error"
          })
        })

    },
    finalPurchase() {
      axios.post("/api/clients/current/endPurchase")
        .then(response => {         
          if (response.status == 200) {
           sessionStorage.removeItem('cart'); //alerta de exito
            swal("We have sent you an email with your invoice and the details of your purchase", {
              title: "Thanks for buying with us!",
              buttons: "OK!",
              icon: "success"
            })
              .then((e) => {
                if (e) {
                  window.location.href = "./profile.html"
                }
              })
          }

        })
    },
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
    login(e) {
      if (e) {
        e.preventDefault()
      }
      if (!this.validEmail(this.email)) {
        this.validMailLogIn = false
        return
      } else {
        this.validMailLogIn = true
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
    removeOne(prod) {
      //if(this.cart.some(product=>product.productId==prod.id)){
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

      //window.location.reload()})
      //                     }else{alert("no tenes este producto en tu carrito")}
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
            // window.location.reload()
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
    validEmail(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
  }
})

app.mount("#app")


