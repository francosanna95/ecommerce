
const app = Vue.createApp({
  data() {
    return {
      hotels: [],
      tickets: [],
      events: [],
      email: "",
      password: "",
      isPasswordVisible: false
    }
  },
  created() {

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


