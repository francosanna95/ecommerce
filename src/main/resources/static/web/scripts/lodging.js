const app = Vue.createApp({

    data() {
        return {
            hotels: [],
            hotel: true,

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
    },
    methods: {
        productId(numero) {
            return `#${numero}`
        },
        hotelShow(product) {
            this.hotel = product;
            console.log(product);
        },
        haveParking(hotel) {
            if (hotel == true) {
                return "Yes"
            } else {
                return "No"
            }
        }
    }, 

})

const verAppVue = app.mount("#app") 
