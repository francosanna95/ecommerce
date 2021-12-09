const app = Vue.createApp({

    data() {
        return {
            hotels: [],
            hotel: ""
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
        }
    }, 

})

const verAppVue = app.mount("#app") 
