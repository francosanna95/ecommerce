
const app = Vue.createApp({
  data() {
    return {
      hotels: [],
      tickets: [],
      events: [],
      hotelsCantTres: [],
      ticketsCantTres: [],
      eventsCantTres: [],
      productToFilter: "",
      filtrado: "",
      filtradoHotel: "",
      filtradoEvent: "",
      filtradoTicket: "",

      email: "",
      password: "",
      cart:[],

      firstName: "",
      lastName: "",
      roleUser: "",
      isPasswordVisible: false
    }
  },
  created() {
    axios.get('/api/products/hotels')
      .then(response => {
        console.log(response.data)
        this.hotels = response.data
        filtradoParaCard(response.data)
      })
      .catch(error => {
        return error.message;
      })
    axios.get('/api/products/tickets')
      .then(response => {
        console.log(response.data)
        this.tickets = response.data
        filtradoParaCard(response.data)
      })
      .catch(error => {
        return error.message;

      })

    axios.get('/api/products/events')
      .then(response => {
        console.log(response.data)
        this.events = response.data
        filtradoParaCard(response.data)
      })
      .catch(error => {
        return error.message;
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
  computed: {
    showPassword() {
      if (this.isPasswordVisible) {
        return "text";
      } else {
        return "password";
      }
    },
    filteredHotels() {
      return this.hotels.filter(hotel => hotel.address.toLowerCase().includes(this.filtradoHotel.toLowerCase())).slice(0, 3)
    },
    filteredEvents() {
      return this.events.filter(hotel => hotel.address.toLowerCase().includes(this.filtradoEvent.toLowerCase())).slice(0, 3)
    },
    filteredTickets() {
      return this.tickets.filter(hotel => hotel.address.toLowerCase().includes(this.filtradoTicket.toLowerCase())).slice(0, 3)
    },
    filteredRef(){
      return `#${this.productToFilter}`;
    }
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
             const returns = previous + (product.finalPrice*product.quantity);
             return returns;
        }
        return array.reduce(reducer, 0);
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
    logout() {
      axios.post('/api/logout')
        .then(response => {
        })
        .catch(error => {
          console.log('Error', error.message);
        })
    },
    filter() {
      if (this.productToFilter == "events") {
        this.filtradoEvent = this.filtrado;
      } else if (this.productToFilter == "tickets") {
        this.filtradoTicket = this.filtrado;
      } else if (this.productToFilter == "hotels") {
        this.filtradoHotel = this.filtrado;
      }
    },
    validEmail(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    removeOne(prod){
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
  }
})

app.mount("#app")


