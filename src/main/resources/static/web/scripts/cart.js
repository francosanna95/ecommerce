
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
    }
  },
  created() {
        axios.get('/api/clients/4')
            .then(
                resp=>{
                console.log(resp.data.cart)
                this.backCart=resp.data.cart;
                console.log(this.backCart)}
                )
        axios.get('/api/products/hotels')
        .then(response => {
            console.log(response.data)
            this.hotels= response.data
        })
        .catch(error => {
            return error.message;
      })

      axios.get('/api/products/tickets')
        .then(response => {
            console.log(response.data)
            this.tickets = response.data
        })
        .catch(error => {
            return error.message;

        })

        axios.get('/api/products/events')
        .then(response => {
            console.log(response.data)
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
        axios.post('/api/clients/current/removeFromCart',`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
        .then(resp=>{if(this.cart.some(product=>product.productId==prod.id)){
            let findProd= this.cart.findIndex(product=>product.productId==prod.id);
            }}).then(resp=>{console.log(this.cart[findProd])//.quantity--;
                            if(this.cart[this.findProd<=0]){this.cart.splice(this.findProd,1)}
                            this.savingCart();})
                            //window.location.reload()})
   },
    removeAll(prod){
        axios.post('/api/clients/current/finalRemoveFromCart',`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
        .then(resp=>{ if(this.cart.some(product=>product.productId==prod.id)){
            let id=this.cart.findIndex(product=>product.productId==prod.id);
            this.cart.splice(id,1);
            this.savingCart();}
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
  }
})

app.mount("#app")


