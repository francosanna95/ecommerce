
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
        /*console.log(this.service)*/
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
                "price": `${this.price}`,
                "disscountCode": `${this.discountCode}`,
                "productName": `${this.productName}`,
                "description": `${this.description}`,
                "stock": `${this.stock}`,
                "imgUrl": `${this.imgUrl}`,
                "address": `${this.address}`,
                "departureTime": `${this.departureDate}` + "T10:11:30",
                "arrivalTime": `${this.arrivalDate}` + "T10:11:30",
                "departureLocation" : `${this.departureLocation}`,
                "arrivalLocation" : `${this.arrivalLocation}`,
                "agencyName": `${this.agencyName}`
            },
            headers: {'content-type': 'application/json'}
        })
    },
    newHotel(){
        axios({
             method:'POST',
             url:`/api/products/hotels`,
             data:{
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
    },
    newEvent(){
        axios({
            method:'POST',
            url:`/api/products/events`,
            data:{
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


