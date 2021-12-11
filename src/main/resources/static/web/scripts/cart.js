
const app = Vue.createApp({
  data() {
    return {
      hotels: [],
      tickets: [],
      events: [],
      email: "",
      password: "",
      isPasswordVisible: false,
      cart:[],
      backCart:[],
      totalPrice:0,
    }
  },
  created() {
        axios.get('/api/clients/4')
            .then(
                resp=>{
                this.backCart=resp.data.cart;
                console.log(this.backCart)}
                )
        axios.get('/api/products/hotels')
        .then(response => {
            this.hotels=response.data;
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
    },
    totalPriceCalc(){
    this.totalPrice=this.cart.reduce(acumulator,product=> acumulator+= product.quantity*product.finalPrice)
    return this.cart}

  },
  methods: {
    totalPriceCalc() {
          const array = this.cart
          function reducer(previous, current, index, array) {
            const product = array[index];
            const returns = previous + (product.finalPrice*product.quantity);
            return returns;
          }
          return array.reduce(reducer, 0);
        },
    savingCart(){
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
         window.location.href = "./index.html"
        })
        .catch(error => {
         console.log(error.response.status)
         console.log(error.response.data)
        })
    },
    removeOne(prod){
        //if(this.cart.some(product=>product.productId==prod.id)){
        console.log(prod);
        console.log(typeof prod.id)
            let findProd=this.cart.findIndex(product=>product.id==prod.id);
            console.log(findProd);
        axios.post("/api/clients/current/removeFromCart",`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
        .then(resp=>{ console.log(findProd)
                      this.cart[findProd].quantity--;
                      if(this.cart[findProd].quantity<=0){this.cart.splice(findProd,1)}
                      this.savingCart();
                      if(this.cart.length==0){
                         sessionStorage.removeItem('cart');}}).catch(err=>console.log(err))

                            //window.location.reload()})
       //                     }else{alert("no tenes este producto en tu carrito")}
   },
    removeAll(prod){
        axios.post('/api/clients/current/finalRemoveFromCart',`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
        .then(resp=>{ if(this.cart.some(product=>product.id==prod.id)){
            let index=this.cart.findIndex(product=>product.id==prod.id);
            console.log(index);
            this.cart.splice(index,1);
            this.savingCart();
            if(this.cart.length==0){
              sessionStorage.removeItem('cart');}
           // window.location.reload()
            }
        }).catch(err=>console.log(err))
},
    addProduct(prod){
        axios.post("/api/clients/current/add1toCart",`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
        .then(resp=>{
            let id=this.cart.findIndex(product=>product.id==prod.id);
            console.log(id)
            console.log(this.cart)
             this.cart[id].quantity++;
             this.savingCart();

        })
        },

    logout() {
      axios.post('/api/logout')
        .then(response => {
        })
        .catch(error => {
          console.log('Error', error.message);
        })
    },
    validEmail(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
  },
})

app.mount("#app")


