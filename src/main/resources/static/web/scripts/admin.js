
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
                     "productId": asd,
                     "points":200,
                     "address":"Av. siempreviva 123",
                     "name":"Los Simpson Hotel",
                     "maxCapacity":40,
                     "stock":40,
                     "imgUrl":"IMG url",
                     "origin":"cordoba",
                     "destination":"bsAs",
                     "departureDate": "2015-08-04T10:11:30",
                     "arrivalDate": "2015-08-04T10:11:30"
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


