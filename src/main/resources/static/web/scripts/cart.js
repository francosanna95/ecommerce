
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
    }
  },
  created() {
        axios.get('/api/clients/4')
            .then(
                resp=>{
                console.log(resp.data.cart)
                this.cart=resp.data.cart;
                console.log(this.cart)}
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
  //mounted() {
  //    if (sessionStorage.getItem('cart')) {
  //        try {
  //           this.cart = JSON.parse(sessionStorage.getItem('cart'));
  //        } catch (e) {
  //            sessionStorage.removeItem('cart');
  //          }
  //        console.log(this.cart)
  //    };
  //},
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
    validEmail(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
  }
})

app.mount("#app")


