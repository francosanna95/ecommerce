const app = Vue.createApp({

    data() {
        return {
            hotels: [],
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
              
    }, 

})

const verAppVue = app.mount("#app") 
