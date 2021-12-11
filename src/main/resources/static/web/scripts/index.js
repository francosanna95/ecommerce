
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
  }
})

app.mount("#app")


