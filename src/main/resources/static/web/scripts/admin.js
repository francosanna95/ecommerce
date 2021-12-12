
const app = Vue.createApp({
  data() {
    return {
      service: "",
      price: 0,
      discountCode: "",
      address: "",
      productName: "",
      description: "",
      stock: 0,
      imgUrl: "",
      agencyName: "",

      departureLocation: "",
      arrivalLocation: "",
      departureDate: "",
      arrivalDate: "",


      parking: false,
      concierge: false,

      vip: false,

      email: "",
      password: "",
      cart: [],
      currentUser: [],


      firstName: "",
      lastName: "",
      roleUser: "",
      isPasswordVisible: false,
      isAdmin: false,
      isClient: false

    }
  },
  computed: {
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
  }
  ,
  methods: {
    newTicket() {
      axios({
        method: 'POST',
        url: `/api/products/tickets`,
        data: {
          "price": `${this.price}`,
          "disscountCode": `${this.discountCode}`,
          "productName": `${this.productName}`,
          "description": `${this.description}`,
          "stock": `${this.stock}`,
          "imgUrl": `${this.imgUrl}`,
          "address": `${this.address}`,
          "departureTime": `${this.departureDate}` + "T10:11:30",
          "arrivalTime": `${this.arrivalDate}` + "T10:11:30",
          "departureLocation": `${this.departureLocation}`,
          "arrivalLocation": `${this.arrivalLocation}`,
          "agencyName": `${this.agencyName}`
        },
        headers: { 'content-type': 'application/json' }
      })
        .then(response => {
          console.log(response);
          swal({
            title: "Done!",
            text: "New Ticket Created",
            buttons: "Got it",
            icon: "success"
          }).then((value) => {
            if (value) {
              window.location.reload()
            }
          })

          //setTimeout(() => location.reload(), 2500 );
        })
        .catch(err => {
          console.log(err);
          swal('Error', 'Cannot create more flights', 'error');
        })
    },
    newHotel() {
      axios({
        method: 'POST',
        url: `/api/products/hotels`,
        data: {
          "price": `${this.price}`,
          "disscountCode": `${this.discountCode}`,
          "description": `${this.description}`,
          "productName": `${this.productName}`,
          "stock": `${this.stock}`,
          "imgUrl": `${this.imgUrl}`,
          "address": `${this.address}`,
          "parking": `${this.parking}`,
          "concierge": `${this.concierge}`,
          "agencyName": `${this.agencyName}`
        }
      })
        .then(response => {
          console.log(response);
          swal({
            title: "Done!",
            text: "New Lodging Created",
            buttons: "Got it",
            icon: "success"
          }).then((value) => {
            if (value) {
              window.location.reload()
            }
          })
        })
        .catch(err => {
          console.log(err);
          swal('Error', 'Cannot create more flights', 'error');
        })
    },
    newEvent() {
      axios({
        method: 'POST',
        url: `/api/products/events`,
        data: {
          "price": `${this.price}`,
          "disscountCode": `${this.discountCode}`,
          "description": `${this.description}`,
          "productName": `${this.productName}`,
          "stock": `${this.stock}`,
          "imgUrl": `${this.imgUrl}`,
          "address": `${this.address}`,
          "agencyName": `${this.agencyName}`,
          "artist": `${this.artist}`,
          "vip": `${this.vip}`
        }
      })
        .then(response => {
          console.log(response);
          swal({
            title: "Done!",
            text: "New Event Created",
            buttons: "Got it",
            icon: "success"
          }).then((value) => {
            if (value) {
              window.location.reload()
            }
          })

          //setTimeout(() => location.reload(), 2500 );
        })
        .catch(err => {
          console.log(err);
          swal('Error', 'Cannot create more flights', 'error');
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
  },
})
app.mount("#app")


