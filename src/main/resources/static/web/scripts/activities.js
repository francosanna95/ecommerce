const app = Vue.createApp({

    data() {
        return {
            events: [],
        }
    },

    created() {

        axios.get('/api/products/events')
        
        .then(response => {
            console.log(response.data)
            this.events = response.data
        })
        .catch(error => {
            return error.message;
        })
    },
    methods: {
              
    }, 

})

const verAppVue = app.mount("#app") 