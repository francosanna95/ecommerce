
const app = Vue.createApp({
  data() {
    return {
        service: "",
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
        console.log(this.service)
        console.log(this.price)
        console.log(this.discountCode)
        console.log(this.productName)
        console.log(this.description)
        console.log(this.stock)
        console.log(this.imgUrl)
        console.log(this.address)
        console.log(this.departureDate)
        console.log(this.arrivalDate)
        console.log(this.departureLocation)
        console.log(this.arrivalLocation)
        console.log(this.agencyName)

        axios({
            method:'POST',
            url:`/api/products/tickets`,
            data:{
                     "price": this.price,
                     "discountCode": this.discountCode,
                     "productName": this.productName,
                     "description" : this.description,
                     "stock": this.stock,
                     "imgUrl": this.imgUrl,
                     "address": this.address,
                     "departureTime": this.departureDate,
                     "arrivalTime" : this.arrivalDate,
                     "departureLocation" : this.departureLocation,
                     "arrivalLocation" : this.arrivalLocation,
                     "agencyName" : this.agencyName
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


