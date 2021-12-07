const app = Vue.createApp({

    data() {
        return {
            tickets: [],
        }
    },

    created() {

        axios.get('/api/products/tickets')
        
        .then(response => {
            console.log(response.data)
            this.tickets = response.data
        })
        .catch(error => {
            return error.message;
        })
    },
    methods: {
              
    }, 

})

const verAppVue = app.mount("#app") 