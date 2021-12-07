
const app = Vue.createApp({
  data() {
    return {
        service:"",
        price: 0,
        discountCode: "",
        address: "",
        productName: "",
        description: "",
        stock: "",
        imgUrl: "",
        agencyName:"",

        departureLocation:"",
        arrivalLocation:"",
        departureDate:"",
        arrivalDate:"",

        parking: false,
        concierge: false,

        vip: false

    }
  },
  computed: {
  },
  methods: {
    newTicket(){
        axios({
            method:'POST',
            url:`/api/products/tickets`,
            data:{
                     "price": this.price,
                     "discountCode": this.discountCode,
                     "description" : "This is a great ticket, the one you need",
                     "productName":"El mejor vuelo",
                     "stock":250,
                     "imgUrl":"IMG url",
                     "address":"Aeropuerto Internacional de Ezeiza",
                     "departureTime": "2015-08-04T10:11:30",
                     "arrivalTime" : "2015-08-04T10:11:30",
                     "departureLocation" : "Buenos Aires",
                     "arrivalLocation" : "Bariloche",
                     "agencyName" : "Babel"
            }
        })
    },
    newHotel(){
        axios({
             method:'POST',
             url:`/api/products/hotels`,
             data:{
                 "price":1500,
                 "disscountCode":"asd123",
                 "description":"the new Franco's inn!",
                 "productName":"Los Simpson Hotel",
                 "stock":40,
                 "imgUrl":"IMG url",
                 "address":"Av. siempreviva 123",
                 "parking":false,
                 "concierge":true,
                 "agencyName" : "Babel"
             }
        })
    },
    newEvent(){
        axios({
            method:'POST',
            url:`/api/products/events`,
            data:{
                "price":1500,
                "disscountCode":"asd123",
                "description" : "Maluma Baby",
                "productName":"The new Maluma experience",
                "stock":250,
                "imgUrl":"IMG url",
                "address":"Aeropuerto Internacional de Ezeiza",
                "agencyName" : "Babel",
                "artist":"Maluma",
                "vip":true
            }
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
    },
})
app.mount("#app")


