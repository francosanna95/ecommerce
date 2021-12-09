
const app = Vue.createApp({
  data() {
    return {
      hotels: [],
      tickets: [],
      events: [],
      hotelsCantTres: [],
      ticketsCantTres: [],
      eventsCantTres: [],
      filtradoCiudad: 'Miami',
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

},
  computed: {
    showPassword() {
      if (this.isPasswordVisible) {
        return "text";
      } else {
        return "password";
      }
    },

    filterByCity(){
      console.log('hola');
      this.hotels = this.hotels.filter( hotel => hotel.address.includes(this.filtradoCiudad))

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

    filtradoParaCard(){

        this.hotelsCantTres = this.hotels.slice(0,2)
       /* this.ticketsCantTres = arrayTypeCard.filter(tickets => tickets.length <= 3)
        this.eventsCantTres = arrayTypeCard.filter(events => events.length <= 3)*/

      },

    validEmail(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
  }
})

app.mount("#app")


